package com.tms.api.service.impl;


import com.tms.api.consts.EnumType;
import com.tms.api.consts.EnumType.DbStatusResp;
import com.tms.api.exception.ErrorMessages;
import com.tms.api.exception.TMSDbException;
import com.tms.api.exception.TMSEntityNotFoundException;
import com.tms.api.exception.TMSException;
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
import com.tms.dto.request.clFresh.InsClFresh;
import com.tms.dto.request.clFresh.InsClFreshsQuery;
import com.tms.dto.request.clFresh.UpdClFresh;
import com.tms.dto.request.clFresh.UpdClFreshs;
import com.tms.dto.response.GetLeadForAgentDto;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;

@Service
public class ClFreshServiceImpl extends BaseService implements ClFreshService {

    private final ClFreshDao clFreshDao;

    private final ClCallbackDao clCallbackDao;



    public ClFreshServiceImpl(ClFreshDao clFreshDao,ClCallbackDao clCallbackDao) {
        this.clFreshDao = clFreshDao;
        this.clCallbackDao=clCallbackDao;
    }

    @Override
    public void insertClFresh(List<InsClFresh> clFreshes, String sessionId) throws TMSDbException {
        String values = "VALUES";
        for (InsClFresh insClFreshs : clFreshes) {
            values = values + insClFreshs.toString();
            //check if insClFreshs is last element
            if (insClFreshs == clFreshes.get(clFreshes.size() - 1)) {
                continue;
            }
            values = values + ",";
        }
        InsClFreshsQuery insClFreshsQuery = new InsClFreshsQuery(values);
        DBResponse<String> insClFreshDBResponse = clFreshDao.insClFresh(sessionId, insClFreshsQuery);
        if (insClFreshDBResponse == null) {
            throw new TMSDbException("Can't add new Cl Fresh");
        }
        if (insClFreshDBResponse.getErrorCode() != EnumType.DbStatusResp.SUCCESS.getStatus()) {
            throw new TMSDbException(insClFreshDBResponse.getErrorMsg());
        }
    }

    @Override
    public boolean updClFreshAfterValidSaleOrder(UpdClFresh updClFresh) throws TMSDbException {
        updClFresh.setModifyBy(curUserId);
        return updClFresh(sessionId,updClFresh);
    }

    public boolean updClFresh(String sessionId,UpdClFresh updClFresh) throws TMSDbException{
        List<UpdClFresh> clFreshes = new ArrayList<>();
        clFreshes.add(updClFresh);
        String json = Helper.convertListToJson(clFreshes);
        UpdClFreshs updClFreshs =  new UpdClFreshs();
        updClFreshs.setJson(json);
        DBResponse<String> insScheduleDbResp = clFreshDao.updClFresh(sessionId,updClFreshs);
        if (insScheduleDbResp.getErrorCode() != EnumType.DbStatusResp.SUCCESS.getStatus()) {
            throw new TMSDbException(insScheduleDbResp.getErrorMsg());
        }
        return true;
    }
     @Override
    public List<GetLeadForAgentDto> getLeadForAgent(GetLeadfor getLeadfor) throws TMSException {
        DBResponse<List<GetLeadForAgentDto>> result = null;
    
        result = clFreshDao.getLeadForHold(sessionId, getLeadfor);
        if (!CollectionUtils.isEmpty(result.getResult())) {
            return result.getResult();
        }
    
        result = clFreshDao.getLeadForAgentUrgent(sessionId, getLeadfor);
        if (!CollectionUtils.isEmpty(result.getResult())) {
            return handleLead(result,getLeadfor);
        }
    
        result = clFreshDao.getLeadforagentCallback(sessionId, getLeadfor);
        if (!CollectionUtils.isEmpty(result.getResult())) {
            return handleLead(result,getLeadfor);
        }
    
        result = clFreshDao.getLeadForAgentNew(sessionId, getLeadfor);
        if (!CollectionUtils.isEmpty(result.getResult())) {
            return handleLead(result,getLeadfor);
        }
    
        result = clFreshDao.getLeadForUncall(sessionId, getLeadfor);
        if (!CollectionUtils.isEmpty(result.getResult())) {
            return handleLead(result,getLeadfor);
        }
    
        throw new TMSEntityNotFoundException(ErrorMessages.NOT_FOUND);
    }
    
