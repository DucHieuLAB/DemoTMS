package com.tms.api.scheduled;

import com.tms.api.exception.TMSDbException;
import com.tms.api.service.ClBasketService;
import com.tms.dto.response.ClBasket;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
public class DuplicateLeadUpdateScheduler {
    private final ClBasketService clBasketService;

    public DuplicateLeadUpdateScheduler(ClBasketService clBasketService) {
        this.clBasketService = clBasketService;
    }

    @Scheduled(cron = "0 30 * * * *", zone = "Asia/Ho_Chi_Minh"                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      )
    public void updateLeadInLast24Hours() throws TMSDbException {
        LocalDateTime time = LocalDateTime.now();
        List<ClBasket> leadsInRange = clBasketService.getLeadInTimeRange(time,time.minus(24, ChronoUnit.HOURS));
        DuplicateLeadChecker.clearRecords();
        DuplicateLeadChecker.addRecord(leadsInRange);
    }
}
