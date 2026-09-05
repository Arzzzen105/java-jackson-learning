package com.arzzzen.learning.jackson.objectmapper.lab;

import com.arzzzen.learning.jackson.objectmapper.Context;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectReader;
import lombok.*;

import java.util.List;
import java.util.concurrent.*;

public class ParallelMapping {

    @NoArgsConstructor
    @AllArgsConstructor
    @Setter
    @Getter
    public static class Dto {
        private String name;
        private int age;
        private boolean isMarried;
        private String email;
        private String[] skills;
    }

    public enum MappingType { STRICT, LAZY }

    @AllArgsConstructor
    public static class MappingQuery implements Callable<Dto> {
        private String data;
        private MappingType type;

        @Override
        public Dto call() throws Exception {
            ObjectReader reader = Context.getMapper().readerFor(Dto.class);
            if (MappingType.LAZY.equals(type)) {
                reader = reader.with(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
            }

            return reader.readValue(data);
        }
    }

    @SneakyThrows
    public static List<Dto> execute(List<MappingQuery> queries) throws InterruptedException {
        try (ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());)
        {
            List<Future<Dto>> futures = executorService.invokeAll(queries);

            executorService.shutdown();

            if (!executorService.awaitTermination(10, TimeUnit.SECONDS)) return List.of();

            return futures.stream()
                    .map(f -> {
                        try {
                            return f.get();
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .toList();

        }
    }
}
