package com.arzzzen.learning.jackson.streaming.lab;

import com.arzzzen.learning.jackson.streaming.Context;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonWriter {

    /**
     * Writes a nested list of Point objects to a JSON file using Jackson's streaming API.
     * <p>
     * The output JSON structure is a two-dimensional array where each inner array contains
     * Point objects represented as JSON objects with "x" and "y" numeric fields.
     * <p>
     * Example output: [[{"x":1.0,"y":2.0},{"x":3.0,"y":4.0}],[{"x":5.0,"y":6.0}]]
     *
     * @param points a nested list of Point objects to be written to JSON
     * @param file   the target file where JSON data will be written
     * @throws RuntimeException if an I/O error occurs during writing
     */
    public static void write(List<List<Point>> points, File file) {
        try (JsonGenerator jsonGenerator = Context.getFactory().createGenerator(file, JsonEncoding.UTF8)) {
            jsonGenerator.writeStartArray();
            for (List<Point> pointsList : points) {
                jsonGenerator.writeStartArray();
                for (Point point : pointsList) {
                    jsonGenerator.writeStartObject();
                    jsonGenerator.writeNumberField("x", point.x());
                    jsonGenerator.writeNumberField("y", point.y());
                    jsonGenerator.writeEndObject();
                }
                jsonGenerator.writeEndArray();
            }
            jsonGenerator.writeEndArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
