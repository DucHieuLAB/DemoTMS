package com.tms.api.service.impl;


import com.tms.api.commons.ApiMessageError;
import com.tms.api.consts.EnumType;
import com.tms.api.consts.MessageConst;
import com.tms.api.consts.EnumType.DbStatusResp;
import com.tms.api.exception.ErrorMessages;
import com.tms.api.exception.TMSDbException;
import com.tms.api.exception.TMSEntityNotFoundException;
import com.tms.api.exception.TMSException;
import com.tms.api.exception.TMSInvalidInputException;
import com.tms.api.helper.Helper;
import com.tms.api.service.BaseService;
import com.tms.api.service.ClFreshService;
import com.tms.commons.DBResponse;
import com.tms.dao.ClCallbackDao;
import com.tms.dao.ClFreshDao;
import com.tms.dto.request.ClFreshGetLead.GetLeadById;
import com.tms.dto.request.ClFreshGetLead.GetLeadfor;
import com.tms.dto.request.ClFreshGetLead.SetLeadFresh;
import com.tms.dto.request.ClFreshGetLead.SetLeadStatus;
import com.tms.dto.request.ClFreshGetLead.SoSaleOderInsert;
import com.tms.dto.request.clCallback.DelClCallback;
import com.tms.dto.request.clCallback.InsClCallback;
import com.tms.dto.request.clFresh.*;
import com.tms.dto.response.GetLeadForAgentDto;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ClFreshServiceImpl extends BaseService implements ClFreshService {

    private final ClFreshDao clFreshDao;

    private final ClCallbackDao clCallbackDao;

    public ClFreshServiceImpl(ClFreshDao clFreshDao, ClCallbackDao clCallbackDao) {
        this.clFreshDao = clFreshDao;
        this.clCallbackDao = clCallbackDao;
    }

    @Override
    public void insertClFresh(List<InsClFresh> clFreshes, String sessionId) throws TMSDbException {
        StringBuilder values = new StringBuilder();
        values.append(" VALUES ");
        for (InsClFresh insClFreshs : clFreshes) {
            values.append(insClFreshs.toString());
            //check if insClFreshs is last element
            if (insClFreshs == clFreshes.get(clFreshes.size() - 1)) {
                continue;
            }
            values.append(",");
        }
        logger.info(values.toString());
        InsClFreshsQuery insClFreshsQuery = new InsClFreshsQuery(values.toString());
        DBResponse<String> insClFreshDbResponse = clFreshDao.insClFresh(sessionId, insClFreshsQuery);
        if (insClFreshDbResponse.getErrorCode() != EnumType.DbStatusResp.SUCCESS.getStatus()) {
            throw new TMSDbException(insClFreshDbResponse.getErrorMsg());
        }
    }

    @Override
    public boolean updClFreshAfterValidSaleOrder(UpdClFresh updClFresh) throws TMSDbException {
        updClFresh.setModifyBy(curUserId);
        return updClFresh(updClFresh);
    }

    public boolean updClFresh(UpdClFresh updClFresh) throws TMSDbException {
        List<UpdClFresh> clFreshes = new ArrayList<>();
        clFreshes.add(updClFresh);
        String json = Helper.convertListToJson(clFreshes);
        UpdClFreshs updClFreshs = new UpdClFreshs();
        updClFreshs.setJson(json);
        DBResponse<String> insScheduleDbResp = clFreshDao.updClFresh(sessionId, updClFreshs);
        if (insScheduleDbResp.getErrorCode() != EnumType.DbStatusResp.SUCCESS.getStatus()) {
            throw new TMSDbException(insScheduleDbResp.getErrorMsg());
        }
        return true;
    }

    @Override
    public List<GetLeadForAgentDto> getLeadForAgent(GetLeadfor getLeadfor) throws TMSException {
        DBResponse<List<GetLeadForAgentDto>> result;

        result = clFreshDao.getLeadForHold(sessionId, getLeadfor);
        if (!CollectionUtils.isEmpty(result.getResult())) {
            return result.getResult();
        }

        result = clFreshDao.getLeadForAgentUrgent(sessionId, getLeadfor);
        if (!CollectionUtils.isEmpty(result.getResult())) {
            return handleLead(result, getLeadfor);
        }

        result = clFreshDao.getLeadforagentCallback(sessionId, getLeadfor);
        if (!CollectionUtils.isEmpty(result.getResult())) {
            return handleLead(result, getLeadfor);
        }

        result = clFreshDao.getLeadForAgentNew(sessionId, getLeadfor);
        if (!CollectionUtils.isEmpty(result.getResult())) {
            return handleLead(result, getLeadfor);
        }

        result = clFreshDao.getLeadForUncall(sessionId, getLeadfor);
        if (!CollectionUtils.isEmpty(result.getResult())) {
            return handleLead(result, getLeadfor);
        }

        throw new TMSEntityNotFoundException(ErrorMessages.NOT_FOUND);
    }

    private List<GetLeadForAgentDto> handleLead(DBResponse<List<GetLeadForAgentDto>> result, GetLeadfor getLeadfor) throws TMSException {
        if (result.getErrorCode() != DbStatusResp.SUCCESS.getStatus()) {
            throw new TMSDbException(result.getErrorMsg());
        }
        SetLeadFresh setLeadFresh = new SetLeadFresh();
        setLeadFresh.setAssigned(getLeadfor.getAgentId());
        setLeadFresh.setAgentHold(getLeadfor.getAgentId());
        setLeadFresh.setDayCall(result.getResult().get(0).getDayCall()+1);
        setLeadFresh.setTotalCall(result.getResult().get(0).getTotalCall()+1);
        setLeadFresh.setLeadId(result.getResult().get(0).getLeadId());

        DBResponse<String> setLead = clFreshDao.setlead(sessionId, setLeadFresh);
        if (setLead.getErrorCode() != DbStatusResp.SUCCESS.getStatus()) {
            throw new TMSDbException(setLead.getErrorMsg());
        }

        return result.getResult();
    }

    @Override
    public boolean updLead(int id, SetLeadStatus setLeadStatus) throws TMSException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        GetLeadById getLeadById = new GetLeadById(id);
        DBResponse<List<GetLeadForAgentDto>> result = clFreshDao.getLeadById(sessionId, getLeadById);
        if (CollectionUtils.isEmpty(result.getResult())) {
            throw new TMSEntityNotFoundException(ErrorMessages.NOT_FOUND);
        }

        validateStatus(setLeadStatus);

        if (setLeadStatus.getSetLeadFresh().getLeadStatus() == EnumType.LeadStatus.TRASH.getStatus() || setLeadStatus.getSetLeadFresh().getLeadStatus() == EnumType.LeadStatus.REJECTED.getStatus()) {
            handleTrashAndRejected(setLeadStatus);
        }

        if (setLeadStatus.getSetLeadFresh().getLeadStatus() == EnumType.LeadStatus.BUSY.getStatus() || setLeadStatus.getSetLeadFresh().getLeadStatus() == EnumType.LeadStatus.NOANSWER.getStatus() || setLeadStatus.getSetLeadFresh().getLeadStatus() == EnumType.LeadStatus.UNREACHABLE.getStatus()) {
            handleBusyNoAnswerUnreachable(setLeadStatus);
        }

        if (setLeadStatus.getSetLeadFresh().getLeadStatus() == EnumType.LeadStatus.CALLBACKPOTPROSPECT.getStatus() || setLeadStatus.getSetLeadFresh().getLeadStatus() == EnumType.LeadStatus.CALLBACKCONSULTING.getStatus() || setLeadStatus.getSetLeadFresh().getLeadStatus() == EnumType.LeadStatus.CALLBACKPOTENTIAL.getStatus()) {
            handleCallBack(setLeadStatus);
        }

        if (setLeadStatus.getSetLeadFresh().getLeadStatus() == EnumType.LeadStatus.APPROVED.getStatus()) {
            handleApproved(setLeadStatus);
        }

        return true;
    }

    @Override
    public boolean updDayCallAfter24Hour() throws TMSException {
        DBResponse<String> dbResponse  = clFreshDao.updClFreshDayCallAfter24Hour(sessionId,new UpdDayCallAfter24Hour());
        if (dbResponse.getErrorCode() != DbStatusResp.SUCCESS.getStatus()) {
            throw new TMSDbException(dbResponse.getErrorMsg());
        }
        return true;
    }

    private void validateStatus(SetLeadStatus setLeadStatus) throws TMSInvalidInputException {
        int[] validStatuses = {EnumType.LeadStatus.TRASH.getStatus(),
                EnumType.LeadStatus.REJECTED.getStatus(),
                EnumType.LeadStatus.BUSY.getStatus(),
                EnumType.LeadStatus.NOANSWER.getStatus(),
                EnumType.LeadStatus.UNREACHABLE.getStatus(),
                EnumType.LeadStatus.CALLBACKPOTPROSPECT.getStatus(),
                EnumType.LeadStatus.CALLBACKCONSULTING.getStatus(),
                EnumType.LeadStatus.CALLBACKPOTENTIAL.getStatus(),
                EnumType.LeadStatus.APPROVED.getStatus()};
        if (Arrays.stream(validStatuses).noneMatch(status -> status == setLeadStatus.getSetLeadFresh().getLeadStatus())) {
            String errorMessage = MessageConst.ERROR_MESSAGE_INFORMATION + Helper.toJson("status :"+setLeadStatus.getSetLeadFresh().getLeadStatus());
            throw new TMSInvalidInputException(ErrorMessages.INVALID_VALUE,new ApiMessageError(errorMessage));
        }
    }

    private void handleTrashAndRejected(SetLeadStatus setLeadStatus) throws TMSException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        SetLeadFresh setLeadFresh = new SetLeadFresh();
        PropertyUtils.copyProperties(setLeadFresh, setLeadStatus.getSetLeadFresh());

        if (setLeadStatus.getSetLeadFresh().getFcrReason().isEmpty() || setLeadStatus.getSetLeadFresh().getFcrReason() ==null ) {
            String errorMessage = MessageConst.ERROR_MESSAGE_INFORMATION_NULL + Helper.toJson(" Reason:"+setLeadStatus.getSetLeadFresh().getFcrReason());
            throw new TMSInvalidInputException(ErrorMessages.INVALID_VALUE,new ApiMessageError(errorMessage));
        }

        DBResponse<String> setLead = clFreshDao.setlead(sessionId, setLeadFresh);

        if (setLead.getErrorCode() != DbStatusResp.SUCCESS.getStatus()) {
            throw new TMSDbException(setLead.getErrorMsg());
        }
    }

    private void handleBusyNoAnswerUnreachable(SetLeadStatus setLeadStatus) throws TMSException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        SetLeadFresh setLeadFresh = new SetLeadFresh();
        PropertyUtils.copyProperties(setLeadFresh, setLeadStatus.getSetLeadFresh());
        DBResponse<String> setLead = clFreshDao.setlead(sessionId, setLeadFresh);

        if (setLead.getErrorCode() != DbStatusResp.SUCCESS.getStatus()) {
            throw new TMSDbException(setLead.getErrorMsg());
        }
    }

    private void handleCallBack(SetLeadStatus setLeadStatus) throws TMSException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        SetLeadFresh setLeadFresh = new SetLeadFresh();
        PropertyUtils.copyProperties(setLeadFresh, setLeadStatus.getSetLeadFresh());
        if (setLeadStatus.getSetLeadFresh().getFcrReason().isEmpty()) {
             String errorMessage = MessageConst.ERROR_MESSAGE_INFORMATION_NULL + Helper.toJson(" Reason:"+setLeadStatus.getSetLeadFresh().getFcrReason());
            throw new TMSInvalidInputException(ErrorMessages.INVALID_VALUE,new ApiMessageError(errorMessage));
        }

        if (setLeadStatus.getInsClCallback().getRequestTime().isEmpty()) {
             String errorMessage = MessageConst.ERROR_MESSAGE_INFORMATION_NULL + Helper.toJson(" RequestTime:"+setLeadStatus.getInsClCallback().getRequestTime());
            throw new TMSInvalidInputException(ErrorMessages.INVALID_VALUE,new ApiMessageError(errorMessage));
        }
        InsClCallback insClCallback = new InsClCallback();
        PropertyUtils.copyProperties(insClCallback, setLeadStatus.getInsClCallback());
        DelClCallback delClCallback = new DelClCallback(setLeadStatus.getInsClCallback().getLeadId());

        DBResponse<String> setLead = clFreshDao.setlead(sessionId, setLeadFresh);
        if (setLead.getErrorCode() != DbStatusResp.SUCCESS.getStatus()) {
            throw new TMSDbException(setLead.getErrorMsg());
        }
        DBResponse<String> delcalback = clCallbackDao.delClCallback(sessionId, delClCallback);
        if (delcalback.getErrorCode() != DbStatusResp.SUCCESS.getStatus()) {
            throw new TMSDbException(delcalback.getErrorMsg());
        }
        DBResponse<String> setcalback = clCallbackDao.insClCallback(sessionId, insClCallback);

        if (setcalback.getErrorCode() != DbStatusResp.SUCCESS.getStatus()) {
            throw new TMSDbException(setcalback.getErrorMsg());
        }
    }

    private void handleApproved(SetLeadStatus setLeadStatus) throws TMSException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        if (setLeadStatus.getSetLeadFresh().getAddress().isEmpty() || setLeadStatus.getSetLeadFresh().getAddress() == null) {
           String errorMessage = MessageConst.ERROR_MESSAGE_INFORMATION_NULL + Helper.toJson(" address:"+setLeadStatus.getSetLeadFresh().getAddress());
            throw new TMSInvalidInputException(ErrorMessages.INVALID_VALUE,new ApiMessageError(errorMessage));
        }

        if (setLeadStatus.getSoSaleOderInsert().getLeadPhone().isEmpty() || setLeadStatus.getSoSaleOderInsert().getLeadPhone() == null) {
             String errorMessage = MessageConst.ERROR_MESSAGE_INFORMATION_NULL + Helper.toJson(" Phone:"+setLeadStatus.getSoSaleOderInsert().getLeadPhone());
            throw new TMSInvalidInputException(ErrorMessages.INVALID_VALUE,new ApiMessageError(errorMessage));
        }

        if (setLeadStatus.getSetLeadFresh().getProdId() == null) {
             String errorMessage = MessageConst.ERROR_MESSAGE_INFORMATION_NULL + Helper.toJson(" ProdID:"+setLeadStatus.getSetLeadFresh().getProdId() );
            throw new TMSInvalidInputException(ErrorMessages.INVALID_VALUE,new ApiMessageError(errorMessage));
        }
        if (setLeadStatus.getSoSaleOderInsert().getPaymentMethod() == null) {
            String errorMessage = MessageConst.ERROR_MESSAGE_INFORMATION_NULL + Helper.toJson(" PaymentMethod:"+setLeadStatus.getSoSaleOderInsert().getPaymentMethod());
            throw new TMSInvalidInputException(ErrorMessages.INVALID_VALUE,new ApiMessageError(errorMessage));
        }

        SetLeadFresh setLeadFresh = new SetLeadFresh();
        PropertyUtils.copyProperties(setLeadFresh, setLeadStatus.getSetLeadFresh());

        SoSaleOderInsert soSaleOderInsert = new SoSaleOderInsert();
        PropertyUtils.copyProperties(soSaleOderInsert, setLeadStatus.getSoSaleOderInsert());
        soSaleOderInsert.setStatus(EnumType.SaleOrder.NEW.getStatus());
        DBResponse<String> insSo = clFreshDao.insSoSaleOder(sessionId, soSaleOderInsert);
        if (insSo.getErrorCode() != DbStatusResp.SUCCESS.getStatus()) {
            throw new TMSDbException(insSo.getErrorMsg());
        }

        DBResponse<String> approve = clFreshDao.setlead(sessionId, setLeadFresh);
        if (approve.getErrorCode() != DbStatusResp.SUCCESS.getStatus() || insSo.getErrorCode() != DbStatusResp.SUCCESS.getStatus()) {
            throw new TMSDbException(approve.getErrorMsg());
        }
    }


}
