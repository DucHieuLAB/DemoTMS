package com.tms.api.service.impl;

import com.tms.api.service.BaseService;
import com.tms.commons.DBResponse;
import com.tms.dao.ClBasketFillterDao;
import com.tms.dto.request.schedule.GetLeadToFillter;
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
        getLeadToFillter.setIn_attribute3("0");
        List<LeadBasket> basketList = getListToFillter(getLeadToFillter);
        // [To do]
    }

    @Override
    public List<LeadBasket> getListToFillter( GetLeadToFillter getLeadToFillter) {
        DBResponse<List<LeadBasket>> listDBResponse = dao.getLeadUpdate(sessionId,getLeadToFillter);
        return listDBResponse.getResult();
    }
}
