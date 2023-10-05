package com.tms.api.controller;

import java.util.List;
import javax.validation.Valid;

import com.tms.api.service.ClFreshService;
import com.tms.dto.request.clFresh.UpdClFresh;
import org.springframework.web.bind.annotation.*;

import com.tms.api.commons.TMSResponse;
import com.tms.api.exception.TMSException;
import com.tms.api.service.GetLeadForAgentService;
import com.tms.dto.request.ClFreshGetLead.GetLeadfor;
import com.tms.dto.request.ClFreshGetLead.SetLeadStatus;
import com.tms.dto.response.GetLeadForAgentDto;


@RestController
@RequestMapping("api/v1")
public class FreshController {
    private final GetLeadForAgentService getLeadForAgentservice;

    private final ClFreshService clFreshService;

    public FreshController(GetLeadForAgentService getLeadForAgentservice, ClFreshService clFreshService) {
        this.getLeadForAgentservice = getLeadForAgentservice;
        this.clFreshService = clFreshService;
    }

    @PostMapping("/getlead")
    public TMSResponse<List<GetLeadForAgentDto>> getLead(@Valid @RequestBody GetLeadfor getLeadfor) throws TMSException {
        List<GetLeadForAgentDto> result = getLeadForAgentservice.getLeadforAgent(getLeadfor);
        return TMSResponse.buildResponse(result);
    }

    @PostMapping("/setlead")
    public TMSResponse<Boolean> createScheduleUpdate(@Valid @RequestBody SetLeadStatus setLeadStatus) throws TMSException {
        boolean result = getLeadForAgentservice.setLeadForAgent(setLeadStatus);
        return TMSResponse.buildResponse(result);
    }

    @PutMapping("fresh")
    public TMSResponse<Boolean> updateClFresh(
                                              @Valid @RequestBody UpdClFresh updClFresh) throws TMSException {
        boolean result = clFreshService.updClFreshAfterValidSaleOrder(updClFresh);
        return TMSResponse.buildResponse(result);
    }
}
