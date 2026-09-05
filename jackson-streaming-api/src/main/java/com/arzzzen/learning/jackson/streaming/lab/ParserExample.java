package com.arzzzen.learning.jackson.streaming.lab;

import com.arzzzen.learning.jackson.streaming.Context;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ParserExample {
    public static List<Long> getActiveUsersIds(File file) {
        List<Long> ids = new ArrayList<>();

        try (JsonParser parser = Context.getFactory().createParser(file)) {
            // File should start with array
            if (parser.nextToken() != JsonToken.START_ARRAY) {
                throw new IllegalStateException("Array expected at the start of " + file.getName());
            }

            // Read through fiel until array ends
            while (parser.nextToken() != JsonToken.END_ARRAY) {
                Long currentId = null;
                boolean currentActive = false;

                // Iterate over object until its end
                while (parser.nextToken() != JsonToken.END_OBJECT) {
                    String fieldName = parser.currentName();
                    parser.nextToken(); // Read field value

                    if (fieldName.equals("id")) {
                        currentId = parser.getLongValue(); // Read id
                    } else if (fieldName.equals("status")) {
                        currentActive = ("ACTIVE".equals(parser.getText())); // Check if the user is active
                    }
                }

                if (currentActive && currentId != null) ids.add(currentId); // Add ID if found and user is active
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return ids;
    }
}
