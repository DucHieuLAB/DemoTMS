package com.tms.api.service;

import com.tms.api.exception.TMSDbException;
import com.tms.api.exception.TMSException;
import com.tms.dto.request.lead.GetLeadToFillter;
import com.tms.dto.request.lead.ClFresh;
import com.tms.dto.request.lead.UpdLeadFillter;
import com.tms.dto.response.ClBasket;
import com.tms.dto.response.ClBaskets;
import com.tms.dto.response.LeadBasket;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface FillterLeadService {

    public ClBaskets getListToFillter(GetLeadToFillter getLeadToFillter) throws TMSException;

    Boolean updLeadFillter(ClBaskets updLeadFillter) throws TMSException;

    boolean insLeadAfterFillter(List<ClFresh> CLFresh)throws TMSDbException;
}
