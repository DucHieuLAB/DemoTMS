package com.tms.dao;

import com.tms.commons.DBResponse;
import com.tms.dto.request.saleOrder.UpdSaleOrders;
import org.springframework.stereotype.Service;

@Service
public class OdSaleOrderDao extends BaseDao{
    private static final String UPDATE_OD_SALE_ORDER = "update_od_sale_order";
    /* BEGIN UPDATE */
    public DBResponse<String> updateSaleOrder(String sessionId, UpdSaleOrders params) {
        return this.dbInsOrUpd(sessionId, UPDATE_OD_SALE_ORDER, params);
    }
    /* END UPDATE */
}
