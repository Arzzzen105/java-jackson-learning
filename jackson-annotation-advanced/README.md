# Jackson Advanced Annotation Mapping

This module focuses on learning **Advanced Jackson Annotation Mapping** techniques.

The purpose of this module is to understand how to handle complex scenarios such as polymorphic data structures and generic type mapping. These features are essential for building robust and flexible APIs that can handle diverse data models and dynamic responses.

## Example

The lab includes an example that demonstrates the following scenario:

> Managing a game world with different types of entities (`Character`, `Monster`) that share common properties but have unique ones. The example showcases polymorphic deserialization where the specific class is determined by a `"type"` property in the JSON. Additionally, it demonstrates how to handle generic `ApiResponse` wrappers using `TypeReference`.

This example shows how `@JsonTypeInfo` and `@JsonSubTypes` are used for polymorphism and how `TypeReference` preserves generic type information during deserialization.
- [AdvancedExample](src/main/java/com/arzzzen/learning/jackson/annotationadvanced/lab/AdvancedExample.java)

## Tasks

- **Polymorphism by Deduction**: Implement a utility that uses `JsonTypeInfo.Id.DEDUCTION` to infer the correct subtype based on the presence of unique fields in the JSON, without requiring an explicit type property.
  - [DeductionPolymorphism](src/main/java/com/arzzzen/learning/jackson/annotationadvanced/lab/DeductionPolymorphism.java)
- **Dynamic Type Construction**: Demonstrate how to use `JavaType` and `TypeFactory` to programmatically construct parametric types (generics) at runtime, allowing for flexible parsing of API responses with varying data types.
  - [DynamicType](src/main/java/com/arzzzen/learning/jackson/annotationadvanced/lab/DynamicType.java)

## Learning Goals

After completing this module, I should be able to:

- Implement polymorphic deserialization using `@JsonTypeInfo` and `@JsonSubTypes`
- Use Type Deduction to resolve subtypes based on field signatures
- Handle Generic types during serialization and deserialization using `TypeReference`
- Programmatically construct complex `JavaType` instances for dynamic data binding
- Design flexible API models that can handle heterogeneous collections and wrapped responses

## Technologies Used

- Java
- Jackson Databind
- Maven
- Lombok
