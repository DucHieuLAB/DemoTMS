package com.tms.api.service;

import com.tms.api.exception.TMSDbException;
import com.tms.api.exception.TMSException;
import com.tms.dto.request.schedule.GetScheduleUpdate;
import com.tms.dto.request.schedule.InsScheduleUpdate;
import com.tms.dto.request.schedule.UpdScheduleUpdate;
import com.tms.dto.response.ScheduleUpdate;

import java.util.List;

public interface ScheduleUpdateService {
    ScheduleUpdate getScheduleUpdateById(int id);

    List<ScheduleUpdate> getScheduleUpdate(GetScheduleUpdate getScheduleUpdate) throws TMSException;

    boolean insScheduleUpdate(InsScheduleUpdate insScheduleUpdate) throws TMSDbException;

    boolean updScheduleUpdate(int scheduleId, UpdScheduleUpdate updScheduleUpdate) throws TMSException;
}
