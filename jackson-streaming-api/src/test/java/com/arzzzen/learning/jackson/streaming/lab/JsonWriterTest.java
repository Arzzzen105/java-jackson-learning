package com.arzzzen.learning.jackson.streaming.lab;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonWriterTest {

    @TempDir
    Path tempDir;

    @Test
    @DisplayName("write() should correctly write nested list of points to a JSON file")
    void testWriteHappyPath() throws IOException {
        // ARRANGE
        List<List<Point>> points = List.of(
                List.of(new Point(1.0, 2.0), new Point(3.0, 4.0)),
                List.of(new Point(5.0, 6.0))
        );
        File file = tempDir.resolve("points.json").toFile();

        // ACT
        JsonWriter.write(points, file);

        // ASSERT
        String expectedJson = "[[{\"x\":1.0,\"y\":2.0},{\"x\":3.0,\"y\":4.0}],[{\"x\":5.0,\"y\":6.0}]]";
        String actualJson = Files.readString(file.toPath());

        assertEquals(expectedJson, actualJson);
    }
}
