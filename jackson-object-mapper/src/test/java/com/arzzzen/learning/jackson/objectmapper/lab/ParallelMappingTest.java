package com.arzzzen.learning.jackson.objectmapper.lab;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class ParallelMappingTest {

    private static final String LAZY_JSON = "{\"name\":\"User %d\",\"age\": 20,\"married\":false,\"email\":\"user@example.com\",\"skills\":\"Java\"}";

    @Test
    void testParallelMapping() {

        // ARRANGE

        List<ParallelMapping.MappingQuery> queries = new ArrayList<>();

        int expectedFailures = 7;
        int expectedSuccesses = 8;

        for (int i = 0; i < expectedFailures; i++) {
            queries.add(new ParallelMapping.MappingQuery(
                    String.format(LAZY_JSON, i),
                    ParallelMapping.MappingType.STRICT
            ));
        }
        for (int i = 0; i < expectedSuccesses; i++) {
            queries.add(new ParallelMapping.MappingQuery(
                    String.format(LAZY_JSON, i),
                    ParallelMapping.MappingType.LAZY
            ));
        }

        // ACT

        int successCount = 0;
        int failureCount = 0;

        for (ParallelMapping.MappingQuery query : queries) {
            try {
                ParallelMapping.Dto dto = query.call();
                if (dto != null) {
                    successCount++;
                }
            } catch (Exception e) {
                failureCount++;
            }
        }

        // ASSERT

        assertThat(successCount).isEqualTo(expectedSuccesses);
        assertThat(failureCount).isEqualTo(expectedFailures);
    }
}
