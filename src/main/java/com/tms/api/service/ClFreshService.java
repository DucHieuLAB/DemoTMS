package com.tms.api.service;

import com.tms.api.exception.TMSDbException;
import com.tms.dto.request.clFresh.InsClFresh;

import java.util.List;

import org.springframework.stereotype.Service;

@Service                         
public interface ClFreshService {
    public void insertClFresh(List<InsClFresh> clFreshes, String sessionId) throws TMSDbException;
}
