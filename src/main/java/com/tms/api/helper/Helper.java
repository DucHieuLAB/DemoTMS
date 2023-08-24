package com.tms.api.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Helper {
    private static final Logger logger = LoggerFactory.getLogger(Helper.class);

    public static String toJson(Object obj) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException ex) {
            logger.info(ex.getMessage());
            return "{}";
        }
    }
}
