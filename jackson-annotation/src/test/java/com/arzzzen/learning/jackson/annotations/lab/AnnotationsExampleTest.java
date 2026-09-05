package com.arzzzen.learning.jackson.annotations.lab;

import com.arzzzen.learning.jackson.annotations.lab.AnnotationsExample.UserProfileDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

class AnnotationsExampleTest {


    @Test
    @DisplayName("deserialize should deserialize all fields including WRITE_ONLY fields")
    void testDeserializeWithAllFields() throws JsonProcessingException {

        // ARRANGE
        String json = """
                    {
                        "account_id": 1,
                        "raw_password": "mySecretPassword",
                        "registrationDate": "2023-09-01 12:30:00",
                        "addr_city": "New York",
                        "addr_street": "5th Avenue",
                        "optionalNote": "Active User"
                    }
                """;

        // ACT

        UserProfileDto userProfileDto = AnnotationsExample.deserialize(json);

        // ASSERT

        assertEquals(1L, userProfileDto.getId());
        assertEquals("mySecretPassword", userProfileDto.getRawPassword());
        assertEquals(LocalDateTime.of(2023, Month.SEPTEMBER, 1, 12, 30, 0), userProfileDto.getRegistrationDate());
        assertNotNull(userProfileDto.getAddress());
        assertEquals("New York", userProfileDto.getAddress().getCity());
        assertEquals("5th Avenue", userProfileDto.getAddress().getStreet());
        assertEquals("Active User", userProfileDto.getOptionalNote());
    }


    @Test
    @DisplayName("deserialize should deserialize only required fields")
    void testDeserializeWithoutOptionalNote() throws JsonProcessingException {

        // ARRANGE

        String json = """
                    {
                        "account_id": 2,
                        "raw_password": "anotherSecretPassword",
                        "registrationDate": "2023-09-02 15:45:30",
                        "addr_city": "Los Angeles",
                        "addr_street": "Sunset Boulevard"
                    }
                """;

        // ACT

        UserProfileDto userProfileDto = AnnotationsExample.deserialize(json);

        // ASSERT

        assertEquals(2L, userProfileDto.getId());
        assertEquals("anotherSecretPassword", userProfileDto.getRawPassword());
        assertEquals(LocalDateTime.of(2023, Month.SEPTEMBER, 2, 15, 45, 30), userProfileDto.getRegistrationDate());
        assertNotNull(userProfileDto.getAddress());
        assertEquals("Los Angeles", userProfileDto.getAddress().getCity());
        assertEquals("Sunset Boulevard", userProfileDto.getAddress().getStreet());
        assertNull(userProfileDto.getOptionalNote());
    }

    @Test
    @DisplayName("serialize should not include WRITE_ONLY fields")
    void testSerializeShouldNotIncludePassword() throws JsonProcessingException {

        // ARRANGE

        UserProfileDto user = new UserProfileDto(
                1L,
                "secret",
                LocalDateTime.of(2026, Month.SEPTEMBER, 5, 23, 31),
                new AnnotationsExample.Address("San Francisco", "123 Main St"),
                "Active User"
        );

        // ACT
        String userJson = AnnotationsExample.serialize(user);


        // ASSERT
        assertThat(userJson)
                .contains("\"account_id\":1",
                        "\"registrationDate\":\"2026-09-05 23:31:00\"",
                        "\"addr_city\":\"San Francisco\"",
                        "\"addr_street\":\"123 Main St\"",
                        "\"optionalNote\":\"Active User\"")
                .doesNotContain("\"raw_password\":\"secret\"");
    }
}