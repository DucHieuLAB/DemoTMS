package com.tms.api.scheduled;

<<<<<<< HEAD

import com.tms.api.consts.EnumType;
import com.tms.api.consts.MessageConst;
import com.tms.api.exception.TMSDbException;
import com.tms.api.helper.*;
import com.tms.api.service.*;
import com.tms.dto.request.clFresh.InsClFresh;

import com.tms.dto.response.CampaignInf;
import com.tms.dto.response.CfBlackList;
import com.tms.dto.response.ClBasket;
=======
import com.tms.api.consts.EnumType;
import com.tms.api.exception.TMSDbException;
import com.tms.api.helper.DateHelper;
import com.tms.api.service.BaseService;
import com.tms.api.helper.PhoneHeper;
import com.tms.commons.DBResponse;
import com.tms.dao.BlackListDao;
import com.tms.dao.LeadFillterDao;
import com.tms.dto.request.blacklist.GetBlackList;
import com.tms.dto.request.lead.GetLeadBasketsInTimeRange;
import com.tms.dto.request.lead.GetLeadToFillter;
import com.tms.dto.request.lead.ClFresh;
import com.tms.dto.request.lead.UpdLeadFillter;
import com.tms.dto.response.BlackList;
import com.tms.dto.response.LeadBasket;
import org.springframework.beans.factory.annotation.Autowired;
>>>>>>> 1a2fcc0 ([Fear] Fillter Lead)
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.temporal.ChronoUnit;
import java.time.LocalDateTime;

import java.time.ZoneId;
import java.time.LocalDateTime;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
<<<<<<< HEAD

=======
>>>>>>> 1a2fcc0 ([Fear] Fillter Lead)

@Component
public class LeadFillter extends BaseService {

<<<<<<< HEAD
    private final BackListService backListService;

    private final CampaignService campaignService;
=======
    @Autowired
    BlackListDao blackListDao;
    @Autowired
    LeadFillterDao leadFillterDao;

    private final String DONE_FILLTER_KEY_VALUE = "1";
    private final String GET_LEAD_FILLTER_VALUE = "0";
    private final String ERROL_INVALID_PHONE_MESSAGE = "Phone not match (+84|84|0)?[0-9]{9}";
    private final String ERROL_IN_BLACKLIST_MESSAGE = "Lead is in blacklist";
    private final String ERROL_DUPLICATE_MESSAGE = "Lead is duplicate";
    private final int TRASH_STATUS = 5;


