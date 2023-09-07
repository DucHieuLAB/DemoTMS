package com.tms.api.service.impl;

import com.tms.api.consts.EnumType;
import com.tms.api.exception.TMSDbException;
import com.tms.api.exception.TMSException;
import com.tms.api.service.BaseService;
import com.tms.commons.DBResponse;
import com.tms.dao.ClBasketFillterDao;
import com.tms.dto.request.schedule.GetLeadToFillter;
import com.tms.dto.request.schedule.UpdLeadFillter;
import com.tms.dto.response.LeadBasket;
import com.tms.api.service.FillterLeadService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FillterLeadServiceImpl extends BaseService implements FillterLeadService {
    private final  ClBasketFillterDao dao;

    public FillterLeadServiceImpl(ClBasketFillterDao fillterLeadImpl) {
        this.dao = fillterLeadImpl;
    }

    @Override
    public void fillterLead() {
        GetLeadToFillter getLeadToFillter = new GetLeadToFillter();
        getLeadToFillter.setInAttribute3("0");
        List<LeadBasket> basketList = getListToFillter(getLeadToFillter);
        // [To do]
    }

    @Override
    public List<LeadBasket> getListToFillter( GetLeadToFillter getLeadToFillter) {
        DBResponse<List<LeadBasket>> listDBResponse = dao.getLeadUpdate(sessionId,getLeadToFillter);
        return listDBResponse.getResult();
    }

    @Override
    public Boolean updLeadFillter(UpdLeadFillter updLeadFillter) throws TMSException {
        updLeadFillter.setModifiedBy(curUserId);
        DBResponse<String> insLeadResp = dao.updLeadFillter(sessionId, updLeadFillter);
        if (insLeadResp.getErrorCode() != EnumType.DbStatusResp.SUCCESS.getStatus()) {
            throw new TMSDbException(insLeadResp.getErrorMsg());
        }
        return true;
    }


}
