package com.arzzzen.learning.jackson.annotations.lab;

import com.arzzzen.learning.jackson.annotations.Context;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.EqualsAndHashCode;
import lombok.Getter;

public class DeviceLegacyField {

    private static final ObjectMapper objectMapper = Context.getMapper();

    @JsonRootName("device")
    @Getter
    @EqualsAndHashCode
    public static class Device {
        @JsonProperty(value = "device_id", required = true)
        @JsonSetter(nulls = Nulls.FAIL)
        @JsonAlias("devId")
        public Long id;
        @JsonProperty("device_name")
        public String name;

        public Device(
                @JsonProperty(value = "device_id", required = true)
                @JsonSetter(nulls = Nulls.FAIL) Long id,
                @JsonProperty("device_name") String name
        ) {
            this.id = id;
            this.name = name;
        }
    }

    public static Device deserialize(String json) throws JsonProcessingException {
        return objectMapper.readValue(json, Device.class);
    }
}
