package com.tms.api.controller;

import com.tms.api.commons.ApiValidatorError;
import com.tms.api.commons.TMSResponse;
import com.tms.api.consts.MessageConst;
import com.tms.api.exception.ErrorMessages;
import com.tms.api.exception.TMSException;
import com.tms.api.exception.TMSInvalidInputException;
import com.tms.api.service.FillterLeadService;
import com.tms.dto.request.schedule.GetLeadToFillter;
import com.tms.dto.request.schedule.UpdLeadFillter;
import com.tms.dto.request.schedule.UpdScheduleUpdate;
import com.tms.dto.response.LeadBasket;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping
    public TMSResponse<Boolean> updateScheduleUpdate(@Valid @RequestBody UpdLeadFillter updLeadFillter) throws TMSException {
        Boolean result = fillterLeadService.updLeadFillter(updLeadFillter);
        return TMSResponse.buildResponse(result);
    }

}
