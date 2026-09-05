package com.arzzzen.learning.jackson.objectmapper;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Context {
    private Context() {}

    private static class ObjectMapperHolder {
        static final ObjectMapper objectMapper = new ObjectMapper();
    }

    public static ObjectMapper getMapper() {
        return ObjectMapperHolder.objectMapper;
    }
}
