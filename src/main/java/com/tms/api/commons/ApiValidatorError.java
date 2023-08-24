package com.tms.api.commons;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ApiValidatorError implements ApiSubError, Serializable {
    private String field;
    private Object rejectValue;
    private String message;
}
