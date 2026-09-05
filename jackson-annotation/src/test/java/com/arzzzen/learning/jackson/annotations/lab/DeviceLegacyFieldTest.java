package com.arzzzen.learning.jackson.annotations.lab;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeviceLegacyFieldTest {


    @Test
    @DisplayName("deserialize should deserialize device object with valid field names")
    void deserialize_ShouldReturnDeviceObject_WhenValidJsonProvided() throws JsonProcessingException {
        // ARRANGE
        String json = """
                    {
                        "device_id": 12345,
                        "device_name": "Smart Device"
                    }
                """;

        // ACT
        DeviceLegacyField.Device device = DeviceLegacyField.deserialize(json);

        // ASSERT
        assertNotNull(device);
        assertEquals(12345, device.id);
        assertEquals("Smart Device", device.name);
    }

    @Test
    @DisplayName("deserialize should deserialize device object with legacy field names")
    void deserialize_ShouldMapIdUsingJsonAlias_WhenAliasIsUsed() throws JsonProcessingException {
        // ARRANGE
        String json = """
                    {
                        "devId": 67890,
                        "device_name": "Legacy Device"
                    }
                """;

        // ACT
        DeviceLegacyField.Device device = DeviceLegacyField.deserialize(json);

        // ASSERT
        assertNotNull(device);
        assertEquals(67890, device.id);
        assertEquals("Legacy Device", device.name);
    }
}