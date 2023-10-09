package com.tms.dao;

import org.springframework.stereotype.Service;

import com.tms.commons.DBResponse;
import com.tms.dto.request.clCallback.DelClCallback;
import com.tms.dto.request.clCallback.InsClCallback;

@Service
public class ClCallbackDao extends BaseDao {
    private static final String DEL_CL_CALLBACK = "del_cl_callback";

    private static final String INS_CL_CALLBACK_V6 = "ins_cl_callback_v6";

    public DBResponse<String> delClCallback(String sessionId, DelClCallback params) {
        return this.dbInsOrUpd(sessionId, DEL_CL_CALLBACK, params);
    }

    public DBResponse<String> insClCallback(String sessionId, InsClCallback params) {
        return this.dbInsOrUpd(sessionId, INS_CL_CALLBACK_V6, params);
    }
}
