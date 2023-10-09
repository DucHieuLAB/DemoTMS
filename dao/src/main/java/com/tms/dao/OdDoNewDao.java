package com.tms.dao;

import com.tms.commons.DBResponse;
import com.tms.dto.request.odDoNew.InsDeliveryOrder;
import com.tms.dto.request.odDoNew.InsDeliveryOrderQuery;
import org.springframework.stereotype.Service;

@Service
public class OdDoNewDao extends BaseDao {

    private static final String INSERT_OD_DO_NEW = "insert_od_do_new";
    private static final String INSERT_OD_DO_NEW_QUERY = "insert_od_do_new_query";

    public DBResponse<String> insDeliveryOrder(String sessionId, InsDeliveryOrder params) {
        return this.dbInsOrUpd(sessionId, INSERT_OD_DO_NEW, params);
    }

    public DBResponse<String> insDeliveryOrders(String sessionId, InsDeliveryOrderQuery params) {
        return this.dbInsOrUpd(sessionId, INSERT_OD_DO_NEW_QUERY, params);
    }
}
