package com.tms.dto.request.ClFreshGetLead;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SoSaleOderInsert {
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
    private String createDate;
    private Integer modifyBy;
    private String modifyDate;
    private BigDecimal amountDeposit;
    private BigDecimal amountPostpaid;
    private String updateDate;
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
    private Integer qtyTotal;
    private Integer qtySaleable;
    private String deliveryPackageCode;
}
    // private Integer orgId;
    // private Integer leadId;
    // private String leadName;
    // private String leadPhone;
    // private Integer paymentMethod;