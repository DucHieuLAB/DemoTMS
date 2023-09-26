package com.tms.api.service.impl;

import com.tms.api.consts.EnumType;
import com.tms.api.exception.TMSDbException;
import com.tms.api.service.BaseService;
import com.tms.api.service.DeliveryOrderService;
import com.tms.commons.DBResponse;
import com.tms.dao.OdDoNewDao;
import com.tms.dto.request.odDoNew.InsDeliveryOrder;
import com.tms.dto.request.odDoNew.InsDeliveryOrderQuery;
import lombok.extern.log4j.Log4j2;
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
        if (insDeliveryOrder.getStatus() == null){
            insDeliveryOrder.setStatus(EnumType.DeliveryOrder.NEW.getStatus());
        }
        DBResponse<String> stringDBResponse =  odDoNewDao.insDeliveryOrder(sessionId,insDeliveryOrder);
        if (stringDBResponse.getErrorCode() != EnumType.DbStatusResp.SUCCESS.getStatus()){
            throw new TMSDbException(stringDBResponse.getErrorMsg());
        }
        return true;
    }

    @Override
    public boolean insertDeliveryOrders(List<InsDeliveryOrder> insDeliveryOrders) throws TMSDbException {
        String values = "VALUES";
        for (InsDeliveryOrder insDeliveryOrder : insDeliveryOrders) {
            if (insDeliveryOrder.getStatus() == null){
                insDeliveryOrder.setStatus(EnumType.DeliveryOrder.NEW.getStatus());
            }
            values = values + insDeliveryOrder.convertToValueQuery();
            //check if insClFreshs is last element
            if (insDeliveryOrder == insDeliveryOrders.get(insDeliveryOrders.size() - 1)) {
                continue;
            }
            values = values + ",";
        }
        logger.info(values);
        InsDeliveryOrderQuery insDeliveryOrderQuery = new InsDeliveryOrderQuery(values);
        DBResponse<String> stringDBResponse = odDoNewDao.insDeliveryOrders(sessionId, insDeliveryOrderQuery);
        if (stringDBResponse == null){
            throw new TMSDbException("Can't add new delivery order ");
        }
        if(stringDBResponse.getErrorCode() != EnumType.DbStatusResp.SUCCESS.getStatus()){
            throw new TMSDbException(stringDBResponse.getErrorMsg());
        }
        return true;
    }


}
