package com.tms.api.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import javax.validation.Valid;

import com.tms.api.commons.ApiValidatorError;
import com.tms.api.consts.MessageConst;
import com.tms.api.exception.ErrorMessages;
import com.tms.api.exception.TMSInvalidInputException;
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
@RequestMapping("api/v1/clfresh")
public class FreshController {
    private final GetLeadForAgentService getLeadForAgentService;

    private final ClFreshService clFreshService;

    public FreshController(GetLeadForAgentService getLeadForAgentservice, ClFreshService clFreshService) {
        this.getLeadForAgentService = getLeadForAgentservice;
        this.clFreshService = clFreshService;
    }
     @PostMapping("/getlead")
    public TMSResponse<List<GetLeadForAgentDto>> getLead(@Valid @RequestBody GetLeadfor getLeadfor )throws TMSException{
        List<GetLeadForAgentDto> result= getLeadForAgentService.getLeadForAgent(getLeadfor);
        return TMSResponse.buildResponse(result);
    }

    @PostMapping("/setlead/{id}")
    public TMSResponse<Boolean> createScheduleUpdate(@PathVariable int id,@Valid @RequestBody SetLeadStatus setLeadStatus) throws TMSException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        if (id != setLeadStatus.getSetLeadFresh().getLeadId()) {
            ApiValidatorError validatorError = ApiValidatorError.builder()
                    .field("id")
                    .rejectValue(id != setLeadStatus.getSetLeadFresh().getLeadId())
                    .message(MessageConst.NOT_MATCH_VALUE_IN_URL)
                    .build();
            throw new TMSInvalidInputException(ErrorMessages.INVALID_VALUE, validatorError);
        }
        boolean result = getLeadForAgentService.updLead(id,setLeadStatus);
        return TMSResponse.buildResponse(result);
    }
}
