package com.tms.api.commons;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class ApiMessageError implements ApiSubError, Serializable {
    private final String errorMessage;

    public ApiMessageError(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
