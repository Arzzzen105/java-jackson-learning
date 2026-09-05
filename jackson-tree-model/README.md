# Jackson Tree Model

This module focuses on learning the **Jackson Tree Model API**.

The purpose of this module is to understand how to process JSON data using a hierarchical tree representation (`JsonNode`). This approach provides a flexible and intuitive way to navigate, search, and modify JSON structures without needing to map them to specific Java classes (POJOs).

## Example

The lab includes an example that demonstrates the following scenario:

> Processing a webhook payload by extracting specific fields using path-based navigation and modifying the JSON structure by adding metadata and removing sensitive information.

This example shows how `JsonNode` and `ObjectNode` can be used to read specific values (like an event type or an ID from a nested array) and perform in-place modifications to the JSON document.
- [TreeExample](src/main/java/com/arzzzen/learning/jackson/tree/lab/TreeExample.java)
- [TreeExampleTest](src/test/java/com/arzzzen/learning/jackson/tree/lab/TreeExampleTest.java)

## Tasks

- **Masking Fields**: Implement a utility that masks specified sensitive fields (e.g., `"password"`, `"email"`) in a JSON tree by replacing their values with `"***"`. The utility should work recursively for nested objects and arrays.
  - [JsonOperations.maskFields](src/main/java/com/arzzzen/learning/jackson/tree/lab/JsonOperations.java)
- **JSON Patching**: Implement a deep merge utility that applies a "patch" JSON to a "target" JSON object. The patch should support adding new fields, updating existing values, recursively merging nested objects and arrays, and deleting fields when a `null` value is provided in the patch.
  - [JsonOperations.patch](src/main/java/com/arzzzen/learning/jackson/tree/lab/JsonOperations.java)

## Learning Goals

After completing this module, I should be able to:

- Understand the purpose of the Jackson Tree Model API
- Navigate JSON structures using `JsonNode.get()`, `path()`, and `at()` (JSON Pointer)
- Distinguish between `JsonNode` (read-only) and `ObjectNode`/`ArrayNode` (mutable)
- Modify JSON documents by adding, updating, or removing fields
- Convert between JSON strings and `JsonNode` objects using `ObjectMapper`

## Technologies Used

- Java
- Jackson Databind
- Maven
