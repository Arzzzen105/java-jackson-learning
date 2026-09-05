package com.arzzzen.learning.jackson.objectmapper.lab;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static com.arzzzen.learning.jackson.objectmapper.lab.ConfiguringObjectMapper.FooDto;

class ConfiguringObjectMapperTest {

    @ParameterizedTest
    @DisplayName("Reader should accept empty strings, numbers as strings and valid integers")
    @MethodSource("provideJsons")
    void test(String json, FooDto expectedFoo) throws JsonProcessingException {
        // ARRANGE

        // ACT
        FooDto actualFoo = ConfiguringObjectMapper.deserialize(json);

        // ASSERT
        assertEquals(expectedFoo, actualFoo);
    }

    private static Stream<Arguments> provideJsons() {
        return Stream.of(
                Arguments.of("{\"id\" : 1}", new FooDto(1L)),
                Arguments.of("{\"id\" : \"2\"}", new FooDto(2L)),
                Arguments.of("{\"id\" : \"\"}", new FooDto(null))
        );
    }
}
