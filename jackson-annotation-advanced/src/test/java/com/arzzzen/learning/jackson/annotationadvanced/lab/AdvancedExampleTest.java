package com.arzzzen.learning.jackson.annotationadvanced.lab;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import static com.arzzzen.learning.jackson.annotationadvanced.lab.AdvancedExample.*;

class AdvancedExampleTest {

    @Test
    void deserializeApiResponse() throws Exception {

        // ARRANGE

        String json = """
                {
                    "code" : 200,
                    "message" : "Entities loaded",
                    "data" : [
                        {
                            "id" : 1,
                            "name" : "Hero",
                            "position" : {
                                "x" : 67,
                                "y" : 123
                            },
                            "level" : 42,
                            "type" : "character"
                        },
                        {
                            "id" : 2,
                            "name" : "Zombie",
                            "position" : {
                                "x" : 69,
                                "y" : 321
                            },
                            "damage" : 12,
                            "hostile" : true,
                            "type" : "monster"
                        }
                    ]
                }
                """;

        List<Entity> expectedEntities = List.of(
                new AdvancedExample.Character(1L, "Hero", new Position(67, 123), 42),
                new Monster(2L, "Zombie", new Position(69, 321), 12, true)
        );

        // ACT

        ApiResponse<List<Entity>> actualEntities = AdvancedExample.deserializeApiResponse(json, apiResponseListTypeRef);

        // ASSERT

        assertThat(actualEntities.code()).isEqualTo(200);
        assertThat(actualEntities.message()).isEqualTo("Entities loaded");
        assertThat(actualEntities.data()).containsExactlyElementsOf(expectedEntities);
    }

    @Test
    void serializeApiResponse() throws JsonProcessingException {
        
        // ARRANGE
        
        ApiResponse<List<Entity>> apiResponse = new ApiResponse<>(
                200, 
                "Entities loaded",
                List.of(
                        new AdvancedExample.Character(1L, "Hero", new Position(67, 123), 42),
                        new Monster(2L, "Zombie", new Position(69, 321), 12, true)
                ));
        
        // ACT
        
        String json = AdvancedExample.serializeApiResponse(apiResponse, apiResponseListTypeRef);

        System.out.println(json);
        
        // ASSERT

        assertThat(json)
                .contains("\"code\":200")
                .contains("\"message\":\"Entities loaded\"")
                .contains("\"data\":[")
                .contains("\"type\":\"character\"")
                .contains("\"type\":\"monster\"")
                .contains("\"id\":1")
                .contains("\"name\":\"Hero\"")
                .contains("\"level\":42")
                .contains("\"id\":2")
                .contains("\"name\":\"Zombie\"")
                .contains("\"damage\":12")
                .contains("\"hostile\":true")
                .contains("\"type\":\"character\"")
                .contains("\"type\":\"monster\"");
    }
}