package com.tms.api.scheduled;

import com.tms.api.consts.MessageConst;
import com.tms.api.exception.TMSDbException;
import com.tms.api.service.BlackListService;
import com.tms.dto.response.CfBlackList;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BlackListUpdateScheduler {
    BlackListService blackListService;
    public BlackListUpdateScheduler(BlackListService blackListService){
        this.blackListService = blackListService;
    }

    @Scheduled(cron = "0 30 0 * * *", zone = "Asia/Ho_Chi_Minh")
    public void updateListBlackList() throws TMSDbException {
        List<CfBlackList> blackLists = blackListService.getBlackList();
        if (blackLists == null){
            throw new TMSDbException(MessageConst.ERROL_NULL_DB_RESPONSE);
        }
        BlackLists.blackLists = blackLists;
    }
}
