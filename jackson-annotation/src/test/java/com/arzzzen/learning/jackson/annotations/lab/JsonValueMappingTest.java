package com.arzzzen.learning.jackson.annotations.lab;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

class JsonValueMappingTest {

    @Test
    void testSerializeValidPerson() throws JsonProcessingException {
        // ARRANGE
        JsonValueMapping.UserId userId = new JsonValueMapping.UserId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        JsonValueMapping.Person person = new JsonValueMapping.Person(
                userId,
                "John Doe",
                35,
                JsonValueMapping.Person.Gender.MALE,
                LocalDate.of(1987, 8, 15),
                180.5
        );

        // ACT
        String result = JsonValueMapping.serialize(person);

        // ASSERT
        assertThat(result)
                .contains("\"person_id\":\"123e4567-e89b-12d3-a456-426614174000\"")
                .contains("\"name\":\"John Doe\"")
                .contains("\"age\":35")
                .contains("\"gender\":\"MALE\"")
                .contains("\"birth_date\":\"15.08.1987\"")
                .contains("\"height_in_cm\":180.5");
    }

    @Test
    void testDeserializeValidPerson() throws JsonProcessingException {

        // ARRANGE
        String json = """
                {
                "person_id":"123e4567-e89b-12d3-a456-426614174000",
                "name":"Jane Doe",
                "age":30,
                "gender":"FEMALE",
                "birth_date":"10.05.1992",
                "height_in_cm":170.2
                }
                """;

        JsonValueMapping.Person expectedPerson = new JsonValueMapping.Person(
                new JsonValueMapping.UserId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000")),
                "Jane Doe",
                30,
                JsonValueMapping.Person.Gender.FEMALE,
                LocalDate.of(1992, Month.MAY, 10),
                170.2
        );

        // ACT

        JsonValueMapping.Person person = JsonValueMapping.deserialize(json);

        // ASSERT
        assertThat(person)
                .isNotNull()
                .isEqualTo(expectedPerson);
    }

    @Test
    void testDeserializePersonWithoutIdShouldFail() {
        // ARRANGE
        String json = "{" +
                "\"name\":\"Jane Doe\"," +
                "\"age\":30," +
                "\"gender\":\"FEMALE\"," +
                "\"birth_date\":\"10.05.1992\"," +
                "\"height_in_cm\":170.2" +
                "}";

        // ACT & ASSERT
        assertThrows(JsonProcessingException.class, () -> {
            JsonValueMapping.deserialize(json);
        });
    }

    @Test
    void testDeserializePersonWithNullIdShouldFail() {
        // ARRANGE
        String json = "{" +
                "\"person_id\":null," +
                "\"name\":\"Jane Doe\"," +
                "\"age\":30," +
                "\"gender\":\"FEMALE\"," +
                "\"birth_date\":\"10.05.1992\"," +
                "\"height_in_cm\":170.2" +
                "}";

        // ACT & ASSERT
        assertThrows(JsonProcessingException.class, () -> {
            JsonValueMapping.deserialize(json);
        });
    }

}