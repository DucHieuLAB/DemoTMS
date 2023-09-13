package com.tms.api.scheduled;

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
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.temporal.ChronoUnit;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class LeadFillter extends BaseService {

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

        // Get Black List
        GetBlackList getBlackList = new GetBlackList();
        DBResponse<List<BlackList>> blackLists = blackListDao.getBlackList(sessionId,getBlackList);

        //Begin fillter invalid phone
        for (LeadBasket basket : leadBaskets.getResult()){
            // Check phone num valid
            if (!PhoneHeper.checkPhoneValid(basket.getPhone())){
                basket.setStatus(TRASH_STATUS);
                basket.setComment(ERROL_INVALID_PHONE_MESSAGE);
                continue;
            }
            // check in blacklist
            if(isInBlackList(basket,blackLists)){
                basket.setStatus(TRASH_STATUS);
                basket.setComment(ERROL_IN_BLACKLIST_MESSAGE);
            }
        }
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
                    && extbasket.getPhone().equals(basket.getPhone())
                    && extbasket.getProdId().equals(basket.getProdId())
                    && extbasket.getCreateDate().isAfter(minTime)
                    && extbasket.getCreateDate().isBefore(createDate1)){
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
                return true;
            }
        }
        return false;
    }

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
    }
}
