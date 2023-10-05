package com.tms.api.service;


import com.tms.api.exception.TMSDbException;
import com.tms.dto.request.odDoNew.InsDeliveryOrder;

import java.util.List;

public interface DeliveryOrderService {
     boolean insertDeliveryOrder(InsDeliveryOrder insDeliveryOrder) throws TMSDbException;

     boolean insertDeliveryOrders(List<InsDeliveryOrder> insDeliveryOrderList) throws TMSDbException;
}
