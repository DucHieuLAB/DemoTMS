package com.tms.api.service.impl;

import com.tms.api.consts.EnumType;
import com.tms.api.consts.MessageConst;
import com.tms.api.exception.TMSDbException;
import com.tms.api.helper.Helper;
import com.tms.api.service.BaseService;
import com.tms.api.service.SaleOrderService;
import com.tms.commons.DBResponse;
import com.tms.dao.OdSaleOrderDao;
import com.tms.dto.request.saleOrder.*;
import com.tms.dto.response.SaleOrder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SaleOrderServiceImpl extends BaseService implements SaleOrderService {
    private final OdSaleOrderDao odSaleOrderDao;

    public SaleOrderServiceImpl(OdSaleOrderDao odSaleOrderDao) {
        this.odSaleOrderDao = odSaleOrderDao;
    }

    @Override
    public boolean updSaleOrder(UpdSaleOrder updSaleOrder) throws TMSDbException {
        updSaleOrder.setModifyBy(curUserId);
        UpdSaleOrders updSaleOrders = new UpdSaleOrders();
        List<UpdSaleOrder> updSaleOrders1 = new ArrayList<>();
        updSaleOrders1.add(updSaleOrder);
        String json = Helper.convertListToJson(updSaleOrders1);
        updSaleOrders.setJson(json);
        DBResponse<String> updateSaleOrder = odSaleOrderDao.updateSaleOrder(sessionId, updSaleOrders);
        if (updateSaleOrder.getErrorCode() != EnumType.DbStatusResp.SUCCESS.getStatus()) {
            throw new TMSDbException(updateSaleOrder.getErrorMsg());
        }
        return true;
    }

    @Override
    public List<SaleOrder> getSaleOrder(GetSaleOrder getSaleOrder) throws TMSDbException {
        DBResponse<List<SaleOrder>> listDbResponse = odSaleOrderDao.getSaleOrder(sessionId,getSaleOrder);
        if (listDbResponse == null){
            throw new TMSDbException(MessageConst.ERROL_NULL_DB_RESPONSE);
        }
        if (listDbResponse.getErrorCode() != EnumType.DbStatusResp.SUCCESS.getStatus()){
            throw new TMSDbException(listDbResponse.getErrorMsg());
        }
        if (listDbResponse.getResult() == null || listDbResponse.getResult().size() == 0){
            return null;
        }
        return listDbResponse.getResult();
    }

    @Override
    public boolean updateSaleOrders(UpdSaleOrders updSaleOrders) throws TMSDbException {
        DBResponse<String> updateSaleOrder = odSaleOrderDao.updateSaleOrder(sessionId, updSaleOrders);
        if (updateSaleOrder.getErrorCode() != EnumType.DbStatusResp.SUCCESS.getStatus()) {
            throw new TMSDbException(updateSaleOrder.getErrorMsg());
        }
        return true;
    }

    @Override
    public boolean insertSaleOrders(InsSaleOrderQuery insSaleOrderQuery) throws TMSDbException {
        DBResponse<String> insertSaleOrders = odSaleOrderDao.insSaleOrders(sessionId,insSaleOrderQuery);
        if (insertSaleOrders.getErrorCode() != EnumType.DbStatusResp.SUCCESS.getStatus()){
            throw new TMSDbException(insertSaleOrders.getErrorMsg());
        }
        return true;
    }

}
