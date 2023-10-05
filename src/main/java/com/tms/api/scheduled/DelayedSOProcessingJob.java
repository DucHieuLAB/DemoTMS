package com.tms.api.scheduled;

import com.tms.api.consts.EnumType;
import com.tms.api.exception.TMSDbException;
import com.tms.api.helper.Helper;
import com.tms.api.helper.SaleOrderConverter;
import com.tms.api.service.BaseService;
import com.tms.api.service.DeliveryOrderService;
import com.tms.api.service.SaleOrderService;
import com.tms.dto.request.odDoNew.InsDeliveryOrder;
import com.tms.dto.request.saleOrder.GetSaleOrder;
import com.tms.dto.request.saleOrder.UpdSaleOrder;
import com.tms.dto.request.saleOrder.UpdSaleOrders;
import com.tms.dto.response.SaleOrder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class DelayedSOProcessingJob extends BaseService {
    private final SaleOrderService saleOrderService;
    private final DeliveryOrderService deliveryOrderService;

    public DelayedSOProcessingJob(SaleOrderService saleOrderService, DeliveryOrderService deliveryOrderService) {
        this.saleOrderService = saleOrderService;
        this.deliveryOrderService = deliveryOrderService;
    }

    @Scheduled(fixedDelay = 5000)
    public void processPendingSaleOrders() throws TMSDbException {
        // Find list of Sale Orders with status "Pending"
        List<SaleOrder> saleOrderDelay = findPendingSaleOrders();

        if (saleOrderDelay == null || saleOrderDelay.isEmpty()) {
            return;
        }
        // Log the size of the list
        logger.info("List SO delay Size: " + saleOrderDelay.size());

        List<InsDeliveryOrder> insDeliveryOrders = new ArrayList<>();

        for (SaleOrder saleOrder : saleOrderDelay) {
            if (saleOrder.getCreationDate().compareTo(LocalDate.now()) <= 0) {
                // Create a Delivery Order
                InsDeliveryOrder insDeliveryOrder = createDeliveryOrderFromSaleOrder(saleOrder);
                insDeliveryOrders.add(insDeliveryOrder);

                // Update the Sale Order status to "Validated"
                saleOrder.setStatus(EnumType.SaleOrder.VALIDATED.getStatus());
            }
        }

        // Log the size of the list of Delivery Orders
        logger.info("List DO Size: " + insDeliveryOrders.size());

        // Create Delivery Orders
        if (!insDeliveryOrders.isEmpty()) {
            deliveryOrderService.insertDeliveryOrders(insDeliveryOrders);
        }

        // Update Sale Orders
        if (!saleOrderDelay.isEmpty()) {
            updateSaleOrders(saleOrderDelay);
        }
    }

    private List<SaleOrder> findPendingSaleOrders() throws TMSDbException {
        GetSaleOrder getSaleOrder = new GetSaleOrder();
        getSaleOrder.setStatus(EnumType.SaleOrder.PENDING.getStatus());
        getSaleOrder.setLimit(1000);
        getSaleOrder.setOffset(0);
        return saleOrderService.getSaleOrder(getSaleOrder);
    }

    private InsDeliveryOrder createDeliveryOrderFromSaleOrder(SaleOrder saleOrder) {
        InsDeliveryOrder insDeliveryOrder = new InsDeliveryOrder();
        insDeliveryOrder.setSoId(saleOrder.getSoId());
        insDeliveryOrder.setCustomerName(saleOrder.getLeadName());
        insDeliveryOrder.setCustomerPhone(saleOrder.getLeadPhone());
        insDeliveryOrder.setCreateby(curUserId);
        return insDeliveryOrder;
    }

    private void updateSaleOrders(List<SaleOrder> saleOrders) throws TMSDbException {
        UpdSaleOrders updSaleOrders = new UpdSaleOrders();
        List<UpdSaleOrder> updSaleOrders1 = SaleOrderConverter.convertToUpdSaleOrderList(saleOrders);
        String json = Helper.convertListToJson(updSaleOrders1);
        updSaleOrders.setJson(json);
        saleOrderService.updateSaleOrders(updSaleOrders);
    }
}
