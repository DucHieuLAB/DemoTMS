package com.tms.dao;

import com.tms.commons.DBResponse;
import com.tms.dto.request.ClFreshGetLead.GetLeadById;
import com.tms.dto.request.ClFreshGetLead.GetLeadfor;
import com.tms.dto.request.ClFreshGetLead.SetLeadFresh;
import com.tms.dto.request.ClFreshGetLead.SoSaleOderInsert;
import com.tms.dto.request.clCallback.UpdClCallback;
import com.tms.dto.request.clFresh.InsClFreshsQuery;
import com.tms.dto.request.clFresh.UpdClFreshs;
import com.tms.dto.response.GetLeadForAgentDto;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ClFreshDao extends BaseDao {

    private static final String INS_CL_FRESHS_QUERY = "insert_cl_freshs_query";
    private static final String UPD_CL_FRESHS ="update_cl_fresh";
    private static final String GET_LEAD_FOR_AGENT_URGENT = "get_lead_for_agent_urgent";
    private static final String GET_LEAD_FOR_AGENT_CALLBACK = "get_lead_for_agent_callback";
    private static final String GET_LEAD_FOR_AGENT_NEW = "get_lead_for_agent_new";
    private static final String GET_LEAD_FOR_AGENT_UNCALL = "get_lead_for_agent_uncall";
    private static final String GET_LEAD_FOR_AGENT_HOLD = "get_lead_for_agent_hold";
    private static final String SET_LEAD_FOR_AGENT="update_lead_for_agent";
    private static final String UDP_LEAD_FOR_AGENT_CALLBACK = "upd_lead_for_agent_callback";
    private static final String INS_SO_SALE_ODER = "ins_so_sale_oder";
    private static final String GET_LEAD_BY_ID = "get_lead_by_id";

    public DBResponse<String> insClFresh(String sessionId, InsClFreshsQuery params) {
        return this.dbInsOrUpd(sessionId, INS_CL_FRESHS_QUERY, params);
    }

    public DBResponse<String> updClFresh(String sessionId, UpdClFreshs params) {
        return this.dbInsOrUpd(sessionId, UPD_CL_FRESHS, params);
    }
        public DBResponse<List<GetLeadForAgentDto>> getLeadForAgentUrgent(String sessionId, GetLeadfor params) {
        return this.dbGet(sessionId, GET_LEAD_FOR_AGENT_URGENT, params, GetLeadForAgentDto.class);
    }
    public DBResponse<List<GetLeadForAgentDto>> getLeadById(String sessionId, GetLeadById params) {
        return this.dbGet(sessionId, GET_LEAD_BY_ID, params, GetLeadForAgentDto.class);
    }
    public DBResponse<List<GetLeadForAgentDto>> getLeadforagentCallback(String sessionId, GetLeadfor params) {
        return this.dbGet(sessionId, GET_LEAD_FOR_AGENT_CALLBACK, params, GetLeadForAgentDto.class);
    }
    public DBResponse<List<GetLeadForAgentDto>> getLeadForAgentNew(String sessionId, GetLeadfor params) {
        return this.dbGet(sessionId, GET_LEAD_FOR_AGENT_NEW, params, GetLeadForAgentDto.class);
    }
    public DBResponse<List<GetLeadForAgentDto>> getLeadForUncall(String sessionId, GetLeadfor params) {
        return this.dbGet(sessionId, GET_LEAD_FOR_AGENT_UNCALL, params, GetLeadForAgentDto.class);
    }
    public DBResponse<List<GetLeadForAgentDto>> getLeadForHold(String sessionId, GetLeadfor params) {
        return this.dbGet(sessionId, GET_LEAD_FOR_AGENT_HOLD, params, GetLeadForAgentDto.class);
    }
            public DBResponse<String> setlead(String sessionId, SetLeadFresh params) {
        return this.dbInsOrUpd(sessionId, SET_LEAD_FOR_AGENT, params);
    }
        public DBResponse<String> updeadForAgentCallback(String sessionId, UpdClCallback params) {
        return this.dbInsOrUpd(sessionId, UDP_LEAD_FOR_AGENT_CALLBACK, params);
    }
            public DBResponse<String> insSoSaleOder(String sessionId, SoSaleOderInsert params) {
        return this.dbInsOrUpd(sessionId, INS_SO_SALE_ODER, params);
    }

}
