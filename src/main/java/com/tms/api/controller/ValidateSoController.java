package com.tms.api.controller;

import com.tms.api.commons.ApiValidatorError;
import com.tms.api.commons.TMSResponse;
import com.tms.api.consts.EnumType;
import com.tms.api.consts.MessageConst;
import com.tms.api.exception.ErrorMessages;
import com.tms.api.exception.TMSException;
import com.tms.api.exception.TMSInvalidInputException;
import com.tms.api.service.ValidateSoService;
import com.tms.dto.request.saleOrder.ValidSaleOrder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("validate_sale_orders")
public class ValidateSoController {
    private final ValidateSoService validateSoService;

    public ValidateSoController(ValidateSoService validateSoService) {
        this.validateSoService = validateSoService;
    }

    @PutMapping("")
    public TMSResponse<Boolean> validateSo(@Valid @RequestBody ValidSaleOrder validSaleOrder) throws TMSException {
         validate(validSaleOrder);
        boolean result = validateSoService.validSaleOrder(validSaleOrder);
        return TMSResponse.buildResponse(result);
    }

    private void validate(ValidSaleOrder validSaleOrder) throws TMSException {
        int status = validSaleOrder.getUpdSaleOrder().getStatus();
        String reason = validSaleOrder.getUpdSaleOrder().getReason();
        boolean isUpdateProduct = false;
        if (validSaleOrder.getIsUpdateProduct() != null){
            isUpdateProduct = validSaleOrder.getIsUpdateProduct();
        }
        if (!EnumType.SaleOrder.isStatusInEnum(status)) {
            createAndThrowException("status", status, MessageConst.SO_INVALID_STATUS);
        }

        if (status == EnumType.SaleOrder.NEW.getStatus()) {
            createAndThrowException("status", status, MessageConst.STATUS_NEW_IS_NOT_VALID);
        }

        if (status == EnumType.SaleOrder.CANCEL.getStatus() || status == EnumType.SaleOrder.PENDING.getStatus()) {
            if (reason == null || reason.isEmpty()) {
                createAndThrowException("reason", reason, MessageConst.SO_REASON_IS_INVALID);
            }
        }

        if (status == EnumType.SaleOrder.DELAY.getStatus() && validSaleOrder.getUpdSaleOrder().getCreationDate() == null) {
            if (reason == null || reason.isEmpty()) {
                createAndThrowException("creation date", reason, MessageConst.ERROR_MESSAGE_VALIDATED_DELAY_CREATION_DATE_REQUIRED);
            }
        }

        if (isUpdateProduct && validSaleOrder.getUpdClFresh() == null) {
            createAndThrowException("UpdClFresh", reason, MessageConst.ERROL_LEAD_UPDATE_IS_EMPTY);
        }
    }

    private void createAndThrowException(String fieldName, Object rejectValue, String message) throws TMSException {
        ApiValidatorError validatorError = ApiValidatorError.builder().field(fieldName)
                .rejectValue(rejectValue)
                .message(message)
                .build();
        throw new TMSInvalidInputException(ErrorMessages.INVALID_VALUE, validatorError);
    }

}
