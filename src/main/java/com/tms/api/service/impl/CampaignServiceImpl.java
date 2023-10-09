package com.tms.api.service.impl;

import com.tms.api.commons.ApiMessageError;
import com.tms.api.consts.EnumType;
import com.tms.api.consts.MessageConst;
import com.tms.api.exception.ErrorMessages;
import com.tms.api.exception.TMSDbException;
import com.tms.api.exception.TMSEntityNotFoundException;
import com.tms.api.exception.TMSException;
import com.tms.api.helper.Helper;
import com.tms.api.service.BaseService;
import com.tms.api.service.CampaignService;
import com.tms.commons.DBResponse;
import com.tms.dao.CampaignDao;
import com.tms.dto.request.campaign.GetCampaignInf;
import com.tms.dto.response.CampaignInf;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class CampaignServiceImpl extends BaseService implements CampaignService {
    private final CampaignDao campaignDao;

    public CampaignServiceImpl(CampaignDao campaignDao){
        this.campaignDao = campaignDao;
    }

     @Override
     public List<CampaignInf> getCampainInfs(Integer campaignStatus) throws TMSException {
         GetCampaignInf getCampaignInf = new GetCampaignInf();
         getCampaignInf.setType(campaignStatus);
         DBResponse<List<CampaignInf>> campaignDbres = campaignDao.getCampaignInf(sessionId,getCampaignInf);
         if(campaignDbres.getErrorCode()!= EnumType.DbStatusResp.SUCCESS.getStatus()){
             throw new TMSDbException(campaignDbres.getErrorMsg());
         }
         if (CollectionUtils.isEmpty(campaignDbres.getResult())) {
             String errorMessage = MessageConst.NOT_FOUND_WITH_OBJECT_PARAMS + Helper.toJson(getCampaignInf);
             throw new TMSEntityNotFoundException(ErrorMessages.NOT_FOUND, new ApiMessageError(errorMessage));
         }
         return campaignDbres.getResult();
     }

}
