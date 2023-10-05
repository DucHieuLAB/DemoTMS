package com.tms.api.service.impl;

import com.tms.api.consts.EnumType;
import com.tms.api.exception.TMSDbException;
import com.tms.api.service.BlackListService;
import com.tms.api.service.BaseService;
import com.tms.commons.DBResponse;
import com.tms.dao.CfBlackListDao;
import com.tms.dto.request.blacklist.GetBlackList;
import com.tms.dto.response.CfBlackList;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlackListServiceImpl extends BaseService implements BlackListService {
    private final CfBlackListDao cfBlackListDao;

    public BlackListServiceImpl(CfBlackListDao cfBlackListDao) {
        this.cfBlackListDao = cfBlackListDao;
    }

    @Override
    public List<CfBlackList> getBlackList() throws TMSDbException {
        GetBlackList getBlackList = new GetBlackList();
        DBResponse<List<CfBlackList>> blackLists = cfBlackListDao.getBlackList(sessionId,getBlackList);
        if (blackLists == null || blackLists.getResult().size() == 0){
            throw new TMSDbException("Balcklist is Empty");
        }
        if(blackLists.getErrorCode() != EnumType.DbStatusResp.SUCCESS.getStatus()){
            throw new TMSDbException(blackLists.getErrorMsg());
        }
        return blackLists.getResult();
    }
}
