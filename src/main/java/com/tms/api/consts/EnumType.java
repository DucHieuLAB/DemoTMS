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
    public enum Filltter {
        DONE_FILLTER_VALUE("0"),
        GET_LEAD_FILLTER_VALUE("1");
        private final String value;
    }

    @Getter
    @AllArgsConstructor
    public enum Campaign {
        DISTRIBUTTION_RULE(82),
        CALLING_LIST(86);
        private final int type;
    }

    @Getter
    @AllArgsConstructor
    public enum LeadStatus {
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

    @Getter
    @AllArgsConstructor
    public enum SaleOrder {
        NEW(41),
        CANCEL(44),
        SUCCESS(45),
        PENDING(42),
        UNASIGNE(46),
        VALIDATED(43),
        DELAY(357),
        DO_CREATE_FAIL(48);

        private final int status;

        public static boolean isStatusInEnum(int status) {
            for (SaleOrder order : SaleOrder.values()) {
                if (order.getStatus() == status) {
                    return true;
                }
            }
            return false;
        }
    }

    @Getter
    @AllArgsConstructor
    public enum DeliveryOrder {
        NEW(51);
        private final int status;
    }
}
