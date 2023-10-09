package com.tms.api.service;

import com.tms.api.exception.TMSDbException;
import com.tms.dto.response.CfBlackList;

import java.util.List;

public interface BlackListService {

    List<CfBlackList> getBlackList() throws TMSDbException;

}
