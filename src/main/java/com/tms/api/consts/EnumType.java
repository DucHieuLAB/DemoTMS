package com.tms.api.consts;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class EnumType {

    @Getter
    @AllArgsConstructor
    public enum DbStatusResp {
        SUCCESS(0),
        FAIL(1),
        ;

        private final int status;
    }
}
