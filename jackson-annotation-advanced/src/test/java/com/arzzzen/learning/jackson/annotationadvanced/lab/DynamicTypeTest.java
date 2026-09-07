package com.arzzzen.learning.jackson.annotationadvanced.lab;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DynamicTypeTest {


    @Test
    @DisplayName("parseApiResponse should parse the JSON into the specified type")
    void test() throws JsonProcessingException {

        // ARRANGE

        String json = """
                {
                    "success": true,
                    "message": "Operation successful",
                    "data": "Hello, World!"
                }
                """;

        // ACT

        DynamicType.ApiResponse<String> response = DynamicType.parseApiResponse(json, String.class);

        // ASSERT

        assertTrue(response.success());
        assertEquals("Operation successful", response.message());
        assertEquals("Hello, World!", response.data());;
    }
}
