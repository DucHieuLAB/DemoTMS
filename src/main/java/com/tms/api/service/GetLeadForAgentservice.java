package com.tms.api.service;
import java.util.List;

import com.tms.api.exception.TMSException;
import com.tms.dto.request.ClFreshGetLead.GetLeadfor;
import com.tms.dto.request.ClFreshGetLead.SetLeadStatus;
import com.tms.dto.response.GetLeadForAgentDto;
public interface GetLeadForAgentService { 

    List<GetLeadForAgentDto> getLeadforagent(GetLeadfor getLeadfor) throws TMSException;
    
    boolean setLeadForAgent(SetLeadStatus setLeadStatus) throws TMSException;    
}
