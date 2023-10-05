package com.tms.api.service;

import com.tms.api.exception.TMSDbException;
import com.tms.dto.request.clFresh.InsClFresh;
import com.tms.dto.request.clFresh.UpdClFresh;

import java.util.List;

public interface ClFreshService {
     void insertClFresh(List<InsClFresh> clFreshes, String sessionId) throws TMSDbException;

    boolean updClFreshAfterValidSaleOrder(UpdClFresh updClFresh) throws TMSDbException;
}
