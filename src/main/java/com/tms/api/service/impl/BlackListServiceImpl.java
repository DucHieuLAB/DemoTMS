package com.tms.api.service.impl;

import com.tms.api.commons.ApiMessageError;
import com.tms.api.consts.EnumType;
import com.tms.api.consts.MessageConst;
import com.tms.api.exception.ErrorMessages;
import com.tms.api.exception.TMSDbException;
import com.tms.api.exception.TMSEntityNotFoundException;
import com.tms.api.exception.TMSException;
import com.tms.api.helper.Helper;
import com.tms.api.service.BlackListService;
import com.tms.api.service.BaseService;
import com.tms.commons.DBResponse;
import com.tms.dao.CfBlackListDao;
import com.tms.dto.request.blacklist.GetBlackList;
import com.tms.dto.response.CfBlackList;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class BlackListServiceImpl extends BaseService implements BlackListService {
    private final CfBlackListDao cfBlackListDao;

    public BlackListServiceImpl(CfBlackListDao cfBlackListDao) {
        this.cfBlackListDao = cfBlackListDao;
    }

    @Override
    public List<CfBlackList> getBlackList() throws TMSException {
        GetBlackList getBlackList = new GetBlackList();
        DBResponse<List<CfBlackList>> blackLists = cfBlackListDao.getBlackList(sessionId,getBlackList);
        if(blackLists.getErrorCode() != EnumType.DbStatusResp.SUCCESS.getStatus()){
            throw new TMSDbException(blackLists.getErrorMsg());
        }
        if (CollectionUtils.isEmpty(blackLists.getResult())) {
            String errorMessage = MessageConst.NOT_FOUND_WITH_OBJECT_PARAMS + Helper.toJson(getBlackList);
            throw new TMSEntityNotFoundException(ErrorMessages.NOT_FOUND, new ApiMessageError(errorMessage));
        }
        return blackLists.getResult();
    }
}
