package com.tms.api.controller;

import com.tms.api.commons.TMSResponse;
import com.tms.api.exception.TMSDbException;
import com.tms.api.exception.TMSException;
import com.tms.api.service.FillterLeadService;
import com.tms.dto.request.lead.GetLeadToFillter;
import com.tms.dto.request.lead.InsLeadAfterFillter;
import com.tms.dto.request.lead.InsListLeadAfterFillter;
import com.tms.dto.request.lead.UpdLeadFillter;
import com.tms.dto.response.LeadBasket;
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
    public TMSResponse<List<LeadBasket>> getLeadBasket(@Valid @RequestBody GetLeadToFillter getLeadToFillter) throws TMSException {
        List<LeadBasket> result = fillterLeadService.getListToFillter(getLeadToFillter);
        return TMSResponse.buildResponse(result);
    }

    @PostMapping
    public TMSResponse<Boolean> insLeadAfterFiller(@Valid @RequestBody List<InsLeadAfterFillter> insLeadAfterFillter) throws TMSDbException {
        boolean result = fillterLeadService.insLeadAfterFillter(insLeadAfterFillter);
        return TMSResponse.buildResponse(result);
    }
    @PutMapping
    public TMSResponse<Boolean> updateScheduleUpdate(@Valid @RequestBody UpdLeadFillter updLeadFillter) throws TMSException {
        Boolean result = fillterLeadService.updLeadFillter(updLeadFillter);
        return TMSResponse.buildResponse(result);
    }

}
