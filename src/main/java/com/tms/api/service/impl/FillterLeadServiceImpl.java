package com.tms.api.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tms.api.consts.EnumType;
import com.tms.api.exception.TMSDbException;
import com.tms.api.exception.TMSException;
import com.tms.api.service.BaseService;
import com.tms.commons.DBResponse;
import com.tms.dao.LeadFillterDao;
import com.tms.dto.request.lead.GetLeadToFillter;
import com.tms.dto.request.lead.ClFresh;
import com.tms.dto.response.ClBasket;
import com.tms.api.service.FillterLeadService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FillterLeadServiceImpl extends BaseService implements FillterLeadService {
    private final LeadFillterDao dao;

    public FillterLeadServiceImpl(LeadFillterDao fillterLeadImpl) {
        this.dao = fillterLeadImpl;
    }

    @Override
    public List<ClBasket> getListToFillter(GetLeadToFillter getLeadToFillter) throws TMSException{
        DBResponse<List<ClBasket>> listDBResponse = dao.getLeadUpdate(sessionId,getLeadToFillter);
        if(listDBResponse.getErrorCode() != EnumType.DbStatusResp.SUCCESS.getStatus()){
            throw new TMSDbException(listDBResponse.getErrorMsg());
        }
        return listDBResponse.getResult();
    }

//


}
