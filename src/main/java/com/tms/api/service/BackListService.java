package com.tms.api.service;

import com.tms.api.exception.TMSDbException;
import com.tms.dto.response.CfBlackList;

import java.util.List;

public interface BackListService {
    public List<CfBlackList> getBlackList(String sessionId) throws TMSDbException;
}
