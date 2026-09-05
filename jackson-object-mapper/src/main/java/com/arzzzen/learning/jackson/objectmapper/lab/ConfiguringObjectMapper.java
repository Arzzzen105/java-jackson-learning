package com.arzzzen.learning.jackson.objectmapper.lab;

import com.arzzzen.learning.jackson.objectmapper.Context;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectReader;
import lombok.*;

public class ConfiguringObjectMapper {

    private static final ObjectReader reader = Context.getMapper().readerFor(FooDto.class)
            .with(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    public static class FooDto {
        private Long id;
    }

    public static FooDto deserialize(String json) throws JsonProcessingException {
        return reader.readValue(json);
    }
}
