package com.tms.dao;

import com.tms.commons.DBResponse;
import com.tms.dto.request.schedule.GetLeadToFillter;
import com.tms.dto.response.LeadBasket;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClBasketFillterDao extends BaseDao{
    private static final String GET_NULL_ATTRIBUTE3 = "get_null_attribute3_rows";

    /* BEGIN GET */
    public DBResponse<List<LeadBasket>> getLeadUpdate(String sessionId , GetLeadToFillter params) {
        return this.dbGet(sessionId, GET_NULL_ATTRIBUTE3, params, LeadBasket.class);
    }
    /* END GET */
}
