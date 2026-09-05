package com.arzzzen.learning.jackson.tree.lab;

import com.arzzzen.learning.jackson.tree.Context;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class TreeExample {

    public static String processWebhookPayload(String rawJson) throws Exception {
        ObjectMapper mapper = Context.getMapper();

        JsonNode rootNode = mapper.readTree(rawJson);

        JsonNode eventNode = rootNode.get("event");

        String eventType = eventNode.path("type").asText("UNKNOWN");
        if ("UNKNOWN".equals(eventType)) {
            throw new IllegalArgumentException("Unknown event type");
        }

        JsonNode firstActorNode = rootNode.at("/payload/audit/0/actor");
        Long actorId = firstActorNode.path("id").asLong(-1L);

        if (rootNode instanceof ObjectNode objectNode) {
            ObjectNode metadataObject = objectNode.putObject("processingMetadata");
            metadataObject.put("processedAtEpochMillis", System.currentTimeMillis());
            metadataObject.put("extractedActorId", actorId);
            objectNode.remove("secrets");
        }

        return mapper.writeValueAsString(rootNode);
    }
}
