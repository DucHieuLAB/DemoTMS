package com.tms.api.helper;

import com.tms.api.consts.Const;
import org.springframework.data.redis.core.StringRedisTemplate;

public class RedisHelper {

    public static String createRedisPrefix(String prefix, int orgId) {
        return prefix + ":" + orgId;
    }

    public static String createRedisField(Integer type, Integer paramId) {
        return type + ":" + paramId;
    }

    public static String getGlobalParamValue(StringRedisTemplate stringRedisTemplate, int orgId, Integer type, Integer paramId) {
        String key = createRedisPrefix(Const.REDIS_PREFIX_GLOBAL, orgId);
        return getKey(stringRedisTemplate, key, createRedisField(type, paramId));
    }

    public static String getKey(StringRedisTemplate stringRedisTemplate, String key) {
        Object obj = stringRedisTemplate.opsForValue().get(key);
        return obj == null ? null : String.valueOf(obj);
    }

    public static String getKey(StringRedisTemplate stringRedisTemplate, String key, String field) {
        Object obj = stringRedisTemplate.opsForHash().get(key, field);
        return obj == null ? null : String.valueOf(obj);
    }
}