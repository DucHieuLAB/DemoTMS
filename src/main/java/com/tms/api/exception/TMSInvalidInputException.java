package com.tms.api.exception;

import com.tms.api.commons.ApiSubError;
import lombok.Getter;

import java.util.Collections;
import java.util.List;

@Getter
public class TMSInvalidInputException extends TMSException {
    List<ApiSubError> apiSubErrors;

    public TMSInvalidInputException(ErrorMessage message) {
        super(message);
    }

    public TMSInvalidInputException(ErrorMessage message, ApiSubError apiSubError) {
        super(message);
        this.apiSubErrors = Collections.singletonList(apiSubError);
    }

    public TMSInvalidInputException(ErrorMessage message, List<ApiSubError> apiSubErrors) {
        super(message);
        this.apiSubErrors = apiSubErrors;
    }
}
