package com.tms.api.service.impl;

import com.tms.api.consts.EnumType;
import com.tms.api.exception.TMSDbException;
import com.tms.api.service.*;
import com.tms.dto.request.odDoNew.InsDeliveryOrder;
import com.tms.dto.request.saleOrder.GetSaleOrder;
import com.tms.dto.request.saleOrder.ValidSaleOrder;
import com.tms.dto.response.SaleOrder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ValidateSoServiceImpl extends BaseService implements ValidateSoService {
    private final SaleOrderService saleOrderService;
    private final ClFreshService clFreshService;
    private final DeliveryOrderService deliveryOrderService;

    public ValidateSoServiceImpl(SaleOrderService saleOrderService,ClFreshService clFreshService,DeliveryOrderService deliveryOrderService){
        this.saleOrderService = saleOrderService;
        this.clFreshService = clFreshService;
        this.deliveryOrderService = deliveryOrderService;
    }

    @Override
    public boolean validSaleOrder(int id, ValidSaleOrder validSaleOrder) throws TMSDbException {
        int status = validSaleOrder.getUpdSaleOrder().getStatus();
        // Get SO for valid
        GetSaleOrder getSaleOrder = new GetSaleOrder();
        getSaleOrder.setSoId(validSaleOrder.getUpdSaleOrder().getSoId());
        getSaleOrder.setLimit(1);
        getSaleOrder.setOffset(0);
        List<SaleOrder> saleOrders = saleOrderService.getSaleOrder(getSaleOrder);
        if (saleOrders == null || saleOrders.size() == 0){
            // Can't find SO by ID
            throw new TMSDbException("Can't find SO with ID: " + id);
        }
        // Những SO sau khi đã Validated chỉ có thể chuyển về trạng thái Unassigned
        if(saleOrders.get(0).getSoId() == EnumType.SaleOrder.VALIDATED.getStatus() ){
           if (status != EnumType.SaleOrder.UNASIGNE.getStatus()){
               throw new TMSDbException("asv");
           }
           //Khi SO đã Validated nhưng muốn chỉnh sửa lại thông tin thì sẽ set về trạng thái Unassigned
           if (validSaleOrder.isUpdateProduct() && status != EnumType.SaleOrder.UNASIGNE.getStatus()){
               return false;
           }
        }
        // Những SO có trạng thái Delay chỉ có thể chuyển về Cancel hoặc Unassigned
        if (saleOrders.get(0).getSoId() == EnumType.SaleOrder.DELAY.getStatus()){
            if (status != EnumType.SaleOrder.CANCEL.getStatus() && status != EnumType.SaleOrder.UNASIGNE.getStatus()){
                return false;
            }
        }
        if (status == EnumType.SaleOrder.PENDING.getStatus()) {
            if (saleOrders.get(0).getStatus() == EnumType.SaleOrder.CANCEL.getStatus()){
                return false;
            }
            if (saleOrders.get(0).getStatus() == EnumType.SaleOrder.VALIDATED.getStatus()){
                return false;
            }
            saleOrderService.updSaleOrder(id, validSaleOrder.getUpdSaleOrder());
        }
        if (status == EnumType.SaleOrder.CANCEL.getStatus()) {
            if (saleOrders.get(0).getStatus() == EnumType.SaleOrder.VALIDATED.getStatus()){
                return false;
            }
        }
        if (status == EnumType.SaleOrder.VALIDATED.getStatus()) {
            boolean updStatus = saleOrderService.updSaleOrder(id, validSaleOrder.getUpdSaleOrder());
            if (updStatus){
                // Insert DO
                InsDeliveryOrder insDeliveryOrder = new InsDeliveryOrder();
                // thêm thông tin DeliveryOrder
                insDeliveryOrder.setSoId(id);
                insDeliveryOrder.setCustomerName(validSaleOrder.getUpdSaleOrder().getLeadName());
                insDeliveryOrder.setCustomerPhone(validSaleOrder.getUpdSaleOrder().getLeadPhone());
                insDeliveryOrder.setCreateby(curUserId);
                deliveryOrderService.insertDeliveryOrder(insDeliveryOrder);
            }

        }
        // Update SO, ClFresh
        boolean updStatus = saleOrderService.updSaleOrder(id, validSaleOrder.getUpdSaleOrder());
        if (validSaleOrder.isUpdateProduct()){
            clFreshService.updClFreshAfterValidSO(validSaleOrder.getUpdClFresh().getLeadId(), validSaleOrder.getUpdClFresh());
        }
        return true;
    }
}
