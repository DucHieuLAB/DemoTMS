package com.tms.dto.request.saleOrder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tms.DaoConst;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetSaleOrder {
    @NotNull
    private Integer soId;
    private Integer orgId;
    private Integer cpId;
    private Integer agId;
    private Integer leadId;
    private String leadName;
    private String leadPhone;
    private Double amount;
    private Integer paymentMethod;
    private Integer status;
    private Integer createby;
    @Pattern(regexp = DaoConst.DATE_TIME_BETWEEN_REGEX, message = "{date-between.Pattern.message}")
    private String createdate;
    private Integer modifyby;
    @Pattern(regexp = DaoConst.DATE_TIME_BETWEEN_REGEX, message = "{date-between.Pattern.message}")
    private String modifydate;
    private Double amountDeposit;
    private Double amountPostpaid;
    private Double listPrice;
    private Integer discountLevel;
    private Integer discountType1;
    private Integer unit1;
    private Double discountCash1;
    private Double discountPercent1;
    private Integer discountType2;
    private Integer unit2;
    private Double discountCash2;
    private Double discountPercent2;
    private Integer discountType3;
    private Integer unit3;
    private Double discountCash3;
    private Double discountPercent3;
    private Integer discountType4;
    private Integer unit4;
    private Double discountCash4;
    private Double discountPercent4;
    private Boolean isValidated;
    @Pattern(regexp = DaoConst.DATE_TIME_BETWEEN_REGEX, message = "{date-between.Pattern.message}")
    private String creationDate;
    private Integer validateBy;
    private String reason;
    private String qaNote;
    @Pattern(regexp = DaoConst.DATE_TIME_BETWEEN_REGEX, message = "{date-between.Pattern.message}")
    private String appointmentDate;
    private Integer qtyTotal;
    private Integer qtySaleable;
    private String deliveryPackageCode;
    private String transactionId;
    private Double transactionFee;
    private String paymentTransactionId;
    private Double totalAmount;
    private Integer limit;
    private Integer offset;
}
