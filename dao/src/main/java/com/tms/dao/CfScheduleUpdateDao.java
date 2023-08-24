package com.tms.dao;

import com.tms.commons.DBResponse;
import com.tms.dto.request.schedule.GetScheduleUpdate;
import com.tms.dto.request.schedule.InsScheduleUpdate;
import com.tms.dto.request.schedule.UpdScheduleUpdate;
import com.tms.dto.response.ScheduleUpdate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CfScheduleUpdateDao extends BaseDao {
    private static final String GET_SCHEDULE_UPDATE = "get_schedule_update";

    private static final String INS_SCHEDULE_UPDATE = "ins_schedule_update";

    private static final String UPD_SCHEDULE_UPDATE = "upd_schedule_update";

    /* BEGIN GET */
    public DBResponse<List<ScheduleUpdate>> getScheduleUpdate(String sessionId, GetScheduleUpdate params) {
        return this.dbGet(sessionId, GET_SCHEDULE_UPDATE, params, ScheduleUpdate.class);
    }
    /* END GET */

    /* BEGIN INSERT */
    public DBResponse<String> insScheduleUpdate(String sessionId, InsScheduleUpdate params) {
        return this.dbInsOrUpd(sessionId, INS_SCHEDULE_UPDATE, params);
    }
    /* END INSERT */

    /* BEGIN UPDATE */
    public DBResponse<String> updScheduleUpdate(String sessionId, UpdScheduleUpdate params) {
        return this.dbInsOrUpd(sessionId, UPD_SCHEDULE_UPDATE, params);
    }
    /* END UPDATE */
}
