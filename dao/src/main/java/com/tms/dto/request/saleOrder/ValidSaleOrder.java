package com.tms.dto.request.saleOrder;

import com.tms.dto.request.clFresh.UpdClFresh;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ValidSaleOrder {
    @NotNull
    private UpdSaleOrder updSaleOrder;
    private UpdClFresh updClFresh;
    private boolean isUpdateProduct;
}