    private List<GetLeadForAgentDto> handleLead(DBResponse<List<GetLeadForAgentDto>> result , GetLeadfor getLeadfor) throws TMSException {
        SetLeadFresh setLeadFresh = new SetLeadFresh();
        setLeadFresh.setAssigned(getLeadfor.getAgentId());
        setLeadFresh.setAgentHold(getLeadfor.getAgentId());
        setLeadFresh.setLeadId(result.getResult().get(0).getLeadId());
    
        DBResponse<String> setLead = clFreshDao.setlead(sessionId, setLeadFresh);
        if (setLead.getErrorCode() != DbStatusResp.SUCCESS.getStatus()) {
            throw new TMSDbException(setLead.getErrorMsg());
        }
    
        return result.getResult();
    }
    @Override 
    public boolean updLead(int id,SetLeadStatus setLeadStatus) throws TMSException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
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
    
    private void validateStatus(SetLeadStatus setLeadStatus) {
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
          throw new InputMismatchException("Not added to status yet");
      }
  }
    
  private void handleTrashAndRejected(SetLeadStatus setLeadStatus) throws TMSException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
    SetLeadFresh setLeadFresh = new SetLeadFresh();
    PropertyUtils.copyProperties(setLeadFresh, setLeadStatus.getSetLeadFresh());
    
    if (setLeadStatus.getSetLeadFresh().getFcrReason().isEmpty()) {
        throw new InputMismatchException("No reason has been added for the above status");
    }
    
    DBResponse<String> setLead = clFreshDao.setlead(sessionId, setLeadFresh);
    
    if (setLead.getErrorCode() != DbStatusResp.SUCCESS.getStatus()) {
        throw new TMSDbException(setLead.getErrorMsg());
    }
}

     private void handleBusyNoAnswerUnreachable(SetLeadStatus setLeadStatus) throws TMSException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
     SetLeadFresh setLeadFresh =new  SetLeadFresh();  
     PropertyUtils.copyProperties(setLeadFresh,setLeadStatus.getSetLeadFresh());
     DBResponse<String> setLead = clFreshDao.setlead(sessionId, setLeadFresh);
  
      if (setLead.getErrorCode() != DbStatusResp.SUCCESS.getStatus()) {
          throw new TMSDbException(setLead.getErrorMsg());
      }
  }

  private void handleCallBack(SetLeadStatus setLeadStatus) throws TMSException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
     SetLeadFresh setLeadFresh =new  SetLeadFresh();  
     PropertyUtils.copyProperties(setLeadFresh,setLeadStatus.getSetLeadFresh());
      if (setLeadStatus.getSetLeadFresh().getFcrReason().isEmpty()) {
          throw new InputMismatchException("No reason has been added for the above status");
      }
  
      if (setLeadStatus.getInsClCallback().getRequestTime().isEmpty()) {
          throw new InputMismatchException("Callback time for status has not been added");
      }
      InsClCallback insClCallback = new InsClCallback();
      PropertyUtils.copyProperties(insClCallback,setLeadStatus.getInsClCallback());
      DelClCallback delClCallback =new DelClCallback(setLeadStatus.getInsClCallback().getLeadId());
     
          DBResponse<String> setLead = clFreshDao.setlead(sessionId, setLeadFresh);
       if (setLead.getErrorCode() != DbStatusResp.SUCCESS.getStatus()) {
          throw new TMSDbException(setLead.getErrorMsg());
      }
          DBResponse<String> delcalback = clCallbackDao.delClCallback(sessionId, delClCallback);
                if (delcalback.getErrorCode() != DbStatusResp.SUCCESS.getStatus()) {
          throw new TMSDbException(delcalback.getErrorMsg());
      }
          DBResponse<String> setcalback = clCallbackDao.InsClCallback(sessionId, insClCallback);
  
          if (setcalback.getErrorCode() != DbStatusResp.SUCCESS.getStatus()) {
              throw new TMSDbException(setcalback.getErrorMsg());
          }
      }

      private void handleApproved(SetLeadStatus setLeadStatus) throws TMSException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        if (setLeadStatus.getSetLeadFresh().getAddress().isEmpty() || setLeadStatus.getSetLeadFresh().getAddress() == null) {
            throw new InputMismatchException("No address has been entered for the status");
        }
    
        if (setLeadStatus.getSoSaleOderInsert().getLeadPhone().isEmpty() || setLeadStatus.getSoSaleOderInsert().getLeadPhone() == null) {
            throw new InputMismatchException("No phone has been entered for the status");
        }
    
        if (setLeadStatus.getSetLeadFresh().getProdId() == null) {
            throw new InputMismatchException("No product has been entered for the status");
        }
        if (setLeadStatus.getSoSaleOderInsert().getPaymentMethod() == null) {
            throw new InputMismatchException("No payment method has been entered for the status");
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
