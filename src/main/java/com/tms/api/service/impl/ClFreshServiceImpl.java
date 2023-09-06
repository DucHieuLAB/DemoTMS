package com.tms.api.service.impl;

import com.tms.api.consts.EnumType;
import com.tms.api.exception.TMSDbException;
import com.tms.api.service.ClFreshService;
import com.tms.commons.DBResponse;
import com.tms.dao.ClFreshDao;
import com.tms.dto.request.clFresh.InsClFresh;
import com.tms.dto.request.clFresh.InsClFreshsQuery;

import java.util.List;

public class ClFreshServiceImpl implements ClFreshService {
    private final ClFreshDao clFreshDao;

    public ClFreshServiceImpl(ClFreshDao clFreshDao){ this.clFreshDao = clFreshDao;}

    @Override
    public void insertClFresh(List<InsClFresh> clFreshes, String sessionId) throws TMSDbException {
        String values = "VALUES";
        for(InsClFresh insClFreshs : clFreshes){
            values = values + insClFreshs.toString();
            //check if insClFreshs is last element
            if (insClFreshs == clFreshes.get(clFreshes.size()-1)){
                continue;
            }
            values = values +",";
        }
        InsClFreshsQuery insClFreshsQuery = new InsClFreshsQuery(values);
        DBResponse<String> insClFreshDBResponse =  clFreshDao.insLeadAfterFillterByQuery(sessionId,insClFreshsQuery);
        if (insClFreshDBResponse == null){
            return;
        }
        if(insClFreshDBResponse.getErrorCode()!= EnumType.DbStatusResp.SUCCESS.getStatus()){
            throw new TMSDbException(insClFreshDBResponse.getErrorMsg());
        }
    }
}
