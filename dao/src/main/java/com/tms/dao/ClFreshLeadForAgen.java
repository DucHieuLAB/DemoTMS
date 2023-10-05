package com.tms.dao;

import java.util.List;

import org.springframework.stereotype.Service;
import com.tms.commons.DBResponse;
import com.tms.dto.request.ClFreshGetLead.GetIdCallback;
import com.tms.dto.request.ClFreshGetLead.GetLeadfor;
import com.tms.dto.request.ClFreshGetLead.SetLeadFresh;
import com.tms.dto.request.ClFreshGetLead.SetLeadStatusCallback;
import com.tms.dto.request.ClFreshGetLead.SoSaleOderInsert;
import com.tms.dto.request.ClFreshGetLead.UpdateforagentHold;
import com.tms.dto.response.GetLeadForAgentDto;

@Service
public class ClFreshLeadForAgen extends BaseDao {
    private static final String GET_LEAD_FOR_AGENT_URGENT = "get_lead_for_agent_urgent";
    private static final String GET_LEAD_FOR_AGENT_CALLBACK = "get_lead_for_agent_callback";
    private static final String GET_LEAD_FOR_AGENT_NEW = "get_lead_for_agent_new";
    private static final String GET_LEAD_FOR_AGENT_UNCALL = "get_lead_for_agent_uncall";
    private static final String GET_LEAD_FOR_AGENT_UPDATE_HOLD = "get_lead_for_agent_update_hold";
    private static final String GET_LEAD_FOR_AGENT_HOLD = "get_lead_for_agent_hold";
    private static final String SET_LEAD_FOR_AGENT = "update_lead_for_agent";
    private static final String INS_LEAD_FOR_AGENT_CALLBACK = "ins_lead_for_agent_callback";
    private static final String UDP_LEAD_FOR_AGENT_CALLBACK = "upd_lead_for_agent_callback";
    private static final String GET_LEAD_TBL_CALLBACK = "get_lead_tbl_callback";
    private static final String INS_SO_SALE_ODER = "ins_so_sale_oder";

    public DBResponse<List<GetLeadForAgentDto>> getLeadForAgentUrgent(String sessionId, GetLeadfor params) {
        return this.dbGet(sessionId, GET_LEAD_FOR_AGENT_URGENT, params, GetLeadForAgentDto.class);
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

    public DBResponse<String> updScheduleUpdate(String sessionId, UpdateforagentHold params) {
        return this.dbInsOrUpd(sessionId, GET_LEAD_FOR_AGENT_UPDATE_HOLD, params);
    }

    public DBResponse<List<GetLeadForAgentDto>> getLeadForHold(String sessionId, GetLeadfor params) {
        return this.dbGet(sessionId, GET_LEAD_FOR_AGENT_HOLD, params, GetLeadForAgentDto.class);
    }

    public DBResponse<String> setlead(String sessionId, SetLeadFresh params) {
        return this.dbInsOrUpd(sessionId, SET_LEAD_FOR_AGENT, params);
    }

    public DBResponse<String> setLeadForAgentCallback(String sessionId, SetLeadStatusCallback params) {
        return this.dbInsOrUpd(sessionId, INS_LEAD_FOR_AGENT_CALLBACK, params);
    }

    public DBResponse<String> updeadForAgentCallback(String sessionId, SetLeadStatusCallback params) {
        return this.dbInsOrUpd(sessionId, UDP_LEAD_FOR_AGENT_CALLBACK, params);
    }

    public DBResponse<List<GetLeadForAgentDto>> getLeadTblCallback(String sessionId, GetIdCallback params) {
        return this.dbGet(sessionId, GET_LEAD_TBL_CALLBACK, params, GetLeadForAgentDto.class);
    }

    public DBResponse<String> insSoSaleOder(String sessionId, SoSaleOderInsert params) {
        return this.dbInsOrUpd(sessionId, INS_SO_SALE_ODER, params);
    }
}
