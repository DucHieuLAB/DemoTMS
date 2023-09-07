package com.tms.dao;

import com.tms.commons.DBResponse;
import com.tms.dto.request.clFresh.InsClFreshsQuery;
import org.springframework.stereotype.Service;

@Service
public class ClFreshDao extends BaseDao {

    private static final String INS_CL_FRESHS_QUERY = "insert_cl_freshs_query";
    /* BEGIN INSERT */
    public DBResponse<String> insLeadAfterFillterByQuery(String sessionId, InsClFreshsQuery params) {
        return this.dbInsOrUpd(sessionId, INS_CL_FRESHS_QUERY, params);
    }
    /* END INSERT */
}
