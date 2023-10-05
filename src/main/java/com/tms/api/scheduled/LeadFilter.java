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

import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class LeadFilter extends BaseService {

    private final BackListService backListService;

    private final CampaignService campaignService;

    private final ClBasketService clBasketService;

    private final ClFreshService clFreshService;

    public LeadFilter(BackListService backListService, CampaignService campaignService, ClBasketService clBasketService, ClFreshService clFreshService) {
        this.backListService = backListService;
        this.campaignService = campaignService;
        this.clBasketService = clBasketService;
        this.clFreshService = clFreshService;
    }

    private final String LOCAL_TIME_ZONE ="Asia/Ho_Chi_Minh";

    @Scheduled(cron = "0 24 10 * * *", zone = "Asia/Ho_Chi_Minh")
    public void filterLead() throws TMSDbException {
        // Get Data To Process
        List<ClBasket> clBaskets = clBasketService.getListToProcess(sessionId);
        List<CfBlackList> blackLists = backListService.getBlackList(sessionId);
        LocalDateTime time = findMinimumCreateDate(clBaskets);
        List<ClBasket> leadsInRange = clBasketService.getLeadInTimeRange(time,time.minus(24, ChronoUnit.HOURS));
        List<CampaignInf> campaignInfs = campaignService.getCampainInfs(EnumType.Campaign.CALLING_LIST.getType());

        Collections.sort(clBaskets);

        filterInvalidPhonesAndBlackListAndListInRage(clBaskets,blackLists,leadsInRange);

        clBasketService.updateClBasket(clBaskets,sessionId,LOCAL_TIME_ZONE);

        List<InsClFresh> clFreshes = ClFreshConverter.convertToInsClFreshs(clBaskets);
        updateCampaignInf(clFreshes,campaignInfs);
        clFreshService.insertClFresh(clFreshes,sessionId);
    }

    public static LocalDateTime findMinimumCreateDate(List<ClBasket> leadBaskets) {
        if (leadBaskets == null || leadBaskets.isEmpty()) {
            return null; // Trả về null nếu danh sách rỗng hoặc null
        }
        int firstIndex = 0;
        LocalDateTime minCreateDate = leadBaskets.get(firstIndex).getCreateDate();

        for (ClBasket leadBasket : leadBaskets) {
            LocalDateTime currentCreateDate = leadBasket.getCreateDate();
            if (currentCreateDate != null && currentCreateDate.isBefore(minCreateDate)) {
                minCreateDate = currentCreateDate;
            }
        }
        return minCreateDate;
    }

    private void updateStatus(ClBasket basket){
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

    private void filterInvalidPhonesAndBlackListAndListInRage(List<ClBasket> clBaskets, List<CfBlackList> blackLists, List<ClBasket> leadsInRange)  {
        for (ClBasket basket : clBaskets){
            // Check phone num valid
            if (!PhoneHeper.checkPhoneValid(basket.getPhone())){
                basket.setStatus(EnumType.LeadStatus.TRASH.getStatus());
                basket.setComment(MessageConst.ERROL_INVALID_PHONE_MESSAGE);
                continue;
            }
            // check in blacklist
            if(isInBlackList(basket,blackLists)){
                basket.setStatus(EnumType.LeadStatus.TRASH.getStatus());
                basket.setComment(MessageConst.ERROL_IN_BLACKLIST_MESSAGE);
                continue;
            }
            // Check Duplicate
            if(checkSelfDuplicate(basket,clBaskets)){
                basket.setStatus(EnumType.LeadStatus.TRASH.getStatus());
                basket.setComment(MessageConst.ERROL_DUPLICATE_MESSAGE);
                continue;
            }
            if (checkForDuplicates(basket,leadsInRange)){
                basket.setStatus(EnumType.LeadStatus.TRASH.getStatus());
                basket.setComment(MessageConst.ERROL_DUPLICATE_MESSAGE);
            }
            updateStatus(basket);
        }
    }

    private boolean checkSelfDuplicate(ClBasket basket, List<ClBasket> result) {
        LocalDateTime createDate1 = basket.getCreateDate();
        LocalDateTime minTime = createDate1.minusHours(24);
        for (ClBasket extbasket: result){
            if (extbasket == basket){
                continue;
            }
            if (!extbasket.equals(EnumType.LeadStatus.TRASH.getStatus())
                    && extbasket.getPhone().equals(basket.getPhone())
                    && extbasket.getProdId().equals(basket.getProdId())
                    && extbasket.getCreateDate().isAfter(minTime)
                    && extbasket.getCreateDate().isBefore(createDate1)){
                return true;
            }
        }
        return false;
    }

    private boolean checkForDuplicates(ClBasket basket, List<ClBasket> leadsInRange) {
        if (leadsInRange == null){
            return false;
        }
        LocalDateTime createDate1 = basket.getCreateDate();
        LocalDateTime minTime = createDate1.minusHours(24);

        List<ClBasket> matchingList = leadsInRange.stream()
                .filter(basket2 -> !basket2.getStatus().equals(EnumType.LeadStatus.TRASH.getStatus())
                        && basket.getPhone().equals(basket2.getPhone())
                        && basket.getProdId().equals(basket2.getProdId())
                        && basket2.getCreateDate().isAfter(minTime)
                        && basket2.getCreateDate().isBefore(createDate1))
                .collect(Collectors.toList());

        return !matchingList.isEmpty();
    }

    private boolean isInBlackList(ClBasket basket, List<CfBlackList> blackLists) {
        // if list is empty return false
        if (blackLists == null ||blackLists.size() == 0){
            return false;
        }
        for (CfBlackList blackList : blackLists){
            if (basket.getLeadId().equals(blackList.getLeadId())){
                return true;
            }
        }
        return false;
    }

    private CampaignInf getCampainInfByProId(Integer prodId, List<CampaignInf> campaignInfs) {
        for(CampaignInf inf : campaignInfs){
            if (inf.getProductId().equals(prodId)){
                return inf;
            }
        }
        return null;
    }
}
