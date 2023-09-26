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
    public ValidateSoController(ValidateSoService validateSoService){
        this.validateSoService = validateSoService;
    }

    @PutMapping("/{id}")
    public TMSResponse<Boolean> validateSo(@PathVariable int id,
                                                     @Valid @RequestBody ValidSaleOrder validSaleOrder) throws TMSException {
        if (!EnumType.SaleOrder.isStatusInEnum(validSaleOrder.getUpdSaleOrder().getStatus())){
            ApiValidatorError validatorError = ApiValidatorError.builder().field("status")
                    .rejectValue(validSaleOrder.getUpdSaleOrder().getStatus())
                    .message(MessageConst.SO_INVALID_STATUS)
                    .build();
            throw new TMSInvalidInputException(ErrorMessages.INVALID_VALUE, validatorError);
        }
        if (id != validSaleOrder.getUpdSaleOrder().getSoId()) {
            ApiValidatorError validatorError = ApiValidatorError.builder().field("id")
                    .rejectValue(validSaleOrder.getUpdSaleOrder().getSoId())
                    .message(MessageConst.NOT_MATCH_VALUE_IN_URL)
                    .build();
            throw new TMSInvalidInputException(ErrorMessages.INVALID_VALUE, validatorError);
        }
        // Các SO sẽ được set 1 trong số các trạng thái: pending, cancel, validated, unassigned, delay
        if (validSaleOrder.getUpdSaleOrder().getStatus() == EnumType.SaleOrder.NEW.getStatus()) {
            ApiValidatorError validatorError = ApiValidatorError.builder().field("status")
                    .rejectValue(validSaleOrder.getUpdSaleOrder().getStatus())
                    .message(MessageConst.STATUS_NEW_IS_NOT_VALID)
                    .build();
            throw new TMSInvalidInputException(ErrorMessages.INVALID_VALUE, validatorError);
        }
        //Các SO được set trạng thái Cancel cần có lý do
        if (validSaleOrder.getUpdSaleOrder().getStatus() == EnumType.SaleOrder.CANCEL.getStatus()) {
            if (validSaleOrder.getUpdSaleOrder().getReason() == null || validSaleOrder.getUpdSaleOrder().getReason().equals("")) {
                ApiValidatorError validatorError = ApiValidatorError.builder().field("reason")
                        .rejectValue(validSaleOrder.getUpdSaleOrder().getReason())
                        .message(MessageConst.SO_REASON_IS_INVALID)
                        .build();
                throw new TMSInvalidInputException(ErrorMessages.INVALID_VALUE, validatorError);
            }
        }
        //Các SO chưa thể xử lý ngay sẽ được set trạng thái pending, kèm theo lý do
        if (validSaleOrder.getUpdSaleOrder().getStatus() == EnumType.SaleOrder.PENDING.getStatus()) {
            if (validSaleOrder.getUpdSaleOrder().getReason() == null || validSaleOrder.getUpdSaleOrder().getReason().equals("")) {
                ApiValidatorError validatorError = ApiValidatorError.builder().field("reason")
                        .rejectValue(validSaleOrder.getUpdSaleOrder().getReason())
                        .message(MessageConst.SO_REASON_IS_INVALID)
                        .build();
                throw new TMSInvalidInputException(ErrorMessages.INVALID_VALUE, validatorError);
            }
        }
        // Khi validate SO, QA có thể chỉnh sửa thông tin của đơn hàng (địa chỉ, sản phẩm, số lượng sp, sđt, ...)
        if (validSaleOrder.isUpdateProduct() == true){
            if(validSaleOrder.getUpdClFresh() == null){
                ApiValidatorError validatorError = ApiValidatorError.builder().field("UpdClFresh")
                        .rejectValue(validSaleOrder.getUpdSaleOrder().getReason())
                        .message(MessageConst.NOT_FOUND_WITH_OBJECT_PARAMS)
                        .build();
                throw new TMSInvalidInputException(ErrorMessages.INVALID_VALUE, validatorError);
            }
        }
        boolean result = validateSoService.validSaleOrder(id,validSaleOrder);
        return TMSResponse.buildResponse(result);
    }

}
