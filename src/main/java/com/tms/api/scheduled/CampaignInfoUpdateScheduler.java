package com.tms.api.scheduled;

import com.tms.api.consts.EnumType;
import com.tms.api.consts.MessageConst;
import com.tms.api.exception.TMSDbException;
import com.tms.api.service.CampaignService;
import com.tms.dto.response.CampaignInf;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CampaignInfoUpdateScheduler {
    private CampaignService campaignService;

    public CampaignInfoUpdateScheduler(CampaignService campaignService) {
        this.campaignService = campaignService;
    }

    @Scheduled(cron = "0 30 0 * * *", zone = "Asia/Ho_Chi_Minh")
    public void updateCampaignInfoList() throws TMSDbException {
        List<CampaignInf> campaignInfs = campaignService.getCampainInfs(EnumType.Campaign.CALLING_LIST.getType());
        if (campaignService == null) {
            throw new TMSDbException(MessageConst.ERROL_NULL_DB_RESPONSE);
        }
        CampaignInfos.campaignInfList = campaignInfs;
    }
}
