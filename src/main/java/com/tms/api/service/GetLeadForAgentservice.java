package com.tms.api.service;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.tms.api.exception.TMSException;
import com.tms.dto.request.ClFreshGetLead.GetLeadfor;
import com.tms.dto.request.ClFreshGetLead.SetLeadStatus;
import com.tms.dto.response.GetLeadForAgentDto;
public interface GetLeadForAgentService { 

    List<GetLeadForAgentDto> getLeadforagent(GetLeadfor getLeadfor) throws TMSException;
    
    boolean UpdLead(SetLeadStatus setLeadStatus) throws TMSException, IllegalAccessException, InvocationTargetException, NoSuchMethodException;    
}
