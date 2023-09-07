package com.tms.api.controller;

import com.tms.api.commons.ApiValidatorError;
import com.tms.api.commons.TMSResponse;
import com.tms.api.consts.MessageConst;
import com.tms.api.exception.ErrorMessages;
import com.tms.api.exception.TMSException;
import com.tms.api.exception.TMSInvalidInputException;
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

    @PutMapping("/{id}")
    public TMSResponse<Boolean> updateScheduleUpdate(@PathVariable int id,
                                                     @Valid @RequestBody UpdSaleOrder updSaleOrder) throws TMSException {
        if (id != updSaleOrder.getSoId()) {
            ApiValidatorError validatorError = ApiValidatorError.builder()
                    .field("id")
                    .rejectValue(updSaleOrder.getSoId())
                    .message(MessageConst.NOT_MATCH_VALUE_IN_URL)
                    .build();
            throw new TMSInvalidInputException(ErrorMessages.INVALID_VALUE, validatorError);
        }
        boolean result = saleOrderService.updSaleOrder(id,updSaleOrder);
        return TMSResponse.buildResponse(result);
    }
}
