package com.arzzzen.learning.jackson.objectmapper.lab;

import com.arzzzen.learning.jackson.objectmapper.Context;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

public class DataBindingExample {

    private static final ObjectMapper objectMapper = Context.getMapper();

    public enum ProgrammingLanguage { JAVA, PYTHON, C, RUBY }

    @Setter
    @Getter
    @NoArgsConstructor
    public static class DeveloperDto {
        private String name;
        private int age;
        private ProgrammingLanguage programmingLanguage;
        private String email;
        private List<String> skills;
    }

    String serializeDeveloper(DeveloperDto developer) throws JsonProcessingException {
        return objectMapper.writeValueAsString(developer);
    }

    DeveloperDto deserializeDeveloper(String json, boolean strict) throws JsonProcessingException {
        ObjectReader reader = objectMapper.readerFor(DeveloperDto.class);
        if (strict) {
            reader = reader
                    .with(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                            DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES,
                            DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS)

                    .without(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL,
                            DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        } else {
            reader = reader
                    .without(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                            DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES,
                            DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS)

                    .with(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL,
                            DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        }
        return reader.readValue(json);
    }
}
