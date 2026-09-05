package com.arzzzen.learning.jackson.tree.lab;

import com.arzzzen.learning.jackson.tree.Context;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TreeExampleTest {

    @Test
    @DisplayName("processWebhookPayload should extract actorId, add processingMetadata and remove secrets")
    void testProcessWebhookPayload() throws Exception {
        // ARRANGE
        String inputJson = """
                {
                  "event": {
                    "type": "USER_CREATED"
                  },
                  "payload": {
                    "audit": [
                      {
                        "actor": {
                          "id": 12345,
                          "name": "Admin"
                        }
                      }
                    ]
                  },
                  "secrets": "top-secret-key"
                }
                """;

        // ACT
        String outputJson = TreeExample.processWebhookPayload(inputJson);

        // ASSERT
        ObjectMapper mapper = Context.getMapper();
        JsonNode rootNode = mapper.readTree(outputJson);

        assertThat(rootNode.at("/event/type").asText()).isEqualTo("USER_CREATED");

        assertThat(rootNode.has("secrets")).isFalse();

        assertThat(rootNode.has("processingMetadata")).isTrue();
        JsonNode metadata = rootNode.get("processingMetadata");
        assertThat(metadata.get("extractedActorId").asLong()).isEqualTo(12345L);
        assertThat(metadata.has("processedAtEpochMillis")).isTrue();
        assertThat(metadata.get("processedAtEpochMillis").isLong()).isTrue();
    }
}
