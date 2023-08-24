package com.tms.api.exception;

import com.tms.api.commons.ApiMessageError;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TMSEntityNotFoundException extends TMSException {
    private ApiMessageError apiMessageError;

    public TMSEntityNotFoundException(ErrorMessage message) {
        super(message);
    }

    public TMSEntityNotFoundException(ErrorMessage message, ApiMessageError apiMessageError) {
        super(message);
        this.apiMessageError = apiMessageError;
    }
}
