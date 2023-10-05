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
@RequestMapping("validate_sale-orders")
public class ValidateSoController {
    private final ValidateSoService validateSoService;

    public ValidateSoController(ValidateSoService validateSoService) {
        this.validateSoService = validateSoService;
    }

    @PutMapping("/")
    public TMSResponse<Boolean> validateSo(@Valid @RequestBody ValidSaleOrder validSaleOrder) throws TMSException {

        boolean isvalid = validate(validSaleOrder);
        boolean result = false;
        if (isvalid){
             result = validateSoService.validSaleOrder(validSaleOrder);
        }
        return TMSResponse.buildResponse(result);
    }

    private boolean validate(ValidSaleOrder validSaleOrder) throws TMSException {
        // Check valid status
        if (!EnumType.SaleOrder.isStatusInEnum(validSaleOrder.getUpdSaleOrder().getStatus())) {
            ApiValidatorError validatorError = ApiValidatorError.builder().field("status")
                    .rejectValue(validSaleOrder.getUpdSaleOrder().getStatus())
                    .message(MessageConst.SO_INVALID_STATUS)
                    .build();
            throw new TMSInvalidInputException(ErrorMessages.INVALID_VALUE, validatorError);
        }
        // SO status can be NEW
        if (validSaleOrder.getUpdSaleOrder().getStatus() == EnumType.SaleOrder.NEW.getStatus()) {
            ApiValidatorError validatorError = ApiValidatorError.builder().field("status")
                    .rejectValue(validSaleOrder.getUpdSaleOrder().getStatus())
                    .message(MessageConst.STATUS_NEW_IS_NOT_VALID)
                    .build();
            throw new TMSInvalidInputException(ErrorMessages.INVALID_VALUE, validatorError);
        }
        // SO have Cancel status need reson
        if (validSaleOrder.getUpdSaleOrder().getStatus() == EnumType.SaleOrder.CANCEL.getStatus()) {
            if (validSaleOrder.getUpdSaleOrder().getReason() == null || validSaleOrder.getUpdSaleOrder().getReason().equals("")) {
                ApiValidatorError validatorError = ApiValidatorError.builder().field("reason")
                        .rejectValue(validSaleOrder.getUpdSaleOrder().getReason())
                        .message(MessageConst.SO_REASON_IS_INVALID)
                        .build();
                throw new TMSInvalidInputException(ErrorMessages.INVALID_VALUE, validatorError);
            }
        }
        // SO have Pending status need reson
        if (validSaleOrder.getUpdSaleOrder().getStatus() == EnumType.SaleOrder.PENDING.getStatus()) {
            if (validSaleOrder.getUpdSaleOrder().getReason() == null || validSaleOrder.getUpdSaleOrder().getReason().equals("")) {
                ApiValidatorError validatorError = ApiValidatorError.builder().field("reason")
                        .rejectValue(validSaleOrder.getUpdSaleOrder().getReason())
                        .message(MessageConst.SO_REASON_IS_INVALID)
                        .build();
                throw new TMSInvalidInputException(ErrorMessages.INVALID_VALUE, validatorError);
            }
        }
        // When update order UpdateClFresh can't null
        if (validSaleOrder.isUpdateProduct()) {
            if (validSaleOrder.getUpdClFresh() == null) {
                ApiValidatorError validatorError = ApiValidatorError.builder().field("UpdClFresh")
                        .rejectValue(validSaleOrder.getUpdSaleOrder().getReason())
                        .message(MessageConst.ERROL_LEAD_UPDATE_IS_EMPTY)
                        .build();
                throw new TMSInvalidInputException(ErrorMessages.INVALID_VALUE, validatorError);
            }
        }
        return true;
    }


}
