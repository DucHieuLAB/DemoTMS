package com.tms.api.service.impl;

import com.tms.api.consts.EnumType;
import com.tms.api.exception.TMSDbException;
import com.tms.api.service.BaseService;
import com.tms.api.service.CampaignService;
import com.tms.commons.DBResponse;
import com.tms.dao.CampaignDao;
import com.tms.dto.request.campaign.GetCampaignInf;
import com.tms.dto.response.CampaignInf;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampaignServiceImpl extends BaseService implements CampaignService {
    private final CampaignDao campaignDao;

    public CampaignServiceImpl(CampaignDao campaignDao){
        this.campaignDao = campaignDao;
    }

     @Override
     public List<CampaignInf> getCampainInfs(Integer campaignStatus) throws TMSDbException {
         GetCampaignInf getCampaignInf = new GetCampaignInf();
         getCampaignInf.setType(campaignStatus);
         DBResponse<List<CampaignInf>> campaignDbres = campaignDao.getCampaignInf(sessionId,getCampaignInf);
         if (campaignDbres == null || campaignDbres.getResult().size() == 0){
             throw new TMSDbException("Can't get campaign info");
         }
         if(campaignDbres.getErrorCode()!= EnumType.DbStatusResp.SUCCESS.getStatus()){
             throw new TMSDbException(campaignDbres.getErrorMsg());
         }
         return campaignDbres.getResult();
     }

}
