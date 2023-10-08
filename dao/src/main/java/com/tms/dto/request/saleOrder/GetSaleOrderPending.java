package com.tms.dto.request.saleOrder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetSaleOrderPending {
    private Integer limit;
    private Integer offset;
}
