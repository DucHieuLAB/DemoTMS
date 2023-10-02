package com.tms.dao;

import org.springframework.stereotype.Service;

import com.tms.commons.DBResponse;
import com.tms.dto.request.ClFreshGetLead.SoSaleOderInsert;
import com.tms.dto.request.clCallback.DelClCallback;

@Service
public class ClCallbackDao extends BaseDao{
    private static final String DEL_CL_CALLBACK = "del_cl_callback";
    public DBResponse<String> delClCallback(String sessionId, DelClCallback params) {
        return this.dbInsOrUpd(sessionId, DEL_CL_CALLBACK, params);
    }
}
