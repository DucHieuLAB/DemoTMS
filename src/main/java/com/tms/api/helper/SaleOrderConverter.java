package com.tms.api.helper;

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
                    updSaleOrder.setAmount(BigDecimal.valueOf(saleOrder.getAmount()));
                    updSaleOrder.setPaymentMethod(saleOrder.getPaymentMethod());
                    updSaleOrder.setStatus(saleOrder.getStatus());
                    updSaleOrder.setCreateBy(saleOrder.getCreateby());
                    updSaleOrder.setCreateDate(saleOrder.getCreatedate().toString());
                    updSaleOrder.setModifyBy(saleOrder.getModifyby());
                    updSaleOrder.setModifyDate(saleOrder.getModifydate().toString());
                    updSaleOrder.setAmountDeposit(BigDecimal.valueOf(saleOrder.getAmountDeposit()));
                    updSaleOrder.setAmountPostpaid(BigDecimal.valueOf(saleOrder.getAmountPostpaid()));
                    updSaleOrder.setListPrice(BigDecimal.valueOf(saleOrder.getListPrice()));
                    updSaleOrder.setDiscountLevel(saleOrder.getDiscountLevel());
                    updSaleOrder.setDiscountType1(saleOrder.getDiscountType1());
                    updSaleOrder.setUnit1(saleOrder.getUnit1());
                    updSaleOrder.setDiscountCash1(BigDecimal.valueOf(saleOrder.getDiscountCash1()));
                    updSaleOrder.setDiscountPercent1(BigDecimal.valueOf(saleOrder.getDiscountPercent1()));
                    updSaleOrder.setDiscountType2(saleOrder.getDiscountType2());
                    updSaleOrder.setUnit2(saleOrder.getUnit2());
                    updSaleOrder.setDiscountCash2(BigDecimal.valueOf(saleOrder.getDiscountCash2()));
                    updSaleOrder.setDiscountPercent2(BigDecimal.valueOf(saleOrder.getDiscountPercent2()));
                    updSaleOrder.setDiscountType3(saleOrder.getDiscountType3());
                    updSaleOrder.setUnit3(saleOrder.getUnit3());
                    updSaleOrder.setDiscountCash3(BigDecimal.valueOf(saleOrder.getDiscountCash3()));
                    updSaleOrder.setDiscountPercent3(BigDecimal.valueOf(saleOrder.getDiscountPercent3()));
                    updSaleOrder.setDiscountType4(saleOrder.getDiscountType4());
                    updSaleOrder.setUnit4(saleOrder.getUnit4());
                    updSaleOrder.setDiscountCash4(BigDecimal.valueOf(saleOrder.getDiscountCash4()));
                    updSaleOrder.setDiscountPercent4(BigDecimal.valueOf(saleOrder.getDiscountPercent4()));
                    updSaleOrder.setIsValidated(saleOrder.getIsValidated());
                    updSaleOrder.setCreationDate(saleOrder.getCreationDate().toString());
                    updSaleOrder.setValidateBy(saleOrder.getValidateBy());
                    updSaleOrder.setReason(saleOrder.getReason());
                    updSaleOrder.setQaNote(saleOrder.getQaNote());
                    updSaleOrder.setAppointmentDate(saleOrder.getAppointmentDate().toString());
                    updSaleOrder.setQtyTotal(saleOrder.getQtyTotal());
                    updSaleOrder.setQtySaleable(saleOrder.getQtySaleable());
                    updSaleOrder.setDeliveryPackageCode(saleOrder.getDeliveryPackageCode());
                    updSaleOrder.setTransactionId(saleOrder.getTransactionId());
                    updSaleOrder.setTransactionFee(BigDecimal.valueOf(saleOrder.getTransactionFee()));
                    updSaleOrder.setPaymentTransactionId(saleOrder.getPaymentTransactionId());
                    return updSaleOrder;
                })
                .collect(Collectors.toList());
    }
}
