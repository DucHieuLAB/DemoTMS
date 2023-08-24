package com.tms.api.controller;

import com.tms.api.commons.ApiValidatorError;
import com.tms.api.commons.TMSResponse;
import com.tms.api.consts.MessageConst;
import com.tms.api.exception.ErrorMessages;
import com.tms.api.exception.TMSDbException;
import com.tms.api.exception.TMSException;
import com.tms.api.exception.TMSInvalidInputException;
import com.tms.api.service.ScheduleUpdateService;
import com.tms.dto.request.schedule.GetScheduleUpdate;
import com.tms.dto.request.schedule.InsScheduleUpdate;
import com.tms.dto.request.schedule.UpdScheduleUpdate;
import com.tms.dto.response.ScheduleUpdate;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/schedules")
public class ScheduleUpdateController {
    private final ScheduleUpdateService scheduleUpdateService;

    public ScheduleUpdateController(ScheduleUpdateService scheduleUpdateService) {
        this.scheduleUpdateService = scheduleUpdateService;
    }

    @PostMapping("/search")
    public TMSResponse<List<ScheduleUpdate>> getScheduleUpdate(@Valid @RequestBody GetScheduleUpdate getScheduleUpdate) throws TMSException {
        List<ScheduleUpdate> result = scheduleUpdateService.getScheduleUpdate(getScheduleUpdate);
        return TMSResponse.buildResponse(result);
    }

    @PostMapping
    public TMSResponse<Boolean> createScheduleUpdate(@Valid @RequestBody InsScheduleUpdate insScheduleUpdate) throws TMSDbException {
        boolean result = scheduleUpdateService.insScheduleUpdate(insScheduleUpdate);
        return TMSResponse.buildResponse(result);
    }

    @PutMapping("/{id}")
    public TMSResponse<Boolean> updateScheduleUpdate(@PathVariable int id,
                                                     @Valid @RequestBody UpdScheduleUpdate updScheduleUpdate) throws TMSException {
        if (id != updScheduleUpdate.getId()) {
            ApiValidatorError validatorError = ApiValidatorError.builder()
                    .field("id")
                    .rejectValue(updScheduleUpdate.getId())
                    .message(MessageConst.NOT_MATCH_VALUE_IN_URL)
                    .build();
            throw new TMSInvalidInputException(ErrorMessages.INVALID_VALUE, validatorError);
        }
        boolean result = scheduleUpdateService.updScheduleUpdate(id, updScheduleUpdate);
        return TMSResponse.buildResponse(result);
    }
}
