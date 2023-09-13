package com.tms.api.service.impl;

import com.tms.api.consts.EnumType;
import com.tms.api.exception.TMSDbException;
import com.tms.api.exception.TMSException;
import com.tms.api.service.BaseService;
import com.tms.commons.DBResponse;
import com.tms.dao.LeadFillterDao;
import com.tms.dto.request.lead.GetLeadToFillter;
import com.tms.dto.request.lead.ClFresh;
import com.tms.dto.request.lead.UpdLeadFillter;
import com.tms.dto.response.LeadBasket;
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
    public List<LeadBasket> getListToFillter( GetLeadToFillter getLeadToFillter) {
        DBResponse<List<LeadBasket>> listDBResponse = dao.getLeadUpdate(sessionId,getLeadToFillter);
        return listDBResponse.getResult();
    }

    @Override
    public Boolean updLeadFillter(UpdLeadFillter updLeadFillter) throws TMSException {
        updLeadFillter.setModifyby(curUserId);
        DBResponse<String> insLeadResp = dao.updLeadFillter(sessionId, updLeadFillter);
        if (insLeadResp.getErrorCode() != EnumType.DbStatusResp.SUCCESS.getStatus()) {
            throw new TMSDbException(insLeadResp.getErrorMsg());
        }
        return true;
    }

    @Override
    public boolean insLeadAfterFillter(List<ClFresh> CLFresh) throws TMSDbException {
        DBResponse<String> insLeadAfterFillterDbResp = dao.insLeadAfterFillter(sessionId, CLFresh);
        if(insLeadAfterFillterDbResp.getErrorCode() != EnumType.DbStatusResp.SUCCESS.getStatus()){
            throw  new TMSDbException(insLeadAfterFillterDbResp.getErrorMsg());
        }
        return true;
    }


}
