package com.tms.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class SaleOrder implements Serializable {
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
    private LocalDateTime createdate;
    private Integer modifyby;
    private LocalDateTime modifydate;
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
    private LocalDate creationDate;
    private Integer validateBy;
    private String reason;
    private String qaNote;
    private LocalDate appointmentDate;
    private Integer qtyTotal;
    private Integer qtySaleable;
    private String deliveryPackageCode;
    private String transactionId;
    private Double transactionFee;
    private String paymentTransactionId;
    private Double totalAmount;
}
