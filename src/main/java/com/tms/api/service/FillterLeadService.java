package com.tms.api.service;

import com.tms.api.exception.TMSDbException;
import com.tms.api.exception.TMSException;
import com.tms.dto.request.lead.GetLeadToFillter;
import com.tms.dto.request.lead.InsLeadAfterFillter;
import com.tms.dto.request.lead.InsListLeadAfterFillter;
import com.tms.dto.request.lead.UpdLeadFillter;
import com.tms.dto.response.LeadBasket;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface FillterLeadService {

    public void fillterLead() ;
    public List<LeadBasket> getListToFillter(GetLeadToFillter getLeadToFillter) throws TMSException;

    Boolean updLeadFillter(UpdLeadFillter updLeadFillter) throws TMSException;

    boolean insLeadAfterFillter(List<InsLeadAfterFillter> insLeadAfterFillter)throws TMSDbException;
}
