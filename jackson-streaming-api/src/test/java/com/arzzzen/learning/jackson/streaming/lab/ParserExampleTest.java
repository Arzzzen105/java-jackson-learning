package com.arzzzen.learning.jackson.streaming.lab;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

class ParserExampleTest {

    private static final File testFile = new File("src/test/resources/test_users.json");

    @Test
    @DisplayName("getActiveUsersIds should find valid ids of active users")
    void test() {

        // ARRANGE
        List<Long> expectedIds = List.of(1L, 4L, 5L, 6L);

        // ACT
        List<Long> actualIds = ParserExample.getActiveUsersIds(testFile);

        // ASSERT
        assertThat(actualIds)
                .containsExactlyElementsOf(expectedIds);
    }
}
