package com.tms.config;

import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Getter
@Setter
public class DBConfigMap {
    private final Map<String, DBConfig> configMaps = new ConcurrentHashMap<>();
    private List<DBConfig> configs;
    private String filePath;

    @PostConstruct
    private void initMap() {
        for (DBConfig config : configs) {
            configMaps.put(config.getFuncName(), config);
        }
    }

    public DBConfig lookup(String fncName) {
        if (!configMaps.isEmpty()) {
            return configMaps.get(fncName);
        }
        return null;
    }
}
