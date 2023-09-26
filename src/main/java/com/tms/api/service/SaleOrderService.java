package com.tms.api.service;

import com.tms.api.exception.TMSDbException;
import com.tms.commons.DBResponse;
import com.tms.dto.request.saleOrder.GetSaleOrder;
import com.tms.dto.request.saleOrder.UpdSaleOrder;
import com.tms.dto.request.saleOrder.UpdSaleOrders;
import com.tms.dto.request.saleOrder.ValidSaleOrder;
import com.tms.dto.response.SaleOrder;

import java.util.List;

public interface SaleOrderService {

    boolean updSaleOrder(int id, UpdSaleOrder updSaleOrder) throws TMSDbException;

    List<SaleOrder> getSaleOrder( GetSaleOrder getSaleOrder) throws  TMSDbException;

    boolean updateSaleOrders( UpdSaleOrders updSaleOrders)throws  TMSDbException;
}
