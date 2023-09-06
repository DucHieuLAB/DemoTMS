package com.tms.api.controller;

import com.tms.api.commons.TMSResponse;
import com.tms.api.exception.TMSException;
import com.tms.api.service.FillterLeadService;
import com.tms.dto.request.schedule.GetLeadToFillter;
import com.tms.dto.response.LeadBasket;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/fillter")
public class FillterLeadController {
    private final FillterLeadService fillterLeadService;

    public FillterLeadController(FillterLeadService fillterLeadService) {
        this.fillterLeadService = fillterLeadService;
    }

    @PostMapping("/Leads")
    public TMSResponse<List<LeadBasket>> getLeadBasket(@Valid @RequestBody GetLeadToFillter getLeadToFillter) throws TMSException {
        List<LeadBasket> result = fillterLeadService.getListToFillter(getLeadToFillter);
        return TMSResponse.buildResponse(result);
    }
}
