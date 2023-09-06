package com.tms.dao;

import com.tms.commons.DBResponse;
import com.tms.dto.request.campaign.GetCampaignInf;
import com.tms.dto.response.CampaignInf;

import java.util.List;

public class CampaignDao extends BaseDao{
    private static final String GET_CAMPAIGN_INF = "get_campaign_inf";
    public DBResponse<List<CampaignInf>> getCampaignInf(String sessionId , GetCampaignInf params) {
        return this.dbGet(sessionId, GET_CAMPAIGN_INF, params, CampaignInf.class);
    }
}