    @Scheduled(fixedDelay = 4000 )
    public void fillterInvalidLead() throws TMSDbException {
        // Get List To Fillter
        GetLeadToFillter getLeadToFillter = new GetLeadToFillter();
        getLeadToFillter.setInAttribute3(GET_LEAD_FILLTER_VALUE);
        DBResponse<List<LeadBasket>> leadBaskets  = leadFillterDao.getLeadUpdate(sessionId, getLeadToFillter);
>>>>>>> 1a2fcc0 ([Fear] Fillter Lead)

    private final ClBasketService clBasketService;

<<<<<<< HEAD
    private final ClFreshService clFreshService;

    public LeadFillter(BackListService backListService, CampaignService campaignService, ClBasketService clBasketService,ClFreshService clFreshService) {
        this.backListService = backListService;
        this.campaignService = campaignService;
        this.clBasketService = clBasketService;
        this.clFreshService = clFreshService;
    }

    private final String LOCAL_TIME_ZONE ="Asia/Ho_Chi_Minh";

    @Scheduled(cron = "0 43 16 * * *", zone = "Asia/Ho_Chi_Minh")
    public void fillterLead() throws TMSDbException {
        // Get Data To Process
        List<ClBasket> clBaskets = clBasketService.getListToProcess(sessionId);
        List<CfBlackList> blackLists = backListService.getBlackList(sessionId);
        LocalDateTime time = findMinimumCreateDate(clBaskets);
        List<ClBasket> leadsInRange = clBasketService.getLeadInTimeRange(clBaskets, sessionId, time);
        List<CampaignInf> campaignInfs = campaignService.getCampainInfs(EnumType.Campaign.CALLING_LIST.getType(),sessionId);

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

    private void filterInvalidPhonesAndBlackListAndListInRage(List<ClBasket> clBaskets, List<CfBlackList> blackLists, List<ClBasket> leadsInRange) {
        for (ClBasket basket : clBaskets){
            // Check phone num valid
            if (!PhoneHeper.checkPhoneValid(basket.getPhone())){
                basket.setStatus(EnumType.LeadStatus.TRASH.getType());
                basket.setComment(MessageConst.ERROL_INVALID_PHONE_MESSAGE);
=======
        //Begin fillter invalid phone
        for (LeadBasket basket : leadBaskets.getResult()){
            // Check phone num valid
            if (!PhoneHeper.checkPhoneValid(basket.getPhone())){
                basket.setStatus(TRASH_STATUS);
                basket.setComment(ERROL_INVALID_PHONE_MESSAGE);
>>>>>>> 1a2fcc0 ([Fear] Fillter Lead)
                continue;
            }
            // check in blacklist
            if(isInBlackList(basket,blackLists)){
<<<<<<< HEAD
                basket.setStatus(EnumType.LeadStatus.TRASH.getType());
                basket.setComment(MessageConst.ERROL_IN_BLACKLIST_MESSAGE);
                continue;
=======
                basket.setStatus(TRASH_STATUS);
                basket.setComment(ERROL_IN_BLACKLIST_MESSAGE);
>>>>>>> 1a2fcc0 ([Fear] Fillter Lead)
            }
            // Check Duplicate
            if(checkSelfDuplicate(basket,clBaskets)){
                basket.setStatus(EnumType.LeadStatus.TRASH.getType());
                basket.setComment(MessageConst.ERROL_DUPLICATE_MESSAGE);
                continue;
            }
            if (checkForDuplicates(basket,leadsInRange)){
                basket.setStatus(EnumType.LeadStatus.TRASH.getType());
                basket.setComment(MessageConst.ERROL_DUPLICATE_MESSAGE);
            }
            updateStatus(basket);
        }
<<<<<<< HEAD
    }

    private boolean checkSelfDuplicate(ClBasket basket, List<ClBasket> result) {
        LocalDateTime createDate1 = basket.getCreateDate();
        LocalDateTime minTime = createDate1.minusHours(24);
        for (ClBasket extbasket: result){
            if (extbasket == basket){
                continue;
            }
            if (!extbasket.equals(EnumType.LeadStatus.TRASH.getType())
=======
        // Check Duplicate
        // Get Min timestame -> get list < mincreatedate > mincreate - 24h
        LocalDateTime minTime = findMinimumCreateDate(leadBaskets.getResult());
        // Lấy danh sách lead có   mindTime - 24h < createdate < mintime
        GetLeadBasketsInTimeRange getLeadBasketsInTimeRange = new GetLeadBasketsInTimeRange();
        getLeadBasketsInTimeRange.setEndTime(DateHelper.toDateTime(minTime));
        getLeadBasketsInTimeRange.setStartTime(DateHelper.toDateTime(minTime.minus(24, ChronoUnit.HOURS)));
        DBResponse<List<LeadBasket>>  leadsInRange = leadFillterDao.getLeadBasketsInTimeRange(sessionId,getLeadBasketsInTimeRange);
        // Check Duplicate
        for (LeadBasket basket : leadBaskets.getResult()){
            if (basket.getStatus() == TRASH_STATUS){
                continue;
            }
            if(checkSelfDuplicate(basket,leadBaskets.getResult())){
                basket.setStatus(TRASH_STATUS);
                basket.setComment(ERROL_DUPLICATE_MESSAGE);
            }
            if (checkForDuplicates(basket,leadsInRange.getResult())){
                basket.setStatus(TRASH_STATUS);
                basket.setComment(ERROL_DUPLICATE_MESSAGE);
            }
        }
        // To-do Add Campain For Lead

        // Add attribute 3
        List<ClFresh> insertList = new ArrayList<>();
        for (LeadBasket basket : leadBaskets.getResult()){
            basket.setAttribute3(DONE_FILLTER_KEY_VALUE);
            insertList.add(LeadBasket.toInsLeadAfterFillter(basket));
        }
        // Insert Lead into cl_fresh
        // to-do: fix insert
        DBResponse<String> resultInsert = leadFillterDao.insLeadAfterFillter(sessionId,insertList);
        if (resultInsert.getErrorCode() != EnumType.DbStatusResp.SUCCESS.getStatus()) {
            throw new TMSDbException(resultInsert.getErrorMsg());
        }
        // Update Lead into cl_basket
        // to-do: fix update
        UpdLeadFillter updLeadFillter = new UpdLeadFillter();

        DBResponse<String> resultUpdate = leadFillterDao.updLeadFillter(sessionId,updLeadFillter);
    }

    private boolean checkSelfDuplicate(LeadBasket basket, List<LeadBasket> result) {
        LocalDateTime createDate1 = basket.getCreateDate();
        LocalDateTime minTime = createDate1.minusHours(24);
        for (LeadBasket extbasket: result){
            if (extbasket == basket){
                continue;
            }
            if (extbasket.getStatus() != TRASH_STATUS
>>>>>>> 1a2fcc0 ([Fear] Fillter Lead)
                    && extbasket.getPhone().equals(basket.getPhone())
                    && extbasket.getProdId().equals(basket.getProdId())
                    && extbasket.getCreateDate().isAfter(minTime)
                    && extbasket.getCreateDate().isBefore(createDate1)){
<<<<<<< HEAD
=======
                return true;
            }
        }
        return false;
    }

    private boolean checkForDuplicates(LeadBasket basket, List<LeadBasket> leadsInRange) {
        LocalDateTime createDate1 = basket.getCreateDate();
        LocalDateTime minTime = createDate1.minusHours(24);

        List<LeadBasket> mathcinglist2 = leadsInRange.stream().filter(basket2
                -> basket2.getStatus() != TRASH_STATUS
                && basket.getPhone().equals(basket2.getPhone())
                && basket.getProdId().equals(basket2.getProdId())
                && basket2.getCreateDate().isAfter(minTime)
                && basket2.getCreateDate().isBefore(createDate1)).collect(Collectors.toList());
        return !mathcinglist2.isEmpty();
    }

    private boolean isInBlackList(LeadBasket basket, DBResponse<List<BlackList>> blackLists) {
        List<BlackList> list = blackLists.getResult();
        // if list is empty return false
        if (list == null ||list.size() == 0){
            return false;
        }

        for (BlackList blackList : list){
            if (basket.getLeadId() == blackList.getLeadId()){
>>>>>>> 1a2fcc0 ([Fear] Fillter Lead)
                return true;
            }
        }
        return false;
    }

<<<<<<< HEAD
    private boolean checkForDuplicates(ClBasket basket, List<ClBasket> leadsInRange) {
        if (leadsInRange == null){
            return false;
        }
        LocalDateTime createDate1 = basket.getCreateDate();
        LocalDateTime minTime = createDate1.minusHours(24);

        List<ClBasket> matchingList = leadsInRange.stream()
                .filter(basket2 -> !basket2.getStatus().equals(EnumType.LeadStatus.TRASH.getType())
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
=======
    public static LocalDateTime findMinimumCreateDate(List<LeadBasket> leadBaskets) {
        if (leadBaskets == null || leadBaskets.isEmpty()) {
            return null; // Trả về null nếu danh sách rỗng hoặc null
        }
        int firstIndex = 0;
        LocalDateTime minCreateDate = leadBaskets.get(firstIndex).getCreateDate();

        for (LeadBasket leadBasket : leadBaskets) {
            LocalDateTime currentCreateDate = leadBasket.getCreateDate();
            if (currentCreateDate != null && currentCreateDate.isBefore(minCreateDate)) {
                minCreateDate = currentCreateDate;
            }
        }
        return minCreateDate;
>>>>>>> 1a2fcc0 ([Fear] Fillter Lead)
    }
}
