package com.alco.armapi;

import com.alco.armapi.application.port.in.SensorUseCase;
import com.alco.armapi.domain.model.Sensor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import com.alco.armapi.infrastructure.adapter.api.SensorController;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class SensorControllerTest {

    @InjectMocks
    private SensorController sensorController;

    @Mock
    private SensorUseCase sensorUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createSensor_ShouldReturnCreatedSensor() {
        Sensor sensor = new Sensor();
        when(sensorUseCase.saveSensor(any(Sensor.class))).thenReturn(sensor);

        ResponseEntity<Sensor> response = sensorController.createSensor(sensor);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(sensor, response.getBody());
        verify(sensorUseCase, times(1)).saveSensor(sensor);
    }

    @Test
    void deleteSensor_ShouldReturnNoContent() {
        UUID sensorId = UUID.randomUUID();
        doNothing().when(sensorUseCase).deleteSensor(sensorId);

        ResponseEntity<Void> response = sensorController.deleteSensor(sensorId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(sensorUseCase, times(1)).deleteSensor(sensorId);
    }

    @Test
    void updateSensor_ShouldReturnUpdatedSensor() {
        UUID sensorId = UUID.randomUUID();
        Sensor sensor = new Sensor();
        when(sensorUseCase.updateSensor(any(UUID.class), any(Sensor.class))).thenReturn(sensor);

        ResponseEntity<Sensor> response = sensorController.updateSensor(sensorId, sensor);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(sensor, response.getBody());
        verify(sensorUseCase, times(1)).updateSensor(sensorId, sensor);
    }

    @Test
    void getSensorById_ShouldReturnSensor() {
        UUID sensorId = UUID.randomUUID();
        Sensor sensor = new Sensor();
        when(sensorUseCase.getSensorById(sensorId)).thenReturn(sensor);

        ResponseEntity<Sensor> response = sensorController.getSensorById(sensorId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(sensor, response.getBody());
        verify(sensorUseCase, times(1)).getSensorById(sensorId);
    }

    @Test
    void getAllSensors_ShouldReturnSensorList() {
        Sensor sensor = new Sensor();
        List<Sensor> sensors = Collections.singletonList(sensor);
        when(sensorUseCase.getAllSensors()).thenReturn(sensors);

        ResponseEntity<List<Sensor>> response = sensorController.getAllSensors();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(sensors, response.getBody());
        verify(sensorUseCase, times(1)).getAllSensors();
    }

    @Test
    void getSensorsByStatus_ShouldReturnSensorList() {
        String status = "active";
        Sensor sensor = new Sensor();
        List<Sensor> sensors = Collections.singletonList(sensor);
        when(sensorUseCase.getSensorByStatus(status)).thenReturn(sensors);

        ResponseEntity<List<Sensor>> response = sensorController.getSensorsByStatus(status);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(sensors, response.getBody());
        verify(sensorUseCase, times(1)).getSensorByStatus(status);
    }

    @Test
    void getSensorsByType_ShouldReturnSensorList() {
        String type = "temperature";
        Sensor sensor = new Sensor();
        List<Sensor> sensors = Collections.singletonList(sensor);
        when(sensorUseCase.getSensorByType(type)).thenReturn(sensors);

        ResponseEntity<List<Sensor>> response = sensorController.getSensorsByType(type);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(sensors, response.getBody());
        verify(sensorUseCase, times(1)).getSensorByType(type);
    }

    @Test
    void getSensorsByName_ShouldReturnSensorList() {
        String name = "TempSensor";
        Sensor sensor = new Sensor();
        List<Sensor> sensors = Collections.singletonList(sensor);
        when(sensorUseCase.getSensorByName(name)).thenReturn(sensors);

        ResponseEntity<List<Sensor>> response = sensorController.getSensorsByName(name);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(sensors, response.getBody());
        verify(sensorUseCase, times(1)).getSensorByName(name);
    }

    @Test
    void getSensorsByDeviceId_ShouldReturnSensorList() {
        UUID deviceId = UUID.randomUUID();
        Sensor sensor = new Sensor();
        List<Sensor> sensors = Collections.singletonList(sensor);
        when(sensorUseCase.getSensorsByDeviceId(deviceId)).thenReturn(sensors);

        ResponseEntity<List<Sensor>> response = sensorController.getSensorsByDeviceId(deviceId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(sensors, response.getBody());
        verify(sensorUseCase, times(1)).getSensorsByDeviceId(deviceId);
    }
}