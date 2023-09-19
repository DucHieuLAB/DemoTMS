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
    public enum Filltter{
        DONE_FILLTER_VALUE("1"),
        GET_LEAD_FILLTER_VALUE("0");
        private final String value;
    }

    @Getter
    @AllArgsConstructor
    public enum Campaign{
        DISTRIBUTTION_RULE(82),
        CALLING_LIST(86);
        private final int type;
    }

    @Getter
    @AllArgsConstructor
    public enum LeadStatus{
        NEW(1),
        TRASH(5);
        private final int type;
    }
}
