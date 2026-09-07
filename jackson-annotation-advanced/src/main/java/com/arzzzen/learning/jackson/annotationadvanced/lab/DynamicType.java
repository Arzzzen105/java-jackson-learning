package com.arzzzen.learning.jackson.annotationadvanced.lab;

import com.arzzzen.learning.jackson.annotationadvanced.Context;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DynamicType {

    private static final ObjectMapper objectMapper = Context.getMapper();

    public record ApiResponse<T> (
        T data,
        String message,
        boolean success
    ) {}

    public static <T> ApiResponse<T> parseApiResponse(String json, Class<T> type) throws JsonProcessingException {
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ApiResponse.class, type);
        return objectMapper.readValue(json, javaType);
    }
}
