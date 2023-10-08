package com.tms.api.scheduled;

import lombok.Getter;
import lombok.Setter;
import com.tms.dto.response.ClBasket;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.TimeUnit;
import java.util.List;

@Getter
@Setter
public class DuplicateLeadChecker {
    private static Cache<String, String> records = CacheBuilder.newBuilder()
            .expireAfterWrite(24, TimeUnit.HOURS)
            .build();

    public static void addRecord(ClBasket data) {
        records.put(data.getPhone(), data.getProdName());
    }

    public static void addRecord(List<ClBasket> datas) {
        for (ClBasket data : datas) {
            records.put(data.getPhone(), data.getProdName());
        }
    }

    public static boolean isEmpty() {
        return records.size() == 0 ? true : false;
    }

    public static boolean isDuplicate(String key, String value) {
        String storedValue = records.getIfPresent(key);
        return storedValue != null && storedValue.equals(value);
    }
}
