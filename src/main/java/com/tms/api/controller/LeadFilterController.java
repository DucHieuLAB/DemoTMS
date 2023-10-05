package com.tms.api.controller;

import com.tms.api.commons.TMSResponse;
import com.tms.api.exception.TMSException;
import com.tms.api.service.ClBasketService;
import com.tms.dto.request.clBasket.GetLeadToFillter;
import com.tms.dto.response.ClBasket;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/lead-fillter")
public class LeadFilterController {
    private final ClBasketService clBasketService;

    public LeadFilterController(ClBasketService clBasketService) {
        this.clBasketService = clBasketService;
    }

    @PostMapping("/leads")
    public TMSResponse<List<ClBasket>> getLeadBasket(@Valid @RequestBody GetLeadToFillter getLeadToFillter) throws TMSException {
        List<ClBasket> result = clBasketService.getListToFillter(getLeadToFillter);
        return TMSResponse.buildResponse(result);
    }
}
