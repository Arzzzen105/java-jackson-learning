# Jackson Object Mapper

This module focuses on learning the **Jackson Object Mapper API**.

The purpose of this module is to understand how to bind JSON data to Java objects (POJOs) and vice versa. The `ObjectMapper` is the most commonly used component in Jackson, providing high-level data binding capabilities.

## Example

The lab includes an example that demonstrates the following scenario:

> Serializing a `DeveloperDto` object to JSON and deserializing it back, showcasing both strict and lenient configurations for handling unknown properties, null values for primitives, and array formats.

This example shows how `ObjectMapper` and `ObjectReader` can be used to customize deserialization behavior using `DeserializationFeature`.
- [DataBindingExample](src/main/java/com/arzzzen/learning/jackson/objectmapper/lab/DataBindingExample.java)

## Tasks

- **Configuring the Object Reader**: Learn how to configure `ObjectReader` to handle specific edge cases, such as accepting empty strings as null objects or handles for other types.
  - [ConfiguringObjectMapper](src/main/java/com/arzzzen/learning/jackson/objectmapper/lab/ConfiguringObjectMapper.java)
- **Parallel Processing and Immutability**: Demonstrate the thread-safety and immutability of `ObjectMapper` and `ObjectReader` by performing multiple mapping operations in parallel using `ExecutorService`.
  - [ParallelMapping](src/main/java/com/arzzzen/learning/jackson/objectmapper/lab/ParallelMapping.java)

## Learning Goals

After completing this module, I should be able to:

- Understand the purpose of the Jackson Object Mapper API
- Serialize Java objects to JSON strings using `ObjectMapper`
- Deserialize JSON strings into Java objects (POJOs)
- Configure `ObjectMapper` and `ObjectReader` using `DeserializationFeature`
- Handle unknown properties and mismatched data types during deserialization

## Technologies Used

- Java
- Jackson Databind
- Maven
- Lombok
