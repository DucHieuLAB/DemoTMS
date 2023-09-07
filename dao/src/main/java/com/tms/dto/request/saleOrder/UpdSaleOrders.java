package com.tms.dto.request.saleOrder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdSaleOrders {
    private String json;
    private String timeZone;

    public UpdSaleOrders(){
        // set default for time zone
        this.timeZone = "Asia/Ho_Chi_Minh";
    }
}
