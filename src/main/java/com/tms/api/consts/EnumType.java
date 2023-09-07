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
    @Getter
    @AllArgsConstructor
    public enum LeadStatus{
        NEW(1),
        APPROVED(2),
        REJECTED(3),
        DUPLICATED(4),
        TRASH(5),
        CLOSED(6),
        UNREACHABLE(7),
        CALLBACKCONSULTING(8),
        CALLBACKPOTPROSPECT(9),
        BUSY(10),
        NOANSWER(11),
        URGENT(12),
        CALLBACKPOTENTIAL(14);
        private final int status;
    }
}
