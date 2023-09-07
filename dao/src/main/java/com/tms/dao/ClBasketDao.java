package com.tms.dao;

import com.tms.commons.DBResponse;
import com.tms.dto.request.clBasket.GetLeadBasketsInTimeRange;
import com.tms.dto.request.clBasket.GetLeadToFillter;
import com.tms.dto.request.campaign.*;
import com.tms.dto.request.clBasket.UpdClBaskets;
import com.tms.dto.response.CampaignInf;
import com.tms.dto.response.ClBasket;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClBasketDao extends BaseDao{
    private static final String GET_NULL_ATTRIBUTE3 = "get_null_attribute3_rows";
    private static final String UPDATE_Cl_BASKET = "update_lead_baskets";
    private static final String GET_LEAD_BASKETS_IN_TIMERANGE = "get_lead_basket_in_timerange";

    public DBResponse<List<ClBasket>> getLeadUpdate(String sessionId , GetLeadToFillter params) {
        return this.dbGet(sessionId, GET_NULL_ATTRIBUTE3, params, ClBasket.class);
    }

    public DBResponse<List<ClBasket>> getLeadBasketsInTimeRange(String sessionId , GetLeadBasketsInTimeRange params) {
        return this.dbGet(sessionId, GET_LEAD_BASKETS_IN_TIMERANGE, params, ClBasket.class);
    }

    public DBResponse<String> updClBasketAfterFillter(String sessionId, UpdClBaskets params) {
        return this.dbInsOrUpd(sessionId, UPDATE_Cl_BASKET, params);
    }
}
