package com.tms.dao;

import com.tms.commons.DBResponse;
import com.tms.dto.request.lead.*;
import com.tms.dto.response.ClBasket;
import com.tms.dto.response.ClBaskets;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeadFillterDao extends BaseDao{
    private static final String GET_NULL_ATTRIBUTE3 = "get_null_attribute3_rows";
    private static final String UPDATE_Cl_BASKET_AFTER_FILLTER = "update_lead_fillter";
    private static final String INS_CL_FRESH_AFTER_FILLTER = "ins_cl_fresh_after_fillter";
    private static final String GET_LEAD_BASKETS_IN_TIMERANGE = "get_lead_basket_in_timerange";

    /* BEGIN GET */
    public DBResponse<List<ClBasket>> getLeadUpdate(String sessionId , GetLeadToFillter params) {
        return this.dbGet(sessionId, GET_NULL_ATTRIBUTE3, params, ClBasket.class);
    }
    /* END GET */

    /* BEGIN GET */
    public DBResponse<List<ClBasket>> getLeadBasketsInTimeRange(String sessionId , GetLeadBasketsInTimeRange params) {
        return this.dbGet(sessionId, GET_LEAD_BASKETS_IN_TIMERANGE, params, ClBasket.class);
    }
    /* END GET */

    /* BEGIN UPDATE */
    public DBResponse<String> updClBasketAfterFillter(String sessionId, ClBaskets params) {
        return this.dbInsOrUpd(sessionId, UPDATE_Cl_BASKET_AFTER_FILLTER,params.getClBaskets());
    }
    /* END UPDATE */

    /* BEGIN INSERT */
    public DBResponse<String> insLeadAfterFillter(String sessionId, List<ClFresh> params) {
        return this.dbInsOrUpd(sessionId, INS_CL_FRESH_AFTER_FILLTER, params);
    }
    /* END INSERT */
}
