package com.tms.api.scheduled;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class LeadFillter {
    @Scheduled(fixedDelay = 2000 )
    public void fillterDuplicate(){

    }
    @Scheduled(fixedDelay = 4000 )
    public void fillterInvalidLead(){
        System.out.println("Run cron job fillterInvalidLead");
    }

    @Scheduled(fixedDelay = 4000 )
    public void generateLead(){
        System.out.println("Run cron job generateLead");
    }
}
