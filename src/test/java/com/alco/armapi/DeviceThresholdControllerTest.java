package com.alco.armapi;

import com.alco.armapi.application.port.in.DeviceThresholdUseCase;
import com.alco.armapi.domain.model.DeviceThreshold;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import com.alco.armapi.infrastructure.adapter.api.DeviceThresholdController;

class DeviceThresholdControllerTest {

    @InjectMocks
    private DeviceThresholdController deviceThresholdController;

    @Mock
    private DeviceThresholdUseCase deviceThresholdService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createDeviceThreshold_ShouldSaveThreshold() {
        DeviceThreshold threshold = new DeviceThreshold(); // Populate with necessary data

        ResponseEntity<String> response = deviceThresholdController.createDeviceThreshold(threshold);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("DeviceThreshold saved successfully", response.getBody());

        // Verify that the service's save method was called
        verify(deviceThresholdService, times(1)).save(threshold);
    }

    @Test
    void getAllDeviceThresholds_ShouldReturnAllThresholds() {
        DeviceThreshold threshold = new DeviceThreshold(); // Populate with necessary data
        when(deviceThresholdService.findAll()).thenReturn(List.of(threshold));

        ResponseEntity<List<DeviceThreshold>> response = deviceThresholdController.getAllDeviceThresholds();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(threshold, response.getBody().get(0));

        // Verify that the service's findAll method was called
        verify(deviceThresholdService, times(1)).findAll();
    }

    @Test
    void getByDeviceId_ShouldReturnThresholdsByDeviceId() {
        String deviceId = "test-device-id";
        DeviceThreshold threshold = new DeviceThreshold(); // Populate with necessary data
        when(deviceThresholdService.findByDeviceId(deviceId)).thenReturn(List.of(threshold));

        ResponseEntity<List<DeviceThreshold>> response = deviceThresholdController.getByDeviceId(deviceId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(threshold, response.getBody().get(0));

        // Verify that the service's findByDeviceId method was called
        verify(deviceThresholdService, times(1)).findByDeviceId(deviceId);
    }

    @Test
    void deleteDeviceThreshold_ShouldDeleteThreshold() {
        String id = "test-id";
        DeviceThreshold threshold = new DeviceThreshold();
        threshold.setId(id); // Set the ID for the threshold

        ResponseEntity<String> response = deviceThresholdController.deleteDeviceThreshold(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("DeviceThreshold deleted successfully", response.getBody());

        // Verify that the service's delete method was called
        verify(deviceThresholdService, times(1)).delete(threshold);
    }

    @Test
    void deleteAllDeviceThresholds_ShouldDeleteAllThresholds() {
        ResponseEntity<String> response = deviceThresholdController.deleteAllDeviceThresholds();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("All DeviceThresholds deleted successfully", response.getBody());

        // Verify that the service's deleteAll method was called
        verify(deviceThresholdService, times(1)).deleteAll();
    }
}