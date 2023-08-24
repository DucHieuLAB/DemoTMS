package com.tms.api.consts;

import com.tms.api.AppProperties;

public class Const {
    public static final String REDIS_PREFIX_GLOBAL = "global" + AppProperties.getPropertyValue("config.redis-suffix");
}
