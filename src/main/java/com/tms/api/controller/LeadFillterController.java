package com.tms.api.controller;

import com.tms.api.commons.TMSResponse;
import com.tms.api.exception.TMSException;
import com.tms.api.service.FillterLeadService;
import com.tms.dto.request.lead.GetLeadToFillter;
import com.tms.dto.request.lead.ClFresh;
import com.tms.dto.request.lead.UpdLeadFillter;
import com.tms.dto.response.LeadBasket;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/lead-fillter")
public class LeadFillterController {
    private final ClBasketService clBasketService;

    public LeadFillterController(ClBasketService clBasketService) {
        this.clBasketService = clBasketService;
    }

    @PostMapping("/leads")
    public TMSResponse<List<ClBasket>> getLeadBasket(@Valid @RequestBody GetLeadToFillter getLeadToFillter) throws TMSException {
        List<ClBasket> result = clBasketService.getListToFillter(getLeadToFillter);
        return TMSResponse.buildResponse(result);
    }

    @PostMapping
    public TMSResponse<Boolean> insLeadAfterFiller(@Valid @RequestBody List<ClFresh> CLFresh) throws TMSDbException {
        boolean result = fillterLeadService.insLeadAfterFillter(CLFresh);
        return TMSResponse.buildResponse(result);
    }
    @PutMapping
    public TMSResponse<Boolean> updateScheduleUpdate(@Valid @RequestBody UpdLeadFillter updLeadFillter) throws TMSException {
        Boolean result = fillterLeadService.updLeadFillter(updLeadFillter);
        return TMSResponse.buildResponse(result);
    }

}
