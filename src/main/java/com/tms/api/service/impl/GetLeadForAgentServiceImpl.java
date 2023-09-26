package com.tms.api.service.impl;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.tms.api.consts.EnumType;
import com.tms.api.consts.EnumType.DbStatusResp;
import com.tms.api.exception.ErrorMessages;
import com.tms.api.exception.TMSDbException;
import com.tms.api.exception.TMSEntityNotFoundException;
import com.tms.api.exception.TMSException;
import com.tms.api.service.BaseService;
import com.tms.api.service.GetLeadForAgentService;
import com.tms.commons.DBResponse;
import com.tms.dao.ClFreshGetLeadForagen;
import com.tms.dto.request.ClFreshGetLead.GetIdCallback;
import com.tms.dto.request.ClFreshGetLead.GetLeadfor;
import com.tms.dto.request.ClFreshGetLead.SetLeadFresh;
import com.tms.dto.request.ClFreshGetLead.SetLeadStatus;
import com.tms.dto.request.ClFreshGetLead.SetLeadStatusCallback;
import com.tms.dto.request.ClFreshGetLead.SoSaleOderInsert;
import com.tms.dto.request.ClFreshGetLead.UpdateforagentHold;
import com.tms.dto.response.GetLeadForAgentDto;
@Service
public class GetLeadForAgentServiceImpl extends BaseService implements GetLeadForAgentService {
    private final ClFreshGetLeadForagen clFreshGetLeadForagen;
    
    public GetLeadForAgentServiceImpl(ClFreshGetLeadForagen clFreshGetLeadForagen){
        this.clFreshGetLeadForagen=clFreshGetLeadForagen;
    }
    @Override 
    public List<GetLeadForAgentDto> getLeadforagent(GetLeadfor getLeadfor) throws TMSException { ///false name method
         DBResponse<List<GetLeadForAgentDto>>  result= clFreshGetLeadForagen.getLeadForHold(sessionId, getLeadfor);
         UpdateforagentHold updateforagentHold =new UpdateforagentHold();
         updateforagentHold.setAgentId(getLeadfor.getAgentId());
          if(!CollectionUtils.isEmpty(result.getResult())){
           return result.getResult();
          }
             result = clFreshGetLeadForagen.getLeadForAgentUrgent(sessionId, getLeadfor);
            if(!CollectionUtils.isEmpty(result.getResult())){
              updateforagentHold.setLeadID(result.getResult().get(0).getLeadId());
              clFreshGetLeadForagen.updScheduleUpdate(sessionId, updateforagentHold);
                return result.getResult();
            }
           result= clFreshGetLeadForagen.getLeadforagentCallback(sessionId, getLeadfor);
           if(!CollectionUtils.isEmpty(result.getResult())){
             updateforagentHold.setLeadID(result.getResult().get(0).getLeadId());
            updateforagentHold.setLeadID(result.getResult().get(0).getLeadId());
             clFreshGetLeadForagen.updScheduleUpdate(sessionId, updateforagentHold);
            return result.getResult();
           }
             result = clFreshGetLeadForagen.getLeadForAgentNew(sessionId, getLeadfor);
           if(!CollectionUtils.isEmpty(result.getResult())){
             updateforagentHold.setLeadID(result.getResult().get(0).getLeadId());
              clFreshGetLeadForagen.updScheduleUpdate(sessionId, updateforagentHold);
            return result.getResult();
           }
           result = clFreshGetLeadForagen.getLeadForUncall(sessionId, getLeadfor);
             if(!CollectionUtils.isEmpty(result.getResult())){
               updateforagentHold.setLeadID(result.getResult().get(0).getLeadId());
              clFreshGetLeadForagen.updScheduleUpdate(sessionId, updateforagentHold);
            return result.getResult();
           }
        throw new  TMSEntityNotFoundException(ErrorMessages.NOT_FOUND);
    }
    @Override 
    public boolean setLeadForAgent(SetLeadStatus setLeadStatus) throws TMSException {

      validateStatus(setLeadStatus);
  
      if (setLeadStatus.getLeadStatus() == EnumType.LeadStatus.TRASH.getStatus() || setLeadStatus.getLeadStatus() == EnumType.LeadStatus.REJECTED.getStatus()) {
          handleTrashAndRejected(setLeadStatus);
      }
  
      if (setLeadStatus.getLeadStatus() == EnumType.LeadStatus.BUSY.getStatus() || setLeadStatus.getLeadStatus() == EnumType.LeadStatus.NOANSWER.getStatus() || setLeadStatus.getLeadStatus() == EnumType.LeadStatus.UNREACHABLE.getStatus()) {
          handleBusyNoAnswerUnreachable(setLeadStatus);
      }
  
      if (setLeadStatus.getLeadStatus() == EnumType.LeadStatus.CALLBACKPOTPROSPECT.getStatus() || setLeadStatus.getLeadStatus() == EnumType.LeadStatus.CALLBACKCONSULTING.getStatus() || setLeadStatus.getLeadStatus() == EnumType.LeadStatus.CALLBACKPOTENTIAL.getStatus()) {
          handleCallBack(setLeadStatus);
      }
  
      if (setLeadStatus.getLeadStatus() == EnumType.LeadStatus.APPROVED.getStatus()) {
          handleApproved(setLeadStatus);
      }
  
      return false;
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
      if (Arrays.stream(validStatuses).noneMatch(status -> status == setLeadStatus.getLeadStatus())) {
          throw new InputMismatchException("Not added to status yet");
      }
  }
    
