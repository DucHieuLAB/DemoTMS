package com.tms.api.controller;

import com.tms.api.commons.TMSResponse;
import com.tms.api.exception.TMSException;
import com.tms.api.service.SaleOrderService;
import com.tms.dto.request.saleOrder.UpdSaleOrder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("sale-orders")
public class SaleOrderController {

    private final SaleOrderService saleOrderService;

    public SaleOrderController(SaleOrderService saleOrderService) {
        this.saleOrderService = saleOrderService;
    }

    @PutMapping("")
    public TMSResponse<Boolean> updateSaleOrder(@Valid @RequestBody UpdSaleOrder updSaleOrder) throws TMSException {
        boolean result = saleOrderService.updSaleOrder(updSaleOrder);
        return TMSResponse.buildResponse(result);
    }
}
