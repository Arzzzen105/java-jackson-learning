# Jackson Annotations

This module focuses on learning the **Jackson Annotations API**.

The purpose of this module is to understand how to use Jackson annotations to customize the serialization and deserialization process. Annotations provide a declarative way to control how Java objects are mapped to JSON and vice versa, without changing the core mapping logic.

## Example

The lab includes an example that demonstrates the following scenario:

> Customizing a `UserProfileDto` object using multiple Jackson annotations to control property naming, inclusion of null values, date formatting, and flatting nested objects.

This example shows how annotations like `@JsonProperty`, `@JsonInclude`, `@JsonFormat`, `@JsonUnwrapped`, and `@JsonCreator` can be used to tailor the JSON representation of a Java object.
- [AnnotationsExample](src/main/java/com/arzzzen/learning/jackson/annotations/lab/AnnotationsExample.java)

## Tasks

- **Custom Value Mapping**: Implement a utility that uses `@JsonValue` to serialize a complex value object (like `UserId`) as a single primitive value (like a `String`). Also, explore `@JsonSetter` for handling null values and `@JsonFormat` for date/time customization.
  - [JsonValueMapping](src/main/java/com/arzzzen/learning/jackson/annotations/lab/JsonValueMapping.java)
- **Handling Legacy Fields**: Demonstrate the use of `@JsonAlias` to support multiple field names during deserialization, allowing for backward compatibility with legacy JSON structures.
  - [DeviceLegacyField](src/main/java/com/arzzzen/learning/jackson/annotations/lab/DeviceLegacyField.java)

## Learning Goals

After completing this module, I should be able to:

- Understand the purpose of Jackson Annotations
- Use `@JsonProperty` to map Java fields to JSON properties with different names
- Use `@JsonInclude` to exclude null or empty values from the serialized output
- Use `@JsonFormat` to customize the format of dates, times, and other types
- Use `@JsonUnwrapped` to flatten nested objects into the parent JSON structure
- Use `@JsonCreator` and `@JsonProperty` to support immutable POJOs with constructor-based injection
- Use `@JsonValue` to provide a custom representation for a class during serialization
- Use `@JsonAlias` to provide alternative names for a property during deserialization

## Technologies Used

- Java
- Jackson Databind
- Maven
- Lombok
