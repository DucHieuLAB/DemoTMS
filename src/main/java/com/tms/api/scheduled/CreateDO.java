package com.tms.api.scheduled;

import com.tms.api.consts.EnumType;
import com.tms.api.exception.TMSDbException;
import com.tms.api.helper.DateHelper;
import com.tms.api.helper.Helper;
import com.tms.api.helper.SaleOrderConverter;
import com.tms.api.service.BaseService;
import com.tms.api.service.DeliveryOrderService;
import com.tms.api.service.SaleOrderService;
import com.tms.commons.DBResponse;
import com.tms.dto.request.odDoNew.InsDeliveryOrder;
import com.tms.dto.request.saleOrder.GetSaleOrder;
import com.tms.dto.request.saleOrder.UpdSaleOrder;
import com.tms.dto.request.saleOrder.UpdSaleOrders;
import com.tms.dto.response.SaleOrder;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Component
public class CreateDO  extends BaseService {
    private final SaleOrderService saleOrderService;
    private final DeliveryOrderService deliveryOrderService;
    public CreateDO(SaleOrderService saleOrderService, DeliveryOrderService deliveryOrderService){
        this.saleOrderService = saleOrderService;
        this.deliveryOrderService = deliveryOrderService;
    }

    @Scheduled(fixedDelay = 5000)
    public void createDO() throws TMSDbException {
        // Find list SO with status value = SaleOrder Delay
        GetSaleOrder getSaleOrder = new GetSaleOrder();
        getSaleOrder.setStatus(EnumType.SaleOrder.PENDING.getStatus());
        getSaleOrder.setLimit(1000);
        getSaleOrder.setOffset(0);
        List<SaleOrder> saleOrderDelay =  saleOrderService.getSaleOrder(getSaleOrder);
        if (saleOrderDelay == null || saleOrderDelay.size() == 0){
            return;
        }
        logger.info("List SO delay Size: "+saleOrderDelay.size());
        List<InsDeliveryOrder> insDeliveryOrders = new ArrayList<>();
        for (SaleOrder saleOrder :saleOrderDelay){
            InsDeliveryOrder insDeliveryOrder = new InsDeliveryOrder();
            // thêm thông tin DeliveryOrder
            insDeliveryOrder.setSoId(saleOrder.getSoId());
            insDeliveryOrder.setCustomerName(saleOrder.getLeadName());
            insDeliveryOrder.setCustomerPhone(saleOrder.getLeadPhone());
            insDeliveryOrder.setCreateby(curUserId);
            // Kiem tra da den nay dat hang hay chua
            if (saleOrder.getCreationDate().compareTo(LocalDate.now()) <= 0){
                insDeliveryOrders.add(insDeliveryOrder);
            }
            // Update saleOrder Status => validate
            saleOrder.setStatus(EnumType.SaleOrder.VALIDATED.getStatus());
        }
        logger.info("List DO Size: "+saleOrderDelay.size());
        // Create Do
        boolean status = false;
        if (insDeliveryOrders.size() > 0){
            status = deliveryOrderService.insertDeliveryOrders(insDeliveryOrders);
        }
        logger.info("Create DO status: " +status);
        // Update SO
        UpdSaleOrders updSaleOrders = new UpdSaleOrders();
        List<UpdSaleOrder> updSaleOrders1 = SaleOrderConverter.convertToUpdSaleOrderList(saleOrderDelay);
        // Convert SaleOrder - > UpdSaleOrder
        String json = Helper.convertListToJson(updSaleOrders1);
        updSaleOrders.setJson(json);
        boolean updateStatus = saleOrderService.updateSaleOrders(updSaleOrders);
    }
}
