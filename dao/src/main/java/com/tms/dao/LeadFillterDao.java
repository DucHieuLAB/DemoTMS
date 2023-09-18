package com.tms.dao;

import com.tms.commons.DBResponse;
import com.tms.dto.request.lead.*;
import com.tms.dto.response.CampaignInf;
import com.tms.dto.response.ClBasket;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeadFillterDao extends BaseDao{
    private static final String GET_NULL_ATTRIBUTE3 = "get_null_attribute3_rows";
    private static final String UPDATE_Cl_BASKET = "update_lead_baskets";
    private static final String INS_CL_FRESH_AFTER_FILLTER = "insert_cl_freshs";
    private static final String GET_LEAD_BASKETS_IN_TIMERANGE = "get_lead_basket_in_timerange";
    private static final String INS_CL_FRESHS_QUERY = "insert_cl_freshs_query";
    private static final String GET_CAMPAIGN_INF = "get_campaign_inf";

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

    /* BEGIN GET */
    public DBResponse<List<CampaignInf>> getCampaignInf(String sessionId , GetCampaignInf params) {
        return this.dbGet(sessionId, GET_CAMPAIGN_INF, params, CampaignInf.class);
    }
    /* END GET */
    /* BEGIN UPDATE */
    public DBResponse<String> updClBasketAfterFillter(String sessionId, UpdClBaskets params) {
        return this.dbInsOrUpd(sessionId, UPDATE_Cl_BASKET, params);
    }
    /* END UPDATE */

    /* BEGIN INSERT */
    public DBResponse<String> insLeadAfterFillter(String sessionId, InsClFreshs params) {
        return this.dbInsOrUpd(sessionId, INS_CL_FRESH_AFTER_FILLTER, params);
    }
    /* END INSERT */

    /* BEGIN INSERT */
    public DBResponse<String> insLeadAfterFillterByQuery(String sessionId, InsClFreshsQuery params) {
        return this.dbInsOrUpd(sessionId, INS_CL_FRESHS_QUERY, params);
    }
    /* END INSERT */
}
