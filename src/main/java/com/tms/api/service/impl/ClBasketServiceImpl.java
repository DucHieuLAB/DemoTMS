package com.tms.api.service.impl;

import com.tms.api.commons.ApiMessageError;
import com.tms.api.consts.EnumType;
import com.tms.api.consts.MessageConst;
import com.tms.api.exception.ErrorMessages;
import com.tms.api.exception.TMSDbException;
import com.tms.api.exception.TMSEntityNotFoundException;
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
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class ClBasketServiceImpl extends BaseService implements ClBasketService {
    private final ClBasketDao clBasketDao;

    public ClBasketServiceImpl(ClBasketDao fillterLeadImpl) {
        this.clBasketDao = fillterLeadImpl;
    }

    @Override
    public List<ClBasket> getListToFillter(GetLeadToFillter getLeadToFillter) throws TMSException {
        DBResponse<List<ClBasket>> listDbResponse = clBasketDao.getLeadUpdate(sessionId, getLeadToFillter);
        if (listDbResponse.getErrorCode() != EnumType.DbStatusResp.SUCCESS.getStatus()) {
            throw new TMSDbException(listDbResponse.getErrorMsg());
        }
        if (CollectionUtils.isEmpty(listDbResponse.getResult())) {
            String errorMessage = MessageConst.NOT_FOUND_WITH_OBJECT_PARAMS + Helper.toJson(getLeadToFillter);
            throw new TMSEntityNotFoundException(ErrorMessages.NOT_FOUND, new ApiMessageError(errorMessage));
        }
        return listDbResponse.getResult();
    }

    @Override
    public List<ClBasket> getLeadInTimeRange(LocalDateTime startTime, LocalDateTime endTime) throws TMSException {
        GetLeadBasketsInTimeRange getLeadBasketsInTimeRange = new GetLeadBasketsInTimeRange();
        getLeadBasketsInTimeRange.setEndTime(DateHelper.toDateTime(endTime));
        getLeadBasketsInTimeRange.setStartTime(DateHelper.toDateTime(startTime));
        DBResponse<List<ClBasket>> leadsInRange = clBasketDao.getLeadBasketsInTimeRange(sessionId, getLeadBasketsInTimeRange);
        if (leadsInRange.getErrorCode() != EnumType.DbStatusResp.SUCCESS.getStatus()) {
            throw new TMSDbException(leadsInRange.getErrorMsg());
        }
        if (CollectionUtils.isEmpty(leadsInRange.getResult())) {
            String errorMessage = MessageConst.NOT_FOUND_WITH_OBJECT_PARAMS + Helper.toJson(getLeadBasketsInTimeRange);
            throw new TMSEntityNotFoundException(ErrorMessages.NOT_FOUND, new ApiMessageError(errorMessage));
        }
        return leadsInRange.getResult();
    }

    @Override
    public List<ClBasket> getListToFilterProcess(String sessionId) throws TMSException {
        GetLeadToFillter getLeadToFillter = new GetLeadToFillter();
        getLeadToFillter.setInAttribute3(EnumType.Filltter.GET_LEAD_FILLTER_VALUE.getValue());
        DBResponse<List<ClBasket>> leadBaskets = clBasketDao.getLeadUpdate(sessionId, getLeadToFillter);
        if (leadBaskets.getErrorCode() != EnumType.DbStatusResp.SUCCESS.getStatus()) {
            throw new TMSDbException(leadBaskets.getErrorMsg());
        }
        if (CollectionUtils.isEmpty(leadBaskets.getResult())) {
            String errorMessage = MessageConst.NOT_FOUND_WITH_OBJECT_PARAMS + Helper.toJson(getLeadToFillter);
            throw new TMSEntityNotFoundException(ErrorMessages.NOT_FOUND, new ApiMessageError(errorMessage));
        }
        return leadBaskets.getResult();
    }

    @Override
    public void updateClBasket(List<ClBasket> clBaskets, String sessionId, String timeZone) throws TMSException {
        List<UpdClBasket> updClBaskets = ClBasketConverter.convertToUpdClBasketList(clBaskets);
        String json = Helper.convertListToJson(updClBaskets);
        UpdClBaskets baskets = new UpdClBaskets();
        baskets.setJson(json);
        if (timeZone == null || timeZone.isEmpty()) {
            baskets.setTimeZone(timeZone);
        }
        DBResponse<String> resultUpdate = clBasketDao.updClBasketAfterFillter(sessionId, baskets);
        if (resultUpdate.getErrorCode() != EnumType.DbStatusResp.SUCCESS.getStatus()) {
            throw new TMSDbException(resultUpdate.getErrorMsg());
        }
    }

}

