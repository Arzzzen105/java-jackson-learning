package com.arzzzen.learning.jackson.annotationadvanced;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Context {
    private Context() {}

    private static class ObjectMapperHolder {
        static final ObjectMapper objectMapper = new ObjectMapper();
        static {
            objectMapper.findAndRegisterModules();
        }
    }

    public static ObjectMapper getMapper() {
        return ObjectMapperHolder.objectMapper;
    }
}
