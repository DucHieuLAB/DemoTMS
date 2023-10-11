package com.tms.api.scheduled;

import com.tms.api.exception.TMSException;
import com.tms.api.service.ClFreshService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class UpdateClFreshScheduler {
    private final ClFreshService clFreshService;

    public UpdateClFreshScheduler(ClFreshService clFreshService){
        this.clFreshService = clFreshService;
    }

    @Scheduled(cron = "0 30 0 * * *", zone = "Asia/Ho_Chi_Minh")
    public void updateDayCall() throws TMSException {
        clFreshService.updDayCallAfter24Hour();
    }
}
