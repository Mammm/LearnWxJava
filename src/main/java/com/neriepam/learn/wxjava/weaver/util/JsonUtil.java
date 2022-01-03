package com.neriepam.learn.wxjava.weaver.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
    private static final ObjectMapper INSTANCE = new ObjectMapper();

    public static <T> T readValue(String json, Class<T> clazz) {
        try {
            return INSTANCE.readValue(json, clazz);
        } catch (JsonProcessingException e) {
           throw new RuntimeException(e);
        }
    }
}
