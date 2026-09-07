package com.arzzzen.learning.jackson.annotationadvanced.lab;

import com.arzzzen.learning.jackson.annotationadvanced.Context;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class AdvancedExample {

    private static final ObjectMapper objectMapper = Context.getMapper();

    public static final TypeReference<ApiResponse<List<Entity>>> apiResponseListTypeRef = new TypeReference<>() {};

    public record Position(
            int x,
            int y
    ) {}

    @JsonTypeInfo(
            use = JsonTypeInfo.Id.NAME,
            include = JsonTypeInfo.As.PROPERTY,
            property = "type"
    )
    @JsonSubTypes({
            @JsonSubTypes.Type(value = Character.class, name = "character"),
            @JsonSubTypes.Type(value = Monster.class, name = "monster")
    })
    public sealed interface Entity permits Character, Monster {
        Long id();
        String name();
        Position position();
    }

    public record Character (
            Long id,
            String name,
            Position position,
            int level
    ) implements Entity {}

    public record Monster (
            Long id,
            String name,
            Position position,
            int damage,
            boolean hostile
    ) implements Entity {}

    public record ApiResponse<T> (
            int code,
            String message,
            T data
    ) {}


    public static <T> String serializeApiResponse(
            ApiResponse<T> apiResponse,
            TypeReference<ApiResponse<T>> typeReference
    ) throws JsonProcessingException {
        return objectMapper.writerFor(typeReference).writeValueAsString(apiResponse);
    }

    public static <T> ApiResponse<T> deserializeApiResponse(
            String responseJson,
            TypeReference<ApiResponse<T>> typeReference
    ) throws Exception {
        return objectMapper.readValue(responseJson, typeReference);
    }
}
