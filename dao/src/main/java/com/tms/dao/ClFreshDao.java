package com.tms.dao;

import com.tms.commons.DBResponse;
import com.tms.dto.request.clFresh.InsClFreshsQuery;
import com.tms.dto.request.clFresh.UpdClFreshs;
import org.springframework.stereotype.Service;

@Service
public class ClFreshDao extends BaseDao {

    private static final String INS_CL_FRESHS_QUERY = "insert_cl_freshs_query";
    private static final String UPD_CL_FRESHS ="insert_cl_freshs_query";

    public DBResponse<String> insClFresh(String sessionId, InsClFreshsQuery params) {
        return this.dbInsOrUpd(sessionId, INS_CL_FRESHS_QUERY, params);
    }

    public DBResponse<String> updClFresh(String sessionId, UpdClFreshs params) {
        return this.dbInsOrUpd(sessionId, UPD_CL_FRESHS, params);
    }

}
