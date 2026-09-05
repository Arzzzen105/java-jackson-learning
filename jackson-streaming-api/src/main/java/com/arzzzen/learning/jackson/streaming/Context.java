package com.arzzzen.learning.jackson.streaming;

import com.fasterxml.jackson.core.JsonFactory;

public class Context {
    private Context() {}

    private static class JsonFactoryHolder {
        static final JsonFactory jsonFactory = new JsonFactory();
    }

    public static JsonFactory getFactory() {
        return JsonFactoryHolder.jsonFactory;
    }
}
