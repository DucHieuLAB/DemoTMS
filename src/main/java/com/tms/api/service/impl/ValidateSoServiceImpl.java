package com.tms.api.service.impl;

import com.tms.api.consts.EnumType;
import com.tms.api.consts.MessageConst;
import com.tms.api.exception.TMSDbException;
import com.tms.api.exception.TMSException;
import com.tms.api.helper.SaleOrderConverter;
import com.tms.api.service.*;
import com.tms.dto.request.odDoNew.InsDeliveryOrder;
import com.tms.dto.request.saleOrder.*;
import com.tms.dto.response.SaleOrder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
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
    public boolean validSaleOrder(ValidSaleOrder validSaleOrder) throws TMSException {
        int status = validSaleOrder.getUpdSaleOrder().getStatus();
        GetSaleOrderById getSaleOrderById = new GetSaleOrderById();
        getSaleOrderById.setSoId(validSaleOrder.getUpdSaleOrder().getSoId());
        getSaleOrderById.setLimit(1000);
        getSaleOrderById.setOffset(0);
        List<SaleOrder> saleOrders = saleOrderService.getSaleOrderById(getSaleOrderById);

        SaleOrder firstSaleOrder = saleOrders.get(0);

        if (firstSaleOrder.getStatus() == EnumType.SaleOrder.CANCEL.getStatus() && status == EnumType.SaleOrder.PENDING.getStatus()) {
            throw new TMSDbException(MessageConst.UNABLE_TO_CHANGE_STATUS_CANCEL_TO_PENDING);
        }

        //Những SO sau khi đã Validated chỉ có thể chuyển về trạng thái Unassigned
        if (firstSaleOrder.getStatus() == EnumType.SaleOrder.VALIDATED.getStatus()) {
            if (status == EnumType.SaleOrder.CANCEL.getStatus()) {
                throw new TMSDbException(MessageConst.UNABLE_TO_CHANGE_STATUS_VALIDATE_TO_CANCEL_PENDING);
            }

            if (status != EnumType.SaleOrder.UNASIGNE.getStatus()) {
                throw new TMSDbException(MessageConst.STATUS_RESTRICTION_VALIDATE);
            }

        }
        if (firstSaleOrder.getStatus() == EnumType.SaleOrder.DELAY.getStatus()) {
            if (status != EnumType.SaleOrder.CANCEL.getStatus() && status != EnumType.SaleOrder.UNASIGNE.getStatus()) {
                throw new TMSDbException(MessageConst.ERROR_CANNOT_CHANGE_STATUS_DELAY);
            }
        }
        //Khi SO set về trạng thái Unassigned sẽ tự động tạo ra 1 SO mới với trạng thái new
        if (status == EnumType.SaleOrder.UNASIGNE.getStatus()) {
            logger.info("UNASIGNE case");
            validSaleOrder.getUpdSaleOrder().setStatus(EnumType.SaleOrder.UNASIGNE.getStatus());
            // Cập nhât các trường mới vào new SO
            SaleOrder udpSaleOrder = SaleOrderConverter.convertToSaleOrder(validSaleOrder.getUpdSaleOrder());
            mergeNonNullFields(firstSaleOrder, udpSaleOrder);
            // Insert sale order
            InsSaleOrder insSaleOrder = SaleOrderConverter.convertSaleOrderToInsSaleOrder(firstSaleOrder);
            insSaleOrder.setStatus(EnumType.SaleOrder.NEW.getStatus());
            InsSaleOrderQuery insSaleOrderQuery = new InsSaleOrderQuery();
            String query = "VALUES ";
            query = query + insSaleOrder.toString();
            insSaleOrderQuery.setQuery(query);
            logger.info("BEGIN inser new SALE ORDER");
            saleOrderService.insertSaleOrders(insSaleOrderQuery);
            logger.info("End inser new SALE ORDER");
        }

        // Create Delivery Order if needed
        createDeliveryOrder(validSaleOrder, status);
        // Update SO and ClFresh
        updateSaleOrderAndClFresh(status, validSaleOrder);

        return true;
    }

    private void mergeNonNullFields(SaleOrder main, SaleOrder update) {
        for (Field field : update.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object value = field.get(update);
                if (value != null) {
                    field.set(main, value);
                }
            } catch (IllegalAccessException e) {
                logger.info(e.getMessage());
            }
        }
    }

    private void createDeliveryOrder(ValidSaleOrder validSaleOrder, int status) throws TMSDbException {
        if (status == EnumType.SaleOrder.VALIDATED.getStatus()) {
            logger.info("VALIDATED case");
            InsDeliveryOrder insDeliveryOrder = new InsDeliveryOrder();
            insDeliveryOrder.setSoId(validSaleOrder.getUpdSaleOrder().getSoId());
            insDeliveryOrder.setCustomerName(validSaleOrder.getUpdSaleOrder().getLeadName());
            insDeliveryOrder.setCustomerPhone(validSaleOrder.getUpdSaleOrder().getLeadPhone());
            insDeliveryOrder.setCreateby(curUserId);
            List<InsDeliveryOrder> insDeliveryOrderList = new ArrayList<>();
            insDeliveryOrderList.add(insDeliveryOrder);
            logger.info("BEGIN insert Delivery ORDER");
            deliveryOrderService.insertDeliveryOrders(insDeliveryOrderList);
            logger.info("END insert Delivery ORDER");
        }
    }

    private void updateSaleOrderAndClFresh(int status, ValidSaleOrder validSaleOrder) throws TMSDbException {
        logger.info("BEGIN update SALE ORDER");
        saleOrderService.updSaleOrder(validSaleOrder.getUpdSaleOrder());
        logger.info("END update SALE ORDER");
        if (validSaleOrder.getIsUpdateProduct()) {
            logger.info("BEGIN update CL_FRESH  ACCEPTD CASE: DELAY|VALIDATED|DELAY");
            if (status == EnumType.SaleOrder.DELAY.getStatus()
                    || status == EnumType.SaleOrder.VALIDATED.getStatus()) {
                clFreshService.updClFreshAfterValidSaleOrder(validSaleOrder.getUpdClFresh());
                logger.info("END update CL_FRESH");
            }

        }
    }
}
