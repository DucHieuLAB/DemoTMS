package com.tms.config;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DBConfig {

    private static final String MEMORY = "mem";
    private static final String DB = "db";
    private static final int DEFAULT_TIMEOUT = 60; // 1MIN

    private String funcName;
    private String qMode;
    private List<Long> qTimeout;
    private Boolean qFallback;
    private Boolean alwaysWriteFile;

    public boolean isMemoryQuery() {
        return MEMORY.equals(qMode);
    }

    public boolean isDBQuery() {
        return DB.equals(qMode);
    }

    public boolean isFallBack() {
        return qFallback != null && qFallback;
    }

    public long getMemTimeout() {
        return !qTimeout.isEmpty() ? qTimeout.get(0) * 1000 : DEFAULT_TIMEOUT * 1000;
    }

    public long getDBTimeout() {
        return qTimeout.size() > 1 ? qTimeout.get(1) * 1000 : DEFAULT_TIMEOUT * 1000;
    }
}
