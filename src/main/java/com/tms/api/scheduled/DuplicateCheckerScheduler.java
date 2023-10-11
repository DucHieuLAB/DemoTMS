package com.tms.api.scheduled;

import com.tms.api.exception.TMSException;
import com.tms.api.service.ClBasketService;
import com.tms.dto.response.ClBasket;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
public class DuplicateCheckerScheduler {
    ClBasketService clBasketService;

    public DuplicateCheckerScheduler(ClBasketService clBasketService){
        this.clBasketService = clBasketService;
    }

    @Scheduled(cron = "0 30 0 * * *", zone = "Asia/Ho_Chi_Minh")
    public void updateLeadAfter24h() throws TMSException {
        List<ClBasket> basketsIn24hour = clBasketService.getLeadInTimeRange(LocalDateTime.now().minus(24, ChronoUnit.HOURS), LocalDateTime.now());
        DuplicateLeadChecker.addRecord(basketsIn24hour);
    }
}
