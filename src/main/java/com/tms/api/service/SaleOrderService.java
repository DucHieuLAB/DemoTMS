package com.tms.api.service;

import com.tms.api.exception.TMSDbException;
import com.tms.dto.request.saleOrder.UpdSaleOrder;

public interface SaleOrderService {
    boolean updSaleOrder(int id, UpdSaleOrder updSaleOrder) throws TMSDbException;
}
