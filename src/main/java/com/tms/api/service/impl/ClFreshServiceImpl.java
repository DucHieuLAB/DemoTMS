package com.tms.api.service.impl;


import com.tms.api.consts.EnumType;
import com.tms.api.consts.MessageConst;
import com.tms.api.exception.TMSDbException;
import com.tms.api.helper.Helper;
import com.tms.api.service.BaseService;
import com.tms.api.service.ClFreshService;
import com.tms.commons.DBResponse;
import com.tms.dao.ClFreshDao;
import com.tms.dto.request.clFresh.InsClFresh;
import com.tms.dto.request.clFresh.InsClFreshsQuery;
import com.tms.dto.request.clFresh.UpdClFresh;
import com.tms.dto.request.clFresh.UpdClFreshs;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClFreshServiceImpl extends BaseService implements ClFreshService {

    private final ClFreshDao clFreshDao;

    public ClFreshServiceImpl(ClFreshDao clFreshDao) {
        this.clFreshDao = clFreshDao;
    }

    @Override
    public void insertClFresh(List<InsClFresh> clFreshes, String sessionId) throws TMSDbException {
        StringBuilder valuesBuilder = new StringBuilder("VALUES");
        for (InsClFresh insClFresh : clFreshes) {
            valuesBuilder.append(insClFresh.toString());
            //check if insClFresh is last element
            if (insClFresh == clFreshes.get(clFreshes.size() - 1)) {
                continue;
            }
            valuesBuilder.append(",");
        }
        InsClFreshsQuery insClFreshsQuery = new InsClFreshsQuery(valuesBuilder.toString());
        DBResponse<String> insClFreshDbResponse = clFreshDao.insClFresh(sessionId, insClFreshsQuery);
        if (insClFreshDbResponse == null) {
            throw new TMSDbException(MessageConst.ERROL_NULL_DB_RESPONSE);
        }
        if (insClFreshDbResponse.getErrorCode() != EnumType.DbStatusResp.SUCCESS.getStatus()) {
            throw new TMSDbException(insClFreshDbResponse.getErrorMsg());
        }
    }

    @Override
    public boolean updClFreshAfterValidSaleOrder(UpdClFresh updClFresh) throws TMSDbException {
        updClFresh.setModifyBy(curUserId);
        return updClFresh(sessionId,updClFresh);
    }

    public boolean updClFresh(String sessionId,UpdClFresh updClFresh) throws TMSDbException{
        List<UpdClFresh> clFreshes = new ArrayList<>();
        clFreshes.add(updClFresh);
        String json = Helper.convertListToJson(clFreshes);
        UpdClFreshs updClFreshs =  new UpdClFreshs();
        updClFreshs.setJson(json);
        DBResponse<String> insScheduleDbResp = clFreshDao.updClFresh(sessionId,updClFreshs);
        if (insScheduleDbResp.getErrorCode() != EnumType.DbStatusResp.SUCCESS.getStatus()) {
            throw new TMSDbException(insScheduleDbResp.getErrorMsg());
        }
        return true;
    }

}
