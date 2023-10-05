package com.tms.api.service.impl;

import com.tms.api.consts.EnumType;
import com.tms.api.consts.MessageConst;
import com.tms.api.exception.TMSDbException;
import com.tms.api.helper.SaleOrderConverter;
import com.tms.api.service.*;
import com.tms.dto.request.odDoNew.InsDeliveryOrder;
import com.tms.dto.request.saleOrder.GetSaleOrder;
import com.tms.dto.request.saleOrder.InsSaleOrder;
import com.tms.dto.request.saleOrder.InsSaleOrderQuery;
import com.tms.dto.request.saleOrder.ValidSaleOrder;
import com.tms.dto.response.SaleOrder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ValidateSoServiceImpl extends BaseService implements ValidateSoService {
    private final SaleOrderService saleOrderService;
    private final ClFreshService clFreshService;
    private final DeliveryOrderService deliveryOrderService;

    public ValidateSoServiceImpl(SaleOrderService saleOrderService, ClFreshService clFreshService, DeliveryOrderService deliveryOrderService) {
        this.saleOrderService = saleOrderService;
        this.clFreshService = clFreshService;
        this.deliveryOrderService = deliveryOrderService;
    }

    @Override
    public boolean validSaleOrder(ValidSaleOrder validSaleOrder) throws TMSDbException {
        int status = validSaleOrder.getUpdSaleOrder().getStatus();

        List<SaleOrder> saleOrders = getSaleOrdersById(validSaleOrder.getUpdSaleOrder().getSoId());
        if (saleOrders.isEmpty()) {
            throw new TMSDbException("Can't find SO with ID: " + validSaleOrder.getUpdSaleOrder().getSoId());
        }

        SaleOrder firstSaleOrder = saleOrders.get(0);

        if(firstSaleOrder.getStatus() == EnumType.SaleOrder.CANCEL.getStatus() && status == EnumType.SaleOrder.PENDING.getStatus()){
            throw new TMSDbException(MessageConst.UNABLE_TO_CHANGE_STATUS_CANCEL_TO_PENDING);
        }

        //Những SO sau khi đã Validated chỉ có thể chuyển về trạng thái Unassigned
        if (firstSaleOrder.getSoId() == EnumType.SaleOrder.VALIDATED.getStatus()) {
            if (status == EnumType.SaleOrder.CANCEL.getStatus()){
                throw new TMSDbException(MessageConst.UNABLE_TO_CHANGE_STATUS_VALIDATE_TO_CANCEL_PENDING);
            }

            if (status != EnumType.SaleOrder.UNASIGNE.getStatus()) {
                throw new TMSDbException(MessageConst.STATUS_RESTRICTION_VALIDATE);
            }
            //Khi SO set về trạng thái Unassigned sẽ tự động tạo ra 1 SO mới với trạng thái new
            if (validSaleOrder.isUpdateProduct()) {
                validSaleOrder.getUpdSaleOrder().setStatus(EnumType.SaleOrder.UNASIGNE.getStatus());
                // Insert sale order
                InsSaleOrder insSaleOrder = SaleOrderConverter.convertSaleOrderToInsSaleOrder(firstSaleOrder);
                insSaleOrder.setStatus(EnumType.SaleOrder.NEW.getStatus());
                InsSaleOrderQuery insSaleOrderQuery = new InsSaleOrderQuery();
                insSaleOrderQuery.setQuery(insSaleOrder.toString());
                saleOrderService.insertSaleOrders(insSaleOrderQuery);
            }
        }

        if (firstSaleOrder.getSoId() == EnumType.SaleOrder.DELAY.getStatus()) {
            if (status != EnumType.SaleOrder.CANCEL.getStatus() && status != EnumType.SaleOrder.UNASIGNE.getStatus()) {
                throw new TMSDbException(MessageConst.ERROR_CANNOT_CHANGE_STATUS_DELAY);
            }
        }


        // Create Delivery Order if needed
        createDeliveryOrder(validSaleOrder, status);

        // Update SO and ClFresh
        updateSaleOrderAndClFresh(validSaleOrder);

        return true;
    }

    private List<SaleOrder> getSaleOrdersById(int soId) throws TMSDbException {
        GetSaleOrder getSaleOrder = new GetSaleOrder();
        getSaleOrder.setSoId(soId);
        getSaleOrder.setLimit(1);
        getSaleOrder.setOffset(0);
        return saleOrderService.getSaleOrder(getSaleOrder);
    }

    private void createDeliveryOrder(ValidSaleOrder validSaleOrder, int status) throws TMSDbException{
        if (status == EnumType.SaleOrder.VALIDATED.getStatus()) {
            InsDeliveryOrder insDeliveryOrder = new InsDeliveryOrder();
            insDeliveryOrder.setSoId(validSaleOrder.getUpdSaleOrder().getSoId());
            insDeliveryOrder.setCustomerName(validSaleOrder.getUpdSaleOrder().getLeadName());
            insDeliveryOrder.setCustomerPhone(validSaleOrder.getUpdSaleOrder().getLeadPhone());
            insDeliveryOrder.setCreateby(curUserId);
            deliveryOrderService.insertDeliveryOrder(insDeliveryOrder);
        }
    }

    private void updateSaleOrderAndClFresh(ValidSaleOrder validSaleOrder) throws TMSDbException{
        saleOrderService.updSaleOrder(validSaleOrder.getUpdSaleOrder());
        if (validSaleOrder.isUpdateProduct()) {
            clFreshService.updClFreshAfterValidSaleOrder(validSaleOrder.getUpdClFresh());
        }
    }
}
