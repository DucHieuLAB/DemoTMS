package com.tms.api.controller;

import com.tms.api.commons.TMSResponse;
import com.tms.api.exception.TMSDbException;
import com.tms.api.exception.TMSException;
import com.tms.api.service.FillterLeadService;
import com.tms.dto.request.lead.GetLeadToFillter;
import com.tms.dto.request.lead.ClFresh;
import com.tms.dto.response.ClBasket;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/fillter")
public class LeadFillterController {
    private final FillterLeadService fillterLeadService;

    public LeadFillterController(FillterLeadService fillterLeadService) {
        this.fillterLeadService = fillterLeadService;
    }

    @PostMapping("/Leads")
    public TMSResponse<List<ClBasket>> getLeadBasket(@Valid @RequestBody GetLeadToFillter getLeadToFillter) throws TMSException {
        List<ClBasket> result = fillterLeadService.getListToFillter(getLeadToFillter);
        return TMSResponse.buildResponse(result);
    }



}
