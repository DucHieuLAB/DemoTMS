package com.tms.dao;

import com.tms.commons.DBResponse;
import com.tms.dto.request.blacklist.GetBlackList;
import com.tms.dto.response.BlackList;
import com.tms.dto.response.ScheduleUpdate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlackListDao extends BaseDao{

    private static final String GET_BLACKLIST = "get_blacklist";

    /* BEGIN GET */
    public DBResponse<List<BlackList>> getBlackList(String sessionId, GetBlackList params) {
        return this.dbGet(sessionId, GET_BLACKLIST, params, BlackList.class);
    }
    /* END GET */

}
