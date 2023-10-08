package com.tms.api.controller;

import com.tms.api.commons.ApiValidatorError;
import com.tms.api.commons.TMSResponse;
import com.tms.api.consts.EnumType;
import com.tms.api.consts.MessageConst;
import com.tms.api.exception.ErrorMessages;
import com.tms.api.exception.TMSException;
import com.tms.api.exception.TMSInvalidInputException;
import com.tms.api.service.SaleOrderService;
import com.tms.dto.request.saleOrder.UpdSaleOrder;
import com.tms.dto.request.saleOrder.ValidSaleOrder;
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
