package com.tms.dao;

import com.tms.commons.DBResponse;
import com.tms.dto.request.saleOrder.*;
import com.tms.dto.response.SaleOrder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OdSaleOrderDao extends BaseDao {
    private static final String UPDATE_OD_SALE_ORDER = "update_od_sale_order";

    private static final String GET_SALE_ORDER_VALIDATE = "get_sale_order_validate";

    private static final String INSERT_OD_SALE_QUERY = "insert_od_sale_order_query";

    private static final String GET_SALE_ORDER_BY_SO_ID = "get_sale_order_by_so_id";

    private static final String GET_SALE_ORDER_PENDING = "get_sale_order_pending";

    public DBResponse<String> updateSaleOrder(String sessionId, UpdSaleOrders params) {
        return this.dbInsOrUpd(sessionId, UPDATE_OD_SALE_ORDER, params);
    }

    public DBResponse<List<SaleOrder>> getSaleOrder(String sessionId, GetSaleOrder params) {
        return this.dbGet(sessionId, GET_SALE_ORDER_VALIDATE, params, SaleOrder.class);
    }

    public DBResponse<List<SaleOrder>> getSaleOrderById(String sessionId, GetSaleOrderById params) {
        return this.dbGet(sessionId, GET_SALE_ORDER_BY_SO_ID, params, SaleOrder.class);
    }

    public DBResponse<List<SaleOrder>> getSaleOrderPending(String sessionId, GetSaleOrderPending params) {
        return this.dbGet(sessionId, GET_SALE_ORDER_PENDING, params, SaleOrder.class);
    }

    public DBResponse<String> insSaleOrders(String sessionId, InsSaleOrderQuery params) {
        return this.dbInsOrUpd(sessionId, INSERT_OD_SALE_QUERY, params);
    }
}
