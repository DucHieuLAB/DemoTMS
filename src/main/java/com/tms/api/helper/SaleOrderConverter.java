package com.tms.api.helper;

import com.tms.dto.request.saleOrder.InsSaleOrder;
import com.tms.dto.request.saleOrder.UpdSaleOrder;
import com.tms.dto.response.SaleOrder;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class SaleOrderConverter {
    public static List<UpdSaleOrder> convertToUpdSaleOrderList(List<SaleOrder> saleOrders) {
        return saleOrders.stream()
                .map(saleOrder -> {
                    UpdSaleOrder updSaleOrder = new UpdSaleOrder();
                    updSaleOrder.setSoId(saleOrder.getSoId());
                    updSaleOrder.setOrgId(saleOrder.getOrgId());
                    updSaleOrder.setCpId(saleOrder.getCpId());
                    updSaleOrder.setAgId(saleOrder.getAgId());
                    updSaleOrder.setLeadId(saleOrder.getLeadId());
                    updSaleOrder.setLeadName(saleOrder.getLeadName());
                    updSaleOrder.setLeadPhone(saleOrder.getLeadPhone());
                    updSaleOrder.setAmount(saleOrder.getAmount() == null ? null : BigDecimal.valueOf(saleOrder.getAmount()));
                    updSaleOrder.setPaymentMethod(saleOrder.getPaymentMethod());
                    updSaleOrder.setStatus(saleOrder.getStatus());
                    updSaleOrder.setCreateBy(saleOrder.getCreateby());
                    updSaleOrder.setCreateDate(saleOrder.getCreatedate() == null ? null : DateHelper.toDateTime(saleOrder.getCreatedate()));
                    updSaleOrder.setModifyBy(saleOrder.getModifyby());
                    updSaleOrder.setModifyDate(saleOrder.getModifydate() == null ? null : DateHelper.toDateTime(saleOrder.getModifydate()));
                    updSaleOrder.setAmountDeposit(saleOrder.getAmountDeposit() == null ? null : BigDecimal.valueOf(saleOrder.getAmountDeposit()));
                    updSaleOrder.setAmountPostpaid(saleOrder.getAmountPostpaid() == null ? null : BigDecimal.valueOf(saleOrder.getAmountPostpaid()));
                    updSaleOrder.setListPrice(saleOrder.getListPrice() == null ? null : BigDecimal.valueOf(saleOrder.getListPrice()));
                    updSaleOrder.setDiscountLevel(saleOrder.getDiscountLevel());
                    updSaleOrder.setDiscountType1(saleOrder.getDiscountType1());
                    updSaleOrder.setUnit1(saleOrder.getUnit1());
                    updSaleOrder.setDiscountCash1(saleOrder.getDiscountCash1() == null ? null : BigDecimal.valueOf(saleOrder.getDiscountCash1()));
                    updSaleOrder.setDiscountPercent1(saleOrder.getDiscountPercent1() == null ? null : BigDecimal.valueOf(saleOrder.getDiscountPercent1()));
                    updSaleOrder.setDiscountType2(saleOrder.getDiscountType2());
                    updSaleOrder.setUnit2(saleOrder.getUnit2());
                    updSaleOrder.setDiscountCash2(saleOrder.getDiscountCash2() == null ? null : BigDecimal.valueOf(saleOrder.getDiscountCash2()));
                    updSaleOrder.setDiscountPercent2(saleOrder.getDiscountPercent2() == null ? null : BigDecimal.valueOf(saleOrder.getDiscountPercent2()));
                    updSaleOrder.setDiscountType3(saleOrder.getDiscountType3());
                    updSaleOrder.setUnit3(saleOrder.getUnit3());
                    updSaleOrder.setDiscountCash3(saleOrder.getDiscountCash3() == null ? null : BigDecimal.valueOf(saleOrder.getDiscountCash3()));
                    updSaleOrder.setDiscountPercent3(saleOrder.getDiscountPercent3() == null ? null : BigDecimal.valueOf(saleOrder.getDiscountPercent3()));
                    updSaleOrder.setDiscountType4(saleOrder.getDiscountType4());
                    updSaleOrder.setUnit4(saleOrder.getUnit4());
                    updSaleOrder.setDiscountCash4(saleOrder.getDiscountCash4() == null ? null : BigDecimal.valueOf(saleOrder.getDiscountCash4()));
                    updSaleOrder.setDiscountPercent4(saleOrder.getDiscountPercent4() == null ? null : BigDecimal.valueOf(saleOrder.getDiscountPercent4()));
                    updSaleOrder.setIsValidated(saleOrder.getIsValidated());
                    updSaleOrder.setCreationDate(saleOrder.getCreationDate().toString());
                    updSaleOrder.setValidateBy(saleOrder.getValidateBy());
                    updSaleOrder.setReason(saleOrder.getReason());
                    updSaleOrder.setQaNote(saleOrder.getQaNote());
                    updSaleOrder.setAppointmentDate(saleOrder.getAppointmentDate() == null ? null :DateHelper.toDbDate(saleOrder.getAppointmentDate()));
                    updSaleOrder.setQtyTotal(saleOrder.getQtyTotal());
                    updSaleOrder.setQtySaleable(saleOrder.getQtySaleable());
                    updSaleOrder.setDeliveryPackageCode(saleOrder.getDeliveryPackageCode());
                    updSaleOrder.setTransactionId(saleOrder.getTransactionId());
                    updSaleOrder.setTransactionFee(saleOrder.getTransactionFee() == null ? null : BigDecimal.valueOf(saleOrder.getTransactionFee()));
                    updSaleOrder.setPaymentTransactionId(saleOrder.getPaymentTransactionId());
                    return updSaleOrder;
                })
                .collect(Collectors.toList());
    }

    public static InsSaleOrder convertSaleOrderToInsSaleOrder(SaleOrder saleOrder) {
        InsSaleOrder insSaleOrder = new InsSaleOrder();

        // Map fields from SaleOrder to InsSaleOrder
        insSaleOrder.setSoId(saleOrder.getSoId());
        insSaleOrder.setOrgId(saleOrder.getOrgId());
        insSaleOrder.setCpId(saleOrder.getCpId());
        insSaleOrder.setAgId(saleOrder.getAgId());
        insSaleOrder.setLeadId(saleOrder.getLeadId());
        insSaleOrder.setLeadName(saleOrder.getLeadName());
        insSaleOrder.setLeadPhone(saleOrder.getLeadPhone());
        insSaleOrder.setAmount(BigDecimal.valueOf(saleOrder.getAmount()));
        insSaleOrder.setPaymentMethod(saleOrder.getPaymentMethod());
        insSaleOrder.setStatus(saleOrder.getStatus());
        insSaleOrder.setCreateBy(saleOrder.getCreateby());
        insSaleOrder.setCreateDate(saleOrder.getCreatedate().toString()); // Assuming it's a String in InsSaleOrder
        insSaleOrder.setModifyBy(saleOrder.getModifyby());
        insSaleOrder.setModifyDate(saleOrder.getModifydate().toString()); // Assuming it's a String in InsSaleOrder
        insSaleOrder.setAmountDeposit(BigDecimal.valueOf(saleOrder.getAmountDeposit()));
        insSaleOrder.setAmountPostpaid(BigDecimal.valueOf(saleOrder.getAmountPostpaid()));
        insSaleOrder.setListPrice(BigDecimal.valueOf(saleOrder.getListPrice()));
        insSaleOrder.setDiscountLevel(saleOrder.getDiscountLevel());
        insSaleOrder.setDiscountType1(saleOrder.getDiscountType1());
        insSaleOrder.setUnit1(saleOrder.getUnit1());
        insSaleOrder.setDiscountCash1(BigDecimal.valueOf(saleOrder.getDiscountCash1()));
        insSaleOrder.setDiscountPercent1(BigDecimal.valueOf(saleOrder.getDiscountPercent1()));
        insSaleOrder.setDiscountType2(saleOrder.getDiscountType2());
        insSaleOrder.setUnit2(saleOrder.getUnit2());
        insSaleOrder.setDiscountCash2(BigDecimal.valueOf(saleOrder.getDiscountCash2()));
        insSaleOrder.setDiscountPercent2(BigDecimal.valueOf(saleOrder.getDiscountPercent2()));
        insSaleOrder.setDiscountType3(saleOrder.getDiscountType3());
        insSaleOrder.setUnit3(saleOrder.getUnit3());
        insSaleOrder.setDiscountCash3(BigDecimal.valueOf(saleOrder.getDiscountCash3()));
        insSaleOrder.setDiscountPercent3(BigDecimal.valueOf(saleOrder.getDiscountPercent3()));
        insSaleOrder.setDiscountType4(saleOrder.getDiscountType4());
        insSaleOrder.setUnit4(saleOrder.getUnit4());
        insSaleOrder.setDiscountCash4(BigDecimal.valueOf(saleOrder.getDiscountCash4()));
        insSaleOrder.setDiscountPercent4(BigDecimal.valueOf(saleOrder.getDiscountPercent4()));
        insSaleOrder.setIsValidated(saleOrder.getIsValidated());
        insSaleOrder.setCreationDate(saleOrder.getCreationDate().toString()); // Assuming it's a String in InsSaleOrder
        insSaleOrder.setValidateBy(saleOrder.getValidateBy());
        insSaleOrder.setReason(saleOrder.getReason());
        insSaleOrder.setQaNote(saleOrder.getQaNote());
        insSaleOrder.setAppointmentDate(saleOrder.getAppointmentDate().toString()); // Assuming it's a String in InsSaleOrder
        insSaleOrder.setQtyTotal(saleOrder.getQtyTotal());
        insSaleOrder.setQtySaleable(saleOrder.getQtySaleable());
        insSaleOrder.setDeliveryPackageCode(saleOrder.getDeliveryPackageCode());
        insSaleOrder.setTransactionId(saleOrder.getTransactionId());
        insSaleOrder.setTransactionFee(BigDecimal.valueOf(saleOrder.getTransactionFee()));
        insSaleOrder.setPaymentTransactionId(saleOrder.getPaymentTransactionId());

        return insSaleOrder;
    }
}
