package com.arzzzen.learning.jackson.annotations.lab;

import com.arzzzen.learning.jackson.annotations.Context;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.Getter;

import java.time.LocalDateTime;

public class AnnotationsExample {

    private static final ObjectMapper objectMapper = Context.getMapper();

    @Getter
    public static class Address {
        private final String city;
        private final String street;

        @JsonCreator
        public Address(
                @JsonProperty("city") String city,
                @JsonProperty("street") String street
        ) {
            this.city = city;
            this.street = street;
        }
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Getter
    public static class UserProfileDto {
        @JsonProperty("account_id")
        private final Long id;

        @JsonProperty(value = "raw_password", access = JsonProperty.Access.WRITE_ONLY)
        private String rawPassword;

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private final LocalDateTime registrationDate;

        @JsonUnwrapped(prefix = "addr_")
        private Address address;

        private final String optionalNote;

        @JsonCreator
        public UserProfileDto(
                @JsonProperty("account_id") Long id,
                @JsonProperty("raw_password") String rawPassword,
                @JsonProperty("registrationDate") LocalDateTime registrationDate,
                @JsonProperty("optionalNote") String optionalNote
        ) {
            this.id = id;
            this.rawPassword = rawPassword;
            this.registrationDate = registrationDate;
            this.optionalNote = optionalNote;
        }

        public UserProfileDto(
                Long id,
                String rawPassword,
                LocalDateTime registrationDate,
                Address address,
                String optionalNote
        ) {
            this.id = id;
            this.rawPassword = rawPassword;
            this.registrationDate = registrationDate;
            this.address = address;
            this.optionalNote = optionalNote;
        }
    }

    public static String serialize(UserProfileDto userProfileDto) throws JsonProcessingException {
        return objectMapper.writeValueAsString(userProfileDto);
    }

    public static UserProfileDto deserialize(String json) throws JsonProcessingException {
        return objectMapper.readValue(json, UserProfileDto.class);
    }
}