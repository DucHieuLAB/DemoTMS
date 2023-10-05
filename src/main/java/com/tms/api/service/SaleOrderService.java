package com.tms.api.service;

import com.tms.api.exception.TMSDbException;
import com.tms.commons.DBResponse;
import com.tms.dto.request.saleOrder.*;
import com.tms.dto.response.SaleOrder;

import java.util.List;

public interface SaleOrderService {

    boolean updSaleOrder( UpdSaleOrder updSaleOrder) throws TMSDbException;

    List<SaleOrder> getSaleOrder( GetSaleOrder getSaleOrder) throws  TMSDbException;

    boolean updateSaleOrders( UpdSaleOrders updSaleOrders)throws  TMSDbException;

    boolean insertSaleOrders(InsSaleOrderQuery insSaleOrderQuery) throws TMSDbException;
}
