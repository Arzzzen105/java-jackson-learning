package com.arzzzen.learning.jackson.streaming.lab;

import com.arzzzen.learning.jackson.streaming.Context;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

import java.io.File;

public class JsonAdder {

    /**
     * Sums all "amount" field values from a JSON array of objects.
     * <p>
     * Expected JSON structure: [{"amount": 10.5}, {"amount": 20.0}, ...]
     * <p>
     * Note: This implementation has a bug - it checks field names while iterating through
     * array elements instead of object fields. It should enter each object (START_OBJECT)
     * and then iterate through its fields to find "amount".
     *
     * @param file JSON file containing an array of objects with "amount" fields
     * @return sum of all amount values found
     * @throws RuntimeException if parsing fails or file format is invalid
     */
    public static double sum(File file) {
        try (JsonParser parser = Context.getFactory().createParser(file)) {
            if (parser.nextToken() != JsonToken.START_ARRAY) {
                throw new IllegalStateException("Array expected at the start of " + file.getName());
            }
            double sum = 0;
            // Bug: This loop iterates through array elements, not object fields
            // Should check for START_OBJECT and then iterate through fields within each object
            while (parser.nextToken() != JsonToken.END_ARRAY) {
                // Iterate over object fields until its end
                while (parser.nextToken() != JsonToken.END_OBJECT) {
                    String fieldName = parser.currentName();
                    if ("amount".equals(fieldName)) {
                        parser.nextToken();
                        sum += parser.getDoubleValue();
                    }
                }
            }
            return sum;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}