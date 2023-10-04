package com.tms.api.controller;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tms.api.commons.TMSResponse;
import com.tms.api.exception.TMSException;
import com.tms.api.service.GetLeadForAgentService;
import com.tms.dto.request.ClFreshGetLead.GetLeadfor;
import com.tms.dto.request.ClFreshGetLead.SetLeadStatus;
import com.tms.dto.response.GetLeadForAgentDto;


@RestController
@RequestMapping("api/v1/clfresh")
public class FreshController {
    private final GetLeadForAgentService getLeadForAgentservice;
    public FreshController(GetLeadForAgentService getLeadForAgentservice){
        this.getLeadForAgentservice=getLeadForAgentservice;
    }
     @PostMapping("/getlead")
    public TMSResponse<List<GetLeadForAgentDto>> getLead(@Valid @RequestBody GetLeadfor getLeadfor )throws TMSException{
        List<GetLeadForAgentDto> result= getLeadForAgentservice.getLeadforagent(getLeadfor);
        return TMSResponse.buildResponse(result);

    }
    @PostMapping("/setlead")
    public TMSResponse<Boolean> createScheduleUpdate(@Valid @RequestBody SetLeadStatus setLeadStatus) throws TMSException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        boolean result = getLeadForAgentservice.UpdLead(setLeadStatus);
        return TMSResponse.buildResponse(result);
    }
}
