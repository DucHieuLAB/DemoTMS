package com.tms.api.service.impl;

import com.tms.api.consts.EnumType;
import com.tms.api.exception.TMSDbException;
import com.tms.api.exception.TMSException;
import com.tms.api.helper.ClBasketConverter;
import com.tms.api.helper.DateHelper;
import com.tms.api.helper.Helper;
import com.tms.api.service.BaseService;
import com.tms.commons.DBResponse;
import com.tms.dao.ClBasketDao;
import com.tms.dto.request.clBasket.GetLeadBasketsInTimeRange;
import com.tms.dto.request.clBasket.GetLeadToFillter;
import com.tms.dto.request.clBasket.UpdClBasket;
import com.tms.dto.request.clBasket.UpdClBaskets;
import com.tms.dto.response.ClBasket;
import com.tms.api.service.ClBasketService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;


@Service
public class ClBasketServiceImpl extends BaseService implements ClBasketService {
    private final ClBasketDao clBasketDao;

    public ClBasketServiceImpl(ClBasketDao fillterLeadImpl) {
        this.clBasketDao = fillterLeadImpl;
    }

    @Override
    public List<ClBasket> getListToFillter(GetLeadToFillter getLeadToFillter) throws TMSException{
        DBResponse<List<ClBasket>> listDBResponse = clBasketDao.getLeadUpdate(sessionId,getLeadToFillter);
        if(listDBResponse.getErrorCode() != EnumType.DbStatusResp.SUCCESS.getStatus()){
            throw new TMSDbException(listDBResponse.getErrorMsg());
        }
        return listDBResponse.getResult();
    }

    @Override
    public List<ClBasket> getLeadInTimeRange(List<ClBasket> clBaskets, String sessionId, LocalDateTime time) throws TMSDbException {
        GetLeadBasketsInTimeRange getLeadBasketsInTimeRange = new GetLeadBasketsInTimeRange();
        getLeadBasketsInTimeRange.setEndTime(DateHelper.toDateTime(time));
        getLeadBasketsInTimeRange.setStartTime(DateHelper.toDateTime(time.minus(24, ChronoUnit.HOURS)));
        DBResponse<List<ClBasket>>  leadsInRange = clBasketDao.getLeadBasketsInTimeRange(sessionId,getLeadBasketsInTimeRange);
        // Check Exception
        if (leadsInRange == null || leadsInRange.getResult().size() == 0){
            return null;
        }
        if(leadsInRange.getErrorCode() != EnumType.DbStatusResp.SUCCESS.getStatus()){
            throw new TMSDbException(leadsInRange.getErrorMsg());
        }
        return leadsInRange.getResult();
    }

    @Override
    public List<ClBasket> getListToProcess(String sessionId) throws TMSDbException {
        GetLeadToFillter getLeadToFillter = new GetLeadToFillter();
        getLeadToFillter.setInAttribute3(EnumType.Filltter.GET_LEAD_FILLTER_VALUE.getValue());
        DBResponse<List<ClBasket>> leadBaskets  = clBasketDao.getLeadUpdate(sessionId, getLeadToFillter);
        if (leadBaskets ==null || leadBaskets.getResult().size() == 0){
            throw new TMSDbException("No lead baskets to process.");
        }
        if(leadBaskets.getErrorCode() != EnumType.DbStatusResp.SUCCESS.getStatus()){
            throw new TMSDbException(leadBaskets.getErrorMsg());
        }
        return leadBaskets.getResult();
    }

    @Override
    public void updateClBasket(List<ClBasket> clBaskets, String sessionId, String TimeZone) throws TMSDbException {
        List<UpdClBasket> updClBaskets = ClBasketConverter.convertToUpdClBasketList(clBaskets);
        String json = Helper.convertListToJson(updClBaskets);
        UpdClBaskets baskets = new UpdClBaskets();
        baskets.setJson(json);
        if (TimeZone.length() == 0 || TimeZone.isEmpty()) {
            baskets.setTimeZone(TimeZone);
        }
        DBResponse<String> resultUpdate = clBasketDao.updClBasketAfterFillter(sessionId, baskets);
        if (resultUpdate.getErrorCode() != EnumType.DbStatusResp.SUCCESS.getStatus()) {
            throw new TMSDbException(resultUpdate.getErrorMsg());
        }
    }
}

