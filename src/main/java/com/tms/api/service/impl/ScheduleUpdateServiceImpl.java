package com.tms.api.service.impl;

import com.tms.api.commons.ApiMessageError;
import com.tms.api.consts.EnumType.DbStatusResp;
import com.tms.api.consts.MessageConst;
import com.tms.api.exception.*;
import com.tms.api.helper.Helper;
import com.tms.api.service.BaseService;
import com.tms.api.service.ScheduleUpdateService;
import com.tms.commons.DBResponse;
import com.tms.dao.CfScheduleUpdateDao;
import com.tms.dto.request.schedule.GetScheduleUpdate;
import com.tms.dto.request.schedule.InsScheduleUpdate;
import com.tms.dto.request.schedule.UpdScheduleUpdate;
import com.tms.dto.response.ScheduleUpdate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class ScheduleUpdateServiceImpl extends BaseService implements ScheduleUpdateService {
    private final CfScheduleUpdateDao cfScheduleUpdateService;

    public ScheduleUpdateServiceImpl(CfScheduleUpdateDao cfScheduleUpdateService) {
        this.cfScheduleUpdateService = cfScheduleUpdateService;
    }

    @Override
    public ScheduleUpdate getScheduleUpdateById(int id) {
        return new ScheduleUpdate();
    }
    @Override
    public List<ScheduleUpdate> getScheduleUpdate(GetScheduleUpdate getScheduleUpdate) throws TMSException {
        DBResponse<List<ScheduleUpdate>> getScheduleDbResp = cfScheduleUpdateService.getScheduleUpdate(sessionId, getScheduleUpdate);
        if (getScheduleDbResp.getErrorCode() != DbStatusResp.SUCCESS.getStatus()) {
            throw new TMSDbException(getScheduleDbResp.getErrorMsg());
        }
        if (CollectionUtils.isEmpty(getScheduleDbResp.getResult())) {
            String errorMessage = MessageConst.NOT_FOUND_WITH_OBJECT_PARAMS + Helper.toJson(getScheduleUpdate);
            throw new TMSEntityNotFoundException(ErrorMessages.NOT_FOUND, new ApiMessageError(errorMessage));
        }
        return getScheduleDbResp.getResult();
    }
    @Override
    public boolean insScheduleUpdate(InsScheduleUpdate insScheduleUpdate) throws TMSDbException {
        insScheduleUpdate.setCreatedBy(curUserId);
        DBResponse<String> insScheduleDbResp = cfScheduleUpdateService.insScheduleUpdate(sessionId, insScheduleUpdate);
        if (insScheduleDbResp.getErrorCode() != DbStatusResp.SUCCESS.getStatus()) {
            throw new TMSDbException(insScheduleDbResp.getErrorMsg());
        }
        return true;
    }
    @Override
    public boolean updScheduleUpdate(int scheduleId, UpdScheduleUpdate updScheduleUpdate) throws TMSException {
        updScheduleUpdate.setModifiedBy(curUserId);
        DBResponse<String> insScheduleDbResp = cfScheduleUpdateService.updScheduleUpdate(sessionId, updScheduleUpdate);
        if (insScheduleDbResp.getErrorCode() != DbStatusResp.SUCCESS.getStatus()) {
            throw new TMSDbException(insScheduleDbResp.getErrorMsg());
        }
        return true;
    }
}
