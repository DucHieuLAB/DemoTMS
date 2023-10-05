package com.tms.dto.request.saleOrder;

import com.tms.DaoConst;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InsSaleOrder {
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
    @NotNull
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

    @Override
    public String toString() {
        return "(" + (soId == null ? "nextval('seq_so_id')":soId) +
                "," + orgId +
                "," + cpId +
                "," + agId +
                "," + leadId +
                "," + (leadName == null ? "null":"'"+leadName+"'")  +
                "," + (leadPhone == null ? "null":"'"+leadPhone+"'")  +
                "," + amount +
                "," + paymentMethod +
                "," + status +
                "," + createBy +
                "," + (createDate == null? "null": "'"+createDate+"'") +
                "," + modifyBy +
                "," + (modifyDate == null ? "null":"'"+modifyDate+"'")  +
                "," + amountDeposit +
                "," + amountPostpaid +
                "," + listPrice +
                "," + discountLevel +
                "," + discountType1 +
                "," + unit1 +
                "," + discountCash1 +
                "," + discountPercent1 +
                "," + discountType2 +
                "," + unit2 +
                "," + discountCash2 +
                "," + discountPercent2 +
                "," + discountType3 +
                "," + unit3 +
                "," + discountCash3 +
                "," + discountPercent3 +
                "," + discountType4 +
                "," + unit4 +
                "," + discountCash4 +
                "," + discountPercent4 +
                "," + isValidated +
                "," + (creationDate==null?"null":"'"+creationDate+"'") +
                "," + validateBy +
                "," + (reason==null? "null": "'"+reason+"'") +
                "," + (qaNote == null ? "null":"'"+qaNote+"'") +
                "," + (appointmentDate==null? "null":"'"+appointmentDate+"'") +
                "," + qtyTotal +
                "," + qtySaleable +
                "," + (deliveryPackageCode== null?"null":"'"+deliveryPackageCode+"'") +
                "," + (transactionId==null?"null":"'"+transactionId+"'") +
                "," + transactionFee +
                "," + (paymentTransactionId==null?"null":"'"+paymentTransactionId+"'") +
                ')';
    }
}
