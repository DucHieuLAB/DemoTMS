package com.tms.api.service;

import com.tms.api.exception.TMSException;
import com.tms.dto.response.CfBlackList;

import java.util.List;

public interface BlackListService {

    List<CfBlackList> getBlackList() throws TMSException;

}