    private void handleTrashAndRejected(SetLeadStatus setLeadStatus) throws TMSException {
    SetLeadFresh setLeadFresh =new  SetLeadFresh(setLeadStatus.getLeadId(),setLeadStatus.getAgenId(),setLeadStatus.getLeadStatus(),setLeadStatus.getFcrReason(),setLeadStatus.getAddress(),setLeadStatus.getPhone(),setLeadStatus.getProdId(),setLeadStatus.getProdName(),setLeadStatus.getComment());      if (setLeadStatus.getFcrReason().isEmpty()) {
          throw new InputMismatchException("No reason has been added for the above status");
      }
  
      DBResponse<String> setLead = clFreshGetLeadForagen.setlead(sessionId, setLeadFresh);
  
      if (setLead.getErrorCode() != DbStatusResp.SUCCESS.getStatus()) {
          throw new TMSDbException(setLead.getErrorMsg());
      }
  }

     private void handleBusyNoAnswerUnreachable(SetLeadStatus setLeadStatus) throws TMSException {
    SetLeadFresh setLeadFresh =new SetLeadFresh(setLeadStatus.getLeadId(),setLeadStatus.getAgenId(),setLeadStatus.getLeadStatus(),setLeadStatus.getFcrReason(),setLeadStatus.getAddress(),setLeadStatus.getPhone(),setLeadStatus.getProdId(),setLeadStatus.getProdName(),setLeadStatus.getComment());
      DBResponse<String> setLead = clFreshGetLeadForagen.setlead(sessionId, setLeadFresh);
  
      if (setLead.getErrorCode() != DbStatusResp.SUCCESS.getStatus()) {
          throw new TMSDbException(setLead.getErrorMsg());
      }
  }

  private void handleCallBack(SetLeadStatus setLeadStatus) throws TMSException {
    SetLeadFresh setLeadFresh =new SetLeadFresh(setLeadStatus.getLeadId(),setLeadStatus.getAgenId(),setLeadStatus.getLeadStatus(),setLeadStatus.getFcrReason(),setLeadStatus.getAddress(),setLeadStatus.getPhone(),setLeadStatus.getProdId(),setLeadStatus.getProdName(),setLeadStatus.getComment());
      if (setLeadStatus.getFcrReason().isEmpty()) {
          throw new InputMismatchException("No reason has been added for the above status");
      }
  
      if (setLeadStatus.getRequestTime().isEmpty()) {
          throw new InputMismatchException("Callback time for status has not been added");
      }
  
      GetIdCallback getIdCallback = new GetIdCallback(setLeadStatus.getLeadId());
      SetLeadStatusCallback setLeadStatusCallback = new SetLeadStatusCallback(setLeadStatus.getLeadId(), setLeadStatus.getRequestTime(), setLeadStatus.getOrgId());
      DBResponse<List<GetLeadForAgentDto>> result = clFreshGetLeadForagen.getLeadTblCallback(sessionId, getIdCallback);
  
      if (result.getResult().size() != 0) {
          DBResponse<String> setLead = clFreshGetLeadForagen.setlead(sessionId, setLeadFresh);
          DBResponse<String> setcalback = clFreshGetLeadForagen.updeadForAgentCallback(sessionId, setLeadStatusCallback);
  
          if (setLead.getErrorCode() != DbStatusResp.SUCCESS.getStatus() || setcalback.getErrorCode() != DbStatusResp.SUCCESS.getStatus()) {
              throw new TMSDbException(setcalback.getErrorMsg());
          }
      } else {
          DBResponse<String> setLead = clFreshGetLeadForagen.setlead(sessionId, setLeadFresh);
          DBResponse<String> setcalback = clFreshGetLeadForagen.setLeadForAgentCallback(sessionId, setLeadStatusCallback);
  
          if (setLead.getErrorCode() != DbStatusResp.SUCCESS.getStatus() || setcalback.getErrorCode() != DbStatusResp.SUCCESS.getStatus()) {
              throw new TMSDbException(setcalback.getErrorMsg());
          }
      }
  }

  private void handleApproved(SetLeadStatus setLeadStatus) throws TMSException {
    SetLeadFresh setLeadFresh =new SetLeadFresh(setLeadStatus.getLeadId(),setLeadStatus.getAgenId(),setLeadStatus.getLeadStatus(),setLeadStatus.getFcrReason(),setLeadStatus.getAddress(),setLeadStatus.getPhone(),setLeadStatus.getProdId(),setLeadStatus.getProdName(),setLeadStatus.getComment());
      if (setLeadStatus.getAddress().isEmpty()) {
          throw new InputMismatchException("No address has been entered for the status");///
      }
  
      if (setLeadStatus.getPhone().isEmpty()) {
          throw new InputMismatchException("No phone has been entered for the status");
      }
  
      if (setLeadStatus.getProdId() == null) {
          throw new InputMismatchException("No product has been entered for the status");
      }
  
      SoSaleOderInsert soSaleOderInsert = new SoSaleOderInsert(setLeadStatus.getOrgId(), setLeadStatus.getLeadId(), setLeadStatus.getName(), setLeadStatus.getPhone(), setLeadStatus.getPaymentMethod());
      DBResponse<String> insso = clFreshGetLeadForagen.insSoSaleOder(sessionId, soSaleOderInsert);//doi so
      DBResponse<String> approve = clFreshGetLeadForagen.setlead(sessionId, setLeadFresh);
  
      if (approve.getErrorCode() != DbStatusResp.SUCCESS.getStatus() || insso.getErrorCode() != DbStatusResp.SUCCESS.getStatus()) {
          throw new TMSDbException(approve.getErrorMsg());
      }
  }
}