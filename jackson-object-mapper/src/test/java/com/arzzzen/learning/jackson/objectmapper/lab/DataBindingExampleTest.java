package com.arzzzen.learning.jackson.objectmapper.lab;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DataBindingExampleTest {

    private final DataBindingExample example = new DataBindingExample();

    @Test
    @DisplayName("serializeDeveloper should serialize DeveloperDto to JSON")
    void testSerialization() throws JsonProcessingException {

        // ARRANGE
        DataBindingExample.DeveloperDto developer = new DataBindingExample.DeveloperDto();
        developer.setName("Arsen");
        developer.setAge(19);
        developer.setProgrammingLanguage(DataBindingExample.ProgrammingLanguage.JAVA);
        developer.setEmail("arsen@example.com");
        developer.setSkills(Arrays.asList("Java", "Spring"));

        // ACT

        String json = example.serializeDeveloper(developer);

        // ASSERT

        assertThat(json).contains("\"name\":\"Arsen\"")
                .contains("\"age\":19")
                .contains("\"programmingLanguage\":\"JAVA\"")
                .contains("\"email\":\"arsen@example.com\"")
                .contains("\"skills\":[\"Java\",\"Spring\"]");
    }

    @Test
    @DisplayName("deserializeDeveloper should throw MismatchedInputException when enum field is given as number")
    void testStrictDeserializationNumberAsEnum() {
        // ARRANGE
        String jsonWithNumberEnum = "{\"name\":\"Alice\",\"programmingLanguage\":0}";

        // ACT & ASSERT
        assertThatThrownBy(() -> example.deserializeDeveloper(jsonWithNumberEnum, true))
                .isInstanceOf(MismatchedInputException.class);
    }

    @Test
    @DisplayName("deserializeDeveloper should throw UnrecognizedPropertyException when unknown property is given")
    void testStrictDeserializationUnknownProperty() {
        // ARRANGE
        String json = "{\"name\":\"Alice\",\"unknownField\":\"value\"}";

        // ACT & ASSERT
        assertThatThrownBy(() -> example.deserializeDeveloper(json, true))
                .isInstanceOf(UnrecognizedPropertyException.class);
    }

    @Test
    @DisplayName("deserializeDeveloper should throw MismatchedInputException when null is given for primitive type")
    void testStrictDeserializationNullForPrimitive() {
        // ARRANGE
        String jsonWithNullAge = "{\"name\":\"Alice\",\"age\":null}";

        // ACT & ASSERT
        assertThatThrownBy(() -> example.deserializeDeveloper(jsonWithNullAge, true))
                .isInstanceOf(MismatchedInputException.class);

    }

    @Test
    @DisplayName("deserializeDeveloper should deserialize DeveloperDto from JSON")
    void testNonStringDeserialization() throws JsonProcessingException {
        // Test non-strict mode features:
        // 1. Unknown properties ignored
        // 2. Single value as array
        // 3. Unknown enum as null (though we test existing enum here)

        String json = "{\"name\":\"Alice\",\"unknownField\":\"value\",\"skills\":\"Java\"}";
        
        DataBindingExample.DeveloperDto developer = example.deserializeDeveloper(json, false);
        
        assertThat(developer.getName()).isEqualTo("Alice");
        assertThat(developer.getSkills()).containsExactly("Java");
    }
}
