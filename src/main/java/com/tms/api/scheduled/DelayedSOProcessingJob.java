package com.tms.api.scheduled;

import com.tms.api.consts.EnumType;
import com.tms.api.exception.TMSDbException;
import com.tms.api.exception.TMSException;
import com.tms.api.helper.Helper;
import com.tms.api.helper.SaleOrderConverter;
import com.tms.api.service.BaseService;
import com.tms.api.service.DeliveryOrderService;
import com.tms.api.service.SaleOrderService;
import com.tms.dto.request.odDoNew.InsDeliveryOrder;
import com.tms.dto.request.saleOrder.GetSaleOrder;
import com.tms.dto.request.saleOrder.GetSaleOrderPending;
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

    @Scheduled(cron = "0 30 0 * * *", zone = "Asia/Ho_Chi_Minh")
    public void processPendingSaleOrders() throws TMSException {
        List<SaleOrder> saleOrderDelay = findPendingSaleOrders();

        if (saleOrderDelay == null || saleOrderDelay.size() == 0) {
            return;
        }
        // Log the size of the list
        logger.info("LIST SO delay Size: " + saleOrderDelay.size());

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

        logger.info("List DO Size: " + insDeliveryOrders.size());

        if (!insDeliveryOrders.isEmpty()) {
            logger.info("BEGIN insert List Delivery Orders");
            deliveryOrderService.insertDeliveryOrders(insDeliveryOrders);
            logger.info("END insert List Delivery Orders");
        }

        // Update Sale Orders
        if (!saleOrderDelay.isEmpty()) {
            logger.info("BEGIN update Sale Order");
            updateSaleOrders(saleOrderDelay);
            logger.info("END update Sale Order");
        }
    }

    private List<SaleOrder> findPendingSaleOrders() throws TMSException {
        GetSaleOrderPending getSaleOrderPending = new GetSaleOrderPending();
        getSaleOrderPending.setLimit(1000);
        getSaleOrderPending.setOffset(0);
        return saleOrderService.getSaleOrderPending(getSaleOrderPending);
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
