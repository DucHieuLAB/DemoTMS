package com.tms.api.service;

import com.tms.dto.request.schedule.GetLeadToFillter;
import com.tms.dto.response.LeadBasket;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface FillterLeadService {
    public void fillterLead() ;
    public List<LeadBasket> getListToFillter(GetLeadToFillter getLeadToFillter);
}
