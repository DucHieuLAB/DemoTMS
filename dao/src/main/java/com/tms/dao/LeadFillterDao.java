package com.tms.dao;

import com.tms.commons.DBResponse;
import com.tms.dto.request.lead.GetLeadToFillter;
import com.tms.dto.request.lead.InsLeadAfterFillter;
import com.tms.dto.request.lead.InsListLeadAfterFillter;
import com.tms.dto.request.lead.UpdLeadFillter;
import com.tms.dto.response.LeadBasket;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeadFillterDao extends BaseDao{
    private static final String GET_NULL_ATTRIBUTE3 = "get_null_attribute3_rows";
    private static final String UPDATE_LEAD_FILLTER = "update_lead_fillter";
    private static final String INS_CL_FRESH_AFTER_FILLTER = "ins_cl_fresh_after_fillter";

    /* BEGIN GET */
    public DBResponse<List<LeadBasket>> getLeadUpdate(String sessionId , GetLeadToFillter params) {
        return this.dbGet(sessionId, GET_NULL_ATTRIBUTE3, params, LeadBasket.class);
    }
    /* END GET */

    /* BEGIN UPDATE */
    public DBResponse<String> updLeadFillter(String sessionId, UpdLeadFillter params) {
        return this.dbInsOrUpd(sessionId, UPDATE_LEAD_FILLTER,params);
    }
    /* END UPDATE */

    /* BEGIN INSERT */
    public DBResponse<String> insLeadAfterFillter(String sessionId, List<InsLeadAfterFillter> params) {
        return this.dbInsOrUpd(sessionId, INS_CL_FRESH_AFTER_FILLTER, params);
    }
    /* END INSERT */
}
