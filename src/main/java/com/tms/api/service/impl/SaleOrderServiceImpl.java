package com.tms.api.service.impl;

import com.tms.api.commons.ApiMessageError;
import com.tms.api.consts.EnumType;
import com.tms.api.consts.MessageConst;
import com.tms.api.exception.ErrorMessages;
import com.tms.api.exception.TMSDbException;
import com.tms.api.exception.TMSEntityNotFoundException;
import com.tms.api.exception.TMSException;
import com.tms.api.helper.Helper;
import com.tms.api.service.BaseService;
import com.tms.api.service.SaleOrderService;
import com.tms.commons.DBResponse;
import com.tms.dao.OdSaleOrderDao;
import com.tms.dto.request.saleOrder.*;
import com.tms.dto.response.SaleOrder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
    public List<SaleOrder> getSaleOrder(GetSaleOrder getSaleOrder) throws TMSException {
        DBResponse<List<SaleOrder>> listDbResponse = odSaleOrderDao.getSaleOrder(sessionId, getSaleOrder);
        if (listDbResponse.getErrorCode() != EnumType.DbStatusResp.SUCCESS.getStatus()) {
            throw new TMSDbException(listDbResponse.getErrorMsg());
        }
        if (CollectionUtils.isEmpty(listDbResponse.getResult())) {
            String errorMessage = MessageConst.NOT_FOUND_WITH_OBJECT_PARAMS + Helper.toJson(getSaleOrder);
            throw new TMSEntityNotFoundException(ErrorMessages.NOT_FOUND, new ApiMessageError(errorMessage));
        }
        return listDbResponse.getResult();
    }

    @Override
    public List<SaleOrder> getSaleOrderById(GetSaleOrderById getSaleOrderById) throws TMSException {
        DBResponse<List<SaleOrder>> listDbResponse = odSaleOrderDao.getSaleOrderById(sessionId, getSaleOrderById);
        if (listDbResponse.getErrorCode() != EnumType.DbStatusResp.SUCCESS.getStatus()) {
            throw new TMSDbException(listDbResponse.getErrorMsg());
        }
        if (CollectionUtils.isEmpty(listDbResponse.getResult())) {
            String errorMessage = MessageConst.NOT_FOUND_WITH_OBJECT_PARAMS + Helper.toJson(getSaleOrderById);
            throw new TMSEntityNotFoundException(ErrorMessages.NOT_FOUND, new ApiMessageError(errorMessage));
        }
        return listDbResponse.getResult();
    }

    @Override
    public List<SaleOrder> getSaleOrderPending(GetSaleOrderPending getSaleOrderPending) throws TMSException {
        DBResponse<List<SaleOrder>> listDbResponse = odSaleOrderDao.getSaleOrderPending(sessionId, getSaleOrderPending);
        if (listDbResponse.getErrorCode() != EnumType.DbStatusResp.SUCCESS.getStatus()) {
            throw new TMSDbException(listDbResponse.getErrorMsg());
        }
        if (CollectionUtils.isEmpty(listDbResponse.getResult())) {
            String errorMessage = MessageConst.NOT_FOUND_WITH_OBJECT_PARAMS + Helper.toJson(getSaleOrderPending);
            throw new TMSEntityNotFoundException(ErrorMessages.NOT_FOUND, new ApiMessageError(errorMessage));
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
        DBResponse<String> insertSaleOrders = odSaleOrderDao.insSaleOrders(sessionId, insSaleOrderQuery);
        if (insertSaleOrders.getErrorCode() != EnumType.DbStatusResp.SUCCESS.getStatus()) {
            throw new TMSDbException(insertSaleOrders.getErrorMsg());
        }
        return true;
    }


}
