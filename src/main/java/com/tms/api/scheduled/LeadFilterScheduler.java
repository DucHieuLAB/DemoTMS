package com.tms.api.scheduled;


import com.tms.api.consts.EnumType;
import com.tms.api.consts.MessageConst;
import com.tms.api.exception.TMSDbException;
import com.tms.api.helper.*;
import com.tms.api.service.*;
import com.tms.dto.request.clFresh.InsClFresh;

import com.tms.dto.response.CampaignInf;
import com.tms.dto.response.CfBlackList;
import com.tms.dto.response.ClBasket;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.LocalDateTime;

import java.util.Comparator;
import java.util.List;

/**
 * This is the LeadFilter class, which is responsible for filtering leads.
 *
 * @author HieuTD
 */
@Component
public class LeadFilterScheduler extends BaseService {

    private final BlackListService blackListService;

    private final CampaignService campaignService;

    private final ClBasketService clBasketService;

    private final ClFreshService clFreshService;

    public LeadFilterScheduler(BlackListService blackListService, CampaignService campaignService, ClBasketService clBasketService, ClFreshService clFreshService) {
        this.blackListService = blackListService;
        this.campaignService = campaignService;
        this.clBasketService = clBasketService;
        this.clFreshService = clFreshService;
    }

    private final String LOCAL_TIME_ZONE = "Asia/Ho_Chi_Minh";

    @Scheduled(fixedDelay = 5000)
    public void filterLead() throws TMSDbException {
        // Get Data To Process
        List<ClBasket> clBaskets = clBasketService.getListToProcess(sessionId);
        if (clBaskets.size() == 0){
            return;
        }
        if (BlackLists.blackLists.size() == 0) {
            BlackLists.blackLists = blackListService.getBlackList();
        }
        if (CampaignInfos.campaignInfList.size() == 0){
            CampaignInfos.campaignInfList = campaignService.getCampainInfs(EnumType.Campaign.CALLING_LIST.getType());
        }
        List<CfBlackList> blackLists = BlackLists.blackLists;
        List<CampaignInf> campaignInfs = CampaignInfos.campaignInfList;
        // Sort by create time for check duplicate
        clBaskets.sort(Comparator.comparing(ClBasket::getCreateDate));

        filterInvalidPhonesAndBlackListAndDuplicate(clBaskets, blackLists);

        clBasketService.updateClBasket(clBaskets, sessionId, LOCAL_TIME_ZONE);

        List<InsClFresh> clFreshes = ClFreshConverter.convertToInsClFreshs(clBaskets);
        updateCampaignInf(clFreshes, campaignInfs);
        logger.info("Insert Cl Fresh");
        clFreshService.insertClFresh(clFreshes, sessionId);
    }

    private void updateStatusFilter(ClBasket basket) {
        ZoneId zone = ZoneId.of(LOCAL_TIME_ZONE);
        basket.setModifyDate(LocalDateTime.now(zone));
        basket.setModifyBy(curUserId);
        basket.setAttribute3(EnumType.Filltter.DONE_FILLTER_VALUE.getValue());
    }

    private void updateCampaignInf(List<InsClFresh> clFreshes, List<CampaignInf> campaignInfs) {
        clFreshes.forEach(insClFresh -> {
            CampaignInf inf = getCampainInfByProId(insClFresh.getProdId(), campaignInfs);
            if (inf != null) {
                insClFresh.setCpId(inf.getCpId());
                insClFresh.setCampaignName(inf.getCpName());
                insClFresh.setCallingListId(inf.getCallinglistId());
                insClFresh.setCallingListName(inf.getClName());
            }
        });
    }

    private void filterInvalidPhonesAndBlackListAndDuplicate(List<ClBasket> clBaskets, List<CfBlackList> blackLists) {
        for (ClBasket basket : clBaskets) {
            updateStatusFilter(basket);
            if (basket.getStatus() == EnumType.LeadStatus.TRASH.getStatus()){
                continue;
            }
            // Check phone num valid
            if (!PhoneHeper.checkPhoneValid(basket.getPhone())) {
                basket.setStatus(EnumType.LeadStatus.TRASH.getStatus());
                basket.setComment(MessageConst.ERROL_INVALID_PHONE_MESSAGE);
            }
            // check in blacklist
            if (isInBlackList(basket, blackLists) && basket.getStatus()!= EnumType.LeadStatus.TRASH.getStatus()) {
                basket.setStatus(EnumType.LeadStatus.TRASH.getStatus());
                basket.setComment(MessageConst.ERROL_IN_BLACKLIST_MESSAGE);
            }
            // check self duplicate
            if (checkSelfDuplicate(basket, clBaskets) && basket.getStatus()!= EnumType.LeadStatus.TRASH.getStatus()) {
                basket.setStatus(EnumType.LeadStatus.TRASH.getStatus());
                basket.setComment(MessageConst.ERROL_DUPLICATE_MESSAGE);
            }
            if (!DuplicateLeadChecker.isEmpty() && basket.getStatus()!= EnumType.LeadStatus.TRASH.getStatus()){
                if (DuplicateLeadChecker.isDuplicate(basket.getPhone(), basket.getProdName())) {
                    basket.setStatus(EnumType.LeadStatus.TRASH.getStatus());
                    basket.setComment(MessageConst.ERROL_DUPLICATE_MESSAGE);
                }
            }
            DuplicateLeadChecker.addRecord(basket);
        }
    }

    private boolean checkSelfDuplicate(ClBasket basket, List<ClBasket> result) {
        LocalDateTime createDate1 = basket.getCreateDate();
        LocalDateTime minTime = createDate1.minusHours(24);
        for (ClBasket extbasket : result) {
            if (extbasket == basket) {
                continue;
            }
            if (!extbasket.getStatus().equals(EnumType.LeadStatus.TRASH.getStatus())
                    && extbasket.getPhone().equals(basket.getPhone())
                    && extbasket.getProdId().equals(basket.getProdId())
                    && extbasket.getCreateDate().isAfter(minTime)
                    && extbasket.getCreateDate().isBefore(createDate1)) {
                return true;
            }
        }
        return false;
    }

    private boolean isInBlackList(ClBasket basket, List<CfBlackList> blackLists) {
        // if list is empty return false
        if (blackLists == null || blackLists.size() == 0) {
            return false;
        }
        for (CfBlackList blackList : blackLists) {
            if (basket.getPhone().equals(blackList.getPhone())) {
                return true;
            }
        }
        return false;
    }

    private CampaignInf getCampainInfByProId(Integer prodId, List<CampaignInf> campaignInfs) {
        for (CampaignInf inf : campaignInfs) {
            if (inf.getProductId().equals(prodId)) {
                return inf;
            }
        }
        return null;
    }
}
