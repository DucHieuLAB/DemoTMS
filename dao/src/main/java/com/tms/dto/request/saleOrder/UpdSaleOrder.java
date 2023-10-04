package com.tms.dto.request.saleOrder;

import com.tms.DaoConst;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

@Getter
@Setter
public class UpdSaleOrder {
    @NotNull
    private Integer soId;
    private Integer orgId;
    private Integer cpId;
    private Integer agId;
    private Integer leadId;
    private String leadName;
    private String leadPhone;
    private BigDecimal amount;
    private Integer paymentMethod;
    private Integer status;
    private Integer createBy;
    @Pattern(regexp = DaoConst.DATE_REGEX, message = "{date.Pattern.message}")
    private String createDate;
    private Integer modifyBy;
    @Pattern(regexp = DaoConst.DATE_REGEX, message = "{date.Pattern.message}")
    private String modifyDate;
    private BigDecimal amountDeposit;
    private BigDecimal amountPostpaid;
    private BigDecimal listPrice;
    private Integer discountLevel;
    private Integer discountType1;
    private Integer unit1;
    private BigDecimal discountCash1;
    private BigDecimal discountPercent1;
    private Integer discountType2;
    private Integer unit2;
    private BigDecimal discountCash2;
    private BigDecimal discountPercent2;
    private Integer discountType3;
    private Integer unit3;
    private BigDecimal discountCash3;
    private BigDecimal discountPercent3;
    private Integer discountType4;
    private Integer unit4;
    private BigDecimal discountCash4;
    private BigDecimal discountPercent4;
    private Boolean isValidated;
    @Pattern(regexp = DaoConst.DATE_REGEX, message = "{date.Pattern.message}")
    private String creationDate;
    private Integer validateBy;
    private String reason;
    private String qaNote;
    @Pattern(regexp = DaoConst.DATE_REGEX, message = "{date.Pattern.message}")
    private String appointmentDate;
    private Integer qtyTotal;
    private Integer qtySaleable;
    private String deliveryPackageCode;
    private String transactionId;
    private BigDecimal transactionFee;
    private String paymentTransactionId;
}
