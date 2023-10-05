package com.tms.api.service.impl;

import com.tms.api.consts.EnumType;
import com.tms.api.exception.TMSDbException;
import com.tms.api.helper.Helper;
import com.tms.api.service.BaseService;
import com.tms.api.service.ClFreshService;
import com.tms.api.service.SaleOrderService;
import com.tms.commons.DBResponse;
import com.tms.dao.OdSaleOrderDao;
import com.tms.dto.request.saleOrder.GetSaleOrder;
import com.tms.dto.request.saleOrder.UpdSaleOrder;
import com.tms.dto.request.saleOrder.UpdSaleOrders;
import com.tms.dto.request.saleOrder.ValidSaleOrder;
import com.tms.dto.response.SaleOrder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SaleOrderServiceImpl extends BaseService implements SaleOrderService {
    private final OdSaleOrderDao odSaleOrderDao;

    public SaleOrderServiceImpl(OdSaleOrderDao odSaleOrderDao, ClFreshService clFreshService) {
        this.odSaleOrderDao = odSaleOrderDao;
    }

    @Override
    public boolean updSaleOrder(int id, UpdSaleOrder updSaleOrder) throws TMSDbException {
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
        DBResponse<List<SaleOrder>> listDBResponse = odSaleOrderDao.getSaleOrder(sessionId,getSaleOrder);
        if (listDBResponse.getErrorCode() != EnumType.DbStatusResp.SUCCESS.getStatus()){
            throw new TMSDbException(listDBResponse.getErrorMsg());
        }
        if (listDBResponse.getResult() == null || listDBResponse.getResult().size() == 0){
            return null;
        }
        return listDBResponse.getResult();
    }

    @Override
    public boolean updateSaleOrders(UpdSaleOrders updSaleOrders) throws TMSDbException {
        DBResponse<String> updateSaleOrder = odSaleOrderDao.updateSaleOrder(sessionId, updSaleOrders);
        if (updateSaleOrder.getErrorCode() != EnumType.DbStatusResp.SUCCESS.getStatus()) {
            throw new TMSDbException(updateSaleOrder.getErrorMsg());
        }
        return true;
    }

}
