package com.alco.armapi;

import com.alco.armapi.application.port.in.DeviceSensorReadingUseCase;
import com.alco.armapi.domain.model.readings.DeviceSensorReading;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.alco.armapi.infrastructure.adapter.api.SensorReadingController;

class SensorReadingControllerTest {

    @InjectMocks
    private SensorReadingController sensorReadingController;

    @Mock
    private DeviceSensorReadingUseCase deviceSensorReadingUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createSensorReading_ShouldSaveReading() {
        DeviceSensorReading reading = new DeviceSensorReading(); // Populate with necessary data

        ResponseEntity<String> response = sensorReadingController.createSensorReading(reading);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("SensorReading saved successfully", response.getBody());

        // Verify that the service's save method was called
        verify(deviceSensorReadingUseCase, times(1)).save(reading);
    }

    @Test
    void getAllSensorReadings_ShouldReturnAllReadings() {
        DeviceSensorReading reading = new DeviceSensorReading(); // Populate with necessary data
        when(deviceSensorReadingUseCase.findAll()).thenReturn(List.of(reading));

        ResponseEntity<List<DeviceSensorReading>> response = sensorReadingController.getAllSensorReadings();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(reading, response.getBody().get(0));

        // Verify that the service's findAll method was called
        verify(deviceSensorReadingUseCase, times(1)).findAll();
    }

    @Test
    void getByDeviceId_ShouldReturnReadingsForDeviceId() {
        String deviceId = "test-device-id";
        DeviceSensorReading reading = new DeviceSensorReading(); // Populate with necessary data
        when(deviceSensorReadingUseCase.findByDeviceId(deviceId)).thenReturn(List.of(reading));

        ResponseEntity<List<DeviceSensorReading>> response = sensorReadingController.getByDeviceId(deviceId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(reading, response.getBody().get(0));

        // Verify that the service's findByDeviceId method was called
        verify(deviceSensorReadingUseCase, times(1)).findByDeviceId(deviceId);
    }

    @Test
    void deleteAllSensorReadings_ShouldDeleteAllReadings() {
        ResponseEntity<String> response = sensorReadingController.deleteAllSensorReadings();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("All SensorReadings deleted successfully", response.getBody());

        // Verify that the service's deleteAll method was called
        verify(deviceSensorReadingUseCase, times(1)).deleteAll();
    }

    @Test
    void saveDHT22_ShouldSaveDHT22Readings() {
        ResponseEntity<String> response = sensorReadingController.saveDHT22();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("DHT22 Sensor Readings saved successfully", response.getBody());

        // Verify that the service's saveList method was called with the appropriate data
        verify(deviceSensorReadingUseCase, times(1)).saveList(any());
    }

    @Test
    void saveMethaneSensorReadingTest_ShouldSaveMethaneReadings() {
        ResponseEntity<String> response = sensorReadingController.saveMethaneSensorReadingTest();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Methane Sensor Readings saved successfully", response.getBody());

        // Verify that the service's saveList method was called with the appropriate data
        verify(deviceSensorReadingUseCase, times(1)).saveList(any());
    }
}