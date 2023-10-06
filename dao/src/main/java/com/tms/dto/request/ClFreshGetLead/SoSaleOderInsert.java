package com.tms.dto.request.ClFreshGetLead;
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
    private Double amount;
    private Integer paymentMethod;
    private Integer status;
    private Integer createBy;
    private String createDate;
    private Integer modifyBy;
    private String modifyDate;
    private Integer amountDeposit;
    private Integer amountPostpaid;
    private String updateDate;
    private Integer listPrice;
    private Integer discountLevel;
    private Integer discountType1;
    private Integer unit1;
    private Double discountCash1;
    private Double discountPercent1;
    private Integer discountType2;
    private Integer unit2;
    private Integer discountCash2;
    private Integer discountPercent2;
    private Integer discountType3;
    private Integer unit3;
    private Double discountCash3;
    private Double discountPercent3;
    private Integer discountType4;
    private Integer unit4;
    private Double discountCash4;
    private Double discountPercent4;
    private Integer qtyTotal;
    private Integer qtySaleable;
    private String deliveryPackageCode;
}
    // private Integer orgId;
    // private Integer leadId;
    // private String leadName;
    // private String leadPhone;
    // private Integer paymentMethod;