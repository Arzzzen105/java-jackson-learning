# Jackson Learning Project

### Intro
This project is a comprehensive guide and practice repository for mastering the **Jackson Project** (JSON processor for Java). It covers everything from low-level streaming processing to advanced annotation-based data binding and polymorphic deserialization.

### Modules shortlist
- [Jackson Core Streaming API](jackson-streaming-api/)
- [Jackson Tree Model](jackson-tree-model/)
- [Jackson Object Mapper](jackson-object-mapper/)
- [Jackson Annotations](jackson-annotation/)
- [Jackson Advanced Annotation Mapping](jackson-annotation-advanced/)

### Module structure
Each module in this project follows a consistent structure to facilitate learning:
- **Topic**: A specific area of the Jackson library.
- **README**: A dedicated guide for the module, explaining the core concepts.
- **Example**: A practical, real-world scenario implementation showcasing the topic.
- **Tasks**: Hands-on exercises to apply the learned concepts.
- **Test**: A suite of unit tests to verify the correctness of the task implementations.

### Modules descriptive list
1. **Jackson Core Streaming API**: Focuses on high-performance, low-level parsing and generation using `JsonParser` and `JsonGenerator`. Ideal for processing large JSON files with minimal memory footprint.
2. **Jackson Tree Model**: Explores the hierarchical representation of JSON using `JsonNode`. Provides flexibility for navigating and modifying JSON structures without mapping them to Java classes.
3. **Jackson Object Mapper**: Covers the most popular part of Jackson—the `ObjectMapper` API. Teaches how to bind JSON to POJOs and customize serialization/deserialization behavior.
4. **Jackson Annotations**: Demonstrates how to use declarative annotations (like `@JsonProperty`, `@JsonInclude`, `@JsonFormat`) to control the mapping process without changing code logic.
5. **Jackson Advanced Annotation Mapping**: Dives into complex scenarios including polymorphic types (`@JsonTypeInfo`), type deduction, and handling generics with `TypeReference` and `JavaType`.

### Outro
By exploring these modules and completing the tasks, you will gain a deep understanding of Jackson's capabilities and how to choose the right tool for different JSON processing needs in Java applications.

### Used technologies
- **Java 21**
- **Jackson** (Core, Databind, Annotations)
- **Maven**
- **JUnit 5** & **AssertJ** (for testing)
- **Lombok**
