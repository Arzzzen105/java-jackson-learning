package com.arzzzen.learning.jackson.annotations.lab;

import com.arzzzen.learning.jackson.annotations.Context;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

public class JsonValueMapping {

    private static final ObjectMapper objectMapper = Context.getMapper();


    @Getter
    @EqualsAndHashCode
    public static class UserId {
        private final UUID id;

        @JsonCreator
        public UserId(String id) {
            this.id = UUID.fromString(id);
        }

        public UserId(UUID id) {
            this.id = id;
        }

        @JsonValue
        public String getUi() {
            return id.toString();
        }
    }

    @Getter
    @EqualsAndHashCode
    public static class Person {
        public enum Gender { MALE, FEMALE }

        @JsonProperty(value = "person_id", required = true)
        @JsonSetter(nulls = Nulls.FAIL)
        private UserId id;

        private final String name;

        private final int age;

        private final Gender gender;

        @JsonProperty("birth_date")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
        private final LocalDate birthDate;

        @JsonProperty("height_in_cm")
        private final double height;

        @JsonCreator
        public Person(
                @JsonProperty(value = "person_id", required = true)
                @JsonSetter(nulls = Nulls.FAIL) UserId id,
                @JsonProperty("name") String name,
                @JsonProperty("age") int age,
                @JsonProperty("gender") Gender gender,
                @JsonProperty("birth_date") LocalDate birthDate,
                @JsonProperty("height_in_cm") double height
        ) {
            this.id = id;
            this.name = name;
            this.age = age;
            this.gender = gender;
            this.birthDate = birthDate;
            this.height = height;
        }
    }

    public static String serialize(Person person) throws JsonProcessingException {
        return objectMapper.writeValueAsString(person);
    }

    public static Person deserialize(String json) throws JsonProcessingException {
        return objectMapper.readValue(json, Person.class);
    }

}
