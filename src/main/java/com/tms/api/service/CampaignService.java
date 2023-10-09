package com.tms.api.service;

import com.tms.api.exception.TMSException;
import com.tms.dto.response.CampaignInf;

import java.util.List;

public interface CampaignService {

    List<CampaignInf> getCampainInfs(Integer campaignStatus) throws TMSException;

}
