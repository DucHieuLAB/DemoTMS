package com.tms.api.service;

import com.tms.api.exception.TMSDbException;
import com.tms.dto.response.CampaignInf;

import java.util.List;

public interface CampaignService {
    public List<CampaignInf> getCampainInfs(Integer campaign_status, String sessionId) throws TMSDbException;
}
