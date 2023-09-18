package com.tms.api.service;

import com.tms.api.exception.TMSDbException;
import com.tms.api.exception.TMSException;
import com.tms.dto.request.lead.GetLeadToFillter;
import com.tms.dto.request.lead.ClFresh;
import com.tms.dto.response.ClBasket;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface FillterLeadService {

    public List<ClBasket> getListToFillter(GetLeadToFillter getLeadToFillter) throws TMSException;

}
