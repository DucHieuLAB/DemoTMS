package com.tms.api.exception;

import lombok.Getter;

@Getter
public class TMSException extends Exception {
    private final ErrorMessage errorMessage;

    public TMSException(ErrorMessage errorMessage) {
        super();
        this.errorMessage = errorMessage;
    }

    public TMSException() {
        super();
        errorMessage = null;
    }
}
