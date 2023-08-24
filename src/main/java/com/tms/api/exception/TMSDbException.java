package com.tms.api.exception;

import com.tms.api.commons.ApiMessageError;
import lombok.Getter;

@Getter
public class TMSDbException extends TMSException {
    private ApiMessageError apiMessageError;

    public TMSDbException(ErrorMessage message) {
        super(message);
    }

    public TMSDbException(String errorMessage) {
        super(ErrorMessages.SAVE_DATABASE_ERROR);
        this.apiMessageError = new ApiMessageError(errorMessage);
    }

    public TMSDbException(ErrorMessage message, String errorMessage) {
        super(message);
        this.apiMessageError = new ApiMessageError(errorMessage);
    }
}
