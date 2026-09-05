package com.arzzzen.learning.jackson.streaming.lab;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class JsonAdderTest {

    private static final File testFile = new File("src/test/resources/test_users.json");


    @Test
    @DisplayName("sum() should correctly sum all amount fields")
    void testSum() {
        //ARRANGE
        double expectedSum = 120.0;

        //ACT
        double actualSum = JsonAdder.sum(testFile);

        //ASSERT
        assertEquals(expectedSum, actualSum, 0.001);
    }
}