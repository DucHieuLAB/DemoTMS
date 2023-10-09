package com.tms.api.service.impl;

import com.tms.api.consts.EnumType;
import com.tms.api.exception.TMSDbException;
import com.tms.api.service.BaseService;
import com.tms.api.service.DeliveryOrderService;
import com.tms.commons.DBResponse;
import com.tms.dao.OdDoNewDao;
import com.tms.dto.request.odDoNew.InsDeliveryOrder;
import com.tms.dto.request.odDoNew.InsDeliveryOrderQuery;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryOrderServiceImpl extends BaseService implements DeliveryOrderService {
    private final OdDoNewDao odDoNewDao;

    public DeliveryOrderServiceImpl(OdDoNewDao odDoNewDao) {
        this.odDoNewDao = odDoNewDao;
    }

    @Override
    public boolean insertDeliveryOrder(InsDeliveryOrder insDeliveryOrder) throws TMSDbException {
        if (insDeliveryOrder.getStatus() == null) {
            insDeliveryOrder.setStatus(EnumType.DeliveryOrder.NEW.getStatus());
        }
        DBResponse<String> stringDbResponse = odDoNewDao.insDeliveryOrder(sessionId, insDeliveryOrder);
        if (stringDbResponse.getErrorCode() != EnumType.DbStatusResp.SUCCESS.getStatus()) {
            throw new TMSDbException(stringDbResponse.getErrorMsg());
        }
        return true;
    }

    @Override
    public boolean insertDeliveryOrders(List<InsDeliveryOrder> insDeliveryOrders) throws TMSDbException {
        StringBuilder valuesBuilder = new StringBuilder("VALUES");
        for (InsDeliveryOrder insDeliveryOrder : insDeliveryOrders) {
            if (insDeliveryOrder.getStatus() == null) {
                insDeliveryOrder.setStatus(EnumType.DeliveryOrder.NEW.getStatus());
            }
            valuesBuilder.append(insDeliveryOrder.convertToValueQuery());
            //check if insClFreshs is last element
            if (insDeliveryOrder == insDeliveryOrders.get(insDeliveryOrders.size() - 1)) {
                continue;
            }
            valuesBuilder.append(",");
        }
        InsDeliveryOrderQuery insDeliveryOrderQuery = new InsDeliveryOrderQuery(valuesBuilder.toString());
        DBResponse<String> stringDbResponse = odDoNewDao.insDeliveryOrders(sessionId, insDeliveryOrderQuery);
        if (stringDbResponse == null) {
            throw new TMSDbException("Can't add new delivery order ");
        }
        if (stringDbResponse.getErrorCode() != EnumType.DbStatusResp.SUCCESS.getStatus()) {
            throw new TMSDbException(stringDbResponse.getErrorMsg());
        }
        return true;
    }


}
