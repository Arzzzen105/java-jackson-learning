package com.arzzzen.learning.jackson.tree.lab;

import com.arzzzen.learning.jackson.tree.Context;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class JsonOperationsTest {

    @Test
    void testMaskFieldsRecursive() throws Exception {
        ObjectMapper mapper = Context.getMapper();
        String json = """
                {
                  "password": "secret123",
                  "user": {
                    "name": "John",
                    "password": "userSecret"
                  },
                  "history": [
                    {
                      "action": "login",
                      "password": "oldPassword"
                    }
                  ],
                  "public": "info"
                }
                """;
        JsonNode node = mapper.readTree(json);

        JsonOperations.maskFields(node, "password");

        assertThat(node.get("password").asText()).isEqualTo("***");
        assertThat(node.get("user").get("password").asText()).isEqualTo("***");
        assertThat(node.get("history").get(0).get("password").asText()).isEqualTo("***");
        assertThat(node.get("public").asText()).isEqualTo("info");
        assertThat(node.get("user").get("name").asText()).isEqualTo("John");
    }

    @Test
    void testPatch() throws Exception {
        ObjectMapper mapper = Context.getMapper();
        String targetJson = """
                {
                  "name": "John",
                  "address": {
                    "city": "New York",
                    "zip": "10001"
                  },
                  "tags": ["old"]
                }
                """;
        String patchJson = """
                {
                  "name": "Jane",
                  "address": {
                    "city": "Boston"
                  },
                  "tags": ["new"]
                }
                """;

        com.fasterxml.jackson.databind.node.ObjectNode target = (com.fasterxml.jackson.databind.node.ObjectNode) mapper.readTree(targetJson);
        JsonNode patch = mapper.readTree(patchJson);

        JsonOperations.patch(target, patch);

        assertThat(target.get("name").asText()).isEqualTo("Jane");
        assertThat(target.get("address").get("city").asText()).isEqualTo("Boston");
        assertThat(target.get("address").get("zip").asText()).isEqualTo("10001");
        assertThat(target.get("tags").get(0).asText()).isEqualTo("new");
    }

    @Test
    void testPatchArrayMerge() throws Exception {
        ObjectMapper mapper = Context.getMapper();
        String targetJson = "{\"arr\": [1, 2, 3]}";
        String patchJson = "{\"arr\": [4]}";
        ObjectNode target = (ObjectNode) mapper.readTree(targetJson);
        JsonNode patch = mapper.readTree(patchJson);

        JsonOperations.patch(target, patch);

        assertThat(target.get("arr")).hasSize(3);
        assertThat(target.get("arr").get(0).asInt()).isEqualTo(4);
        assertThat(target.get("arr").get(1).asInt()).isEqualTo(2);
        assertThat(target.get("arr").get(2).asInt()).isEqualTo(3);
    }

    @Test
    void testPatchArrayElements() throws Exception {
        ObjectMapper mapper = Context.getMapper();
        String targetJson = """
                {
                  "items": [
                    {"id": 1, "value": "A"},
                    {"id": 2, "value": "B"}
                  ]
                }
                """;
        String patchJson = """
                {
                  "items": [
                    {"value": "A-updated"},
                    {"value": "B-updated"}
                  ]
                }
                """;
        ObjectNode target = (ObjectNode) mapper.readTree(targetJson);
        JsonNode patch = mapper.readTree(patchJson);

        JsonOperations.patch(target, patch);

        JsonNode items = target.get("items");
        assertThat(items.get(0).get("id").asInt()).isEqualTo(1);
        assertThat(items.get(0).get("value").asText()).isEqualTo("A-updated");
        assertThat(items.get(1).get("id").asInt()).isEqualTo(2);
        assertThat(items.get(1).get("value").asText()).isEqualTo("B-updated");
    }

    @Test
    void testPatchDeleteWithNullNode() throws Exception {
        ObjectMapper mapper = Context.getMapper();
        String targetJson = "{\"name\": \"John\", \"age\": 30}";
        String patchJson = "{\"age\": null, \"city\": \"New York\"}";
        ObjectNode target = (ObjectNode) mapper.readTree(targetJson);
        JsonNode patch = mapper.readTree(patchJson);

        JsonOperations.patch(target, patch);

        assertThat(target.has("name")).isTrue();
        assertThat(target.has("age")).isFalse(); // Should be removed
        assertThat(target.get("city").asText()).isEqualTo("New York");
    }

    @Test
    void testPatchRecursiveDelete() throws Exception {
        ObjectMapper mapper = Context.getMapper();
        String targetJson = "{\"user\": {\"name\": \"John\", \"email\": \"john@example.com\"}}";
        String patchJson = "{\"user\": {\"email\": null}}";
        ObjectNode target = (ObjectNode) mapper.readTree(targetJson);
        JsonNode patch = mapper.readTree(patchJson);

        JsonOperations.patch(target, patch);

        assertThat(target.get("user").has("name")).isTrue();
        assertThat(target.get("user").has("email")).isFalse();
    }
}
