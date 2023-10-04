package com.tms.api.service.impl;


import com.tms.api.consts.EnumType;
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
        String values = "VALUES";
        for (InsClFresh insClFreshs : clFreshes) {
            values = values + insClFreshs.toString();
            //check if insClFreshs is last element
            if (insClFreshs == clFreshes.get(clFreshes.size() - 1)) {
                continue;
            }
            values = values + ",";
        }
        InsClFreshsQuery insClFreshsQuery = new InsClFreshsQuery(values);
        DBResponse<String> insClFreshDBResponse = clFreshDao.insClFresh(sessionId, insClFreshsQuery);
        if (insClFreshDBResponse == null) {
            throw new TMSDbException("Can't add new Cl Fresh");
        }
        if (insClFreshDBResponse.getErrorCode() != EnumType.DbStatusResp.SUCCESS.getStatus()) {
            throw new TMSDbException(insClFreshDBResponse.getErrorMsg());
        }
    }

    @Override
    public boolean updClFreshAfterValidSO(int leadId, UpdClFresh updClFresh) throws TMSDbException {
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
