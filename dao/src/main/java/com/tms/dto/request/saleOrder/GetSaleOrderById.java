package com.tms.dto.request.saleOrder;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class GetSaleOrderById {
    private Integer soId;
    private Integer limit;
    private Integer offset;
}
