# Jackson Core Streaming API

This module focuses on learning the low-level **Jackson Core Streaming API**.

The purpose of this module is to understand how to process JSON data efficiently using Jackson’s streaming approach, especially when working with large JSON files where loading the entire document into memory is not practical.

## Example

The lab includes an example that demonstrates the following scenario:

> Extracting user identifiers for users with the status `"ACTIVE"` from a large JSON file without significant memory consumption.

This example shows how JSON can be read token by token, allowing only the required parts of the data to be processed. This approach is useful when performance and memory efficiency are important.
- [ParserExample](src/main/java/com/arzzzen/learning/jackson/streaming/lab/ParserExample.java)

## Tasks

- **Allocation-free counting**: Write a utility using `JsonParser` that takes a JSON file with a list of transactions and calculates the sum of the `amount` (double) field without creating a single `String` instance for numeric values.
  - [JsonAdder](src/main/java/com/arzzzen/learning/jackson/streaming/lab/JsonAdder.java)
- **JSON Generation**: Create a utility using `JsonGenerator` that writes a valid JSON file containing nested arrays and random coordinates `{"x": 12.34, "y": 56.78}`.
  - [JsonWriter](src/main/java/com/arzzzen/learning/jackson/streaming/lab/JsonWriter.java)

## Learning Goals

After completing this module, I should be able to:

- Understand the purpose of the Jackson Core Streaming API
- Read JSON data using a low-level token-based approach
- Process large JSON files without loading them fully into memory
- Extract specific fields from JSON based on specific conditions
- Apply memory-efficient JSON parsing techniques in Java

## Technologies Used

- Java
- Jackson Core
- Maven