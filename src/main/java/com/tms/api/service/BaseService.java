package com.tms.api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Component
public abstract class BaseService implements HandlerInterceptor {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    protected String sessionId;
    protected int curUserId;
    protected int curOrgId;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        sessionId = UUID.randomUUID().toString();
        curUserId = 0;
        curOrgId = 4;
        return true;
    }
}
