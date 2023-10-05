package com.tms.api.service;

import com.tms.api.exception.TMSDbException;
import com.tms.api.exception.TMSException;
import com.tms.dto.request.ClFreshGetLead.GetLeadfor;
import com.tms.dto.request.ClFreshGetLead.SetLeadStatus;
import com.tms.dto.request.clFresh.InsClFresh;
import com.tms.dto.request.clFresh.UpdClFresh;
import com.tms.dto.response.GetLeadForAgentDto;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface ClFreshService {
    public void insertClFresh(List<InsClFresh> clFreshes, String sessionId) throws TMSDbException;

    boolean updClFreshAfterValidSO(int leadId, UpdClFresh updClFresh) throws TMSDbException;
    
    List<GetLeadForAgentDto> getLeadForAgent(GetLeadfor getLeadfor) throws TMSException;

    boolean updLead(int id,SetLeadStatus setLeadStatus) throws TMSException, IllegalAccessException, InvocationTargetException, NoSuchMethodException;
}
