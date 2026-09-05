package com.arzzzen.learning.jackson.tree.lab;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.*;

import java.util.HashMap;
import java.util.Map;

public class JsonOperations {

    private static final String MASK = "***";

    /**
     * Masks specified fields in a JSON node by replacing their values with "***".
     * This method recursively processes all nested objects and arrays.
     *
     * @param node   the JSON node to process
     * @param fields the field names to mask
     */
    public static void maskFields(JsonNode node, String... fields) {
        if (node.isObject()) {
            ObjectNode objectNode = (ObjectNode) node;
            for (String field : fields) {
                if (objectNode.has(field)) {
                    objectNode.put(field, MASK);
                }
            }
        }

        for (JsonNode childNode : node) {
            maskFields(childNode, fields);
        }
    }


    public static void patch(ObjectNode target, JsonNode patch) {
        if (patch.isNull() || !patch.isObject()) {
            return;
        }

        patch.fields().forEachRemaining(entry -> {
            String fieldName = entry.getKey();
            JsonNode patchValue = entry.getValue();
            JsonNode targetValue = target.get(fieldName);

            if (patchValue.isNull()) {
                target.remove(fieldName);
            } else if (targetValue != null && targetValue.isObject() && patchValue.isObject()) {
                patch((ObjectNode) targetValue, patchValue);
            } else if (targetValue != null && targetValue.isArray() && patchValue.isArray()) {
                patchArray((ArrayNode) targetValue, (ArrayNode) patchValue);
            } else {
                target.set(fieldName, patchValue);
            }
        });
    }

    private static void patchArray(ArrayNode target, ArrayNode patch) {
        for (int i = 0; i < patch.size(); i++) {
            JsonNode patchElement = patch.get(i);
            if (i < target.size()) {
                JsonNode targetElement = target.get(i);
                if (targetElement.isObject() && patchElement.isObject()) {
                    patch((ObjectNode) targetElement, patchElement);
                } else if (targetElement.isArray() && patchElement.isArray()) {
                    patchArray((ArrayNode) targetElement, (ArrayNode) patchElement);
                } else {
                    target.set(i, patchElement);
                }
            } else {
                target.add(patchElement);
            }
        }
    }
}
