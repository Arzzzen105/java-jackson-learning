package com.arzzzen.learning.jackson.tree;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Context {
    private static class MapperHolder {
        static final ObjectMapper objectMapper = new ObjectMapper();
    }

    public static ObjectMapper getMapper() {
        return MapperHolder.objectMapper;
    }
}
