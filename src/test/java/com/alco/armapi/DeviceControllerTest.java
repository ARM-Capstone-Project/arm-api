package com.alco.armapi;

import com.alco.armapi.application.port.in.DeviceUseCase;
import com.alco.armapi.application.port.in.SensorUseCase;
import com.alco.armapi.domain.model.Device;
import com.alco.armapi.domain.model.Sensor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.boot.test.context.SpringBootTest;
import com.alco.armapi.infrastructure.adapter.api.DeviceController;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class DeviceControllerTest {

    @InjectMocks
    private DeviceController deviceController;

    @Mock
    private DeviceUseCase deviceUseCase;

    @Mock
    private SensorUseCase sensorUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createDevice_ShouldReturnCreatedDevice() {
        Device device = new Device();
        device.setId(UUID.randomUUID());
        when(deviceUseCase.saveDevice(any(Device.class))).thenReturn(device);

        ResponseEntity<Device> response = deviceController.createDevice(device);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(device, response.getBody());
        verify(deviceUseCase, times(1)).saveDevice(device);
    }

    @Test
    void deleteDevice_ShouldReturnNoContent() {
        UUID deviceId = UUID.randomUUID();
        doNothing().when(deviceUseCase).deleteDevice(deviceId);

        ResponseEntity<Void> response = deviceController.deleteDevice(deviceId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(deviceUseCase, times(1)).deleteDevice(deviceId);
    }

    @Test
    void updateDevice_ShouldReturnUpdatedDevice() {
        UUID deviceId = UUID.randomUUID();
        Device device = new Device();
        device.setId(deviceId);
        when(deviceUseCase.updateDevice(any(UUID.class), any(Device.class))).thenReturn(device);

        ResponseEntity<Device> response = deviceController.updateDevice(deviceId, device);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(device, response.getBody());
        verify(deviceUseCase, times(1)).updateDevice(deviceId, device);
    }

    @Test
    void getDeviceById_ShouldReturnDevice() {
        UUID deviceId = UUID.randomUUID();
        Device device = new Device();
        device.setId(deviceId);
        when(deviceUseCase.getDeviceById(deviceId)).thenReturn(device);

        ResponseEntity<Device> response = deviceController.getDeviceById(deviceId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(device, response.getBody());
        verify(deviceUseCase, times(1)).getDeviceById(deviceId);
    }

    @Test
    void getAllDevices_ShouldReturnDeviceList() {
        Device device = new Device();
        List<Device> devices = Collections.singletonList(device);
        when(deviceUseCase.getAllDevices()).thenReturn(devices);

        ResponseEntity<List<Device>> response = deviceController.getAllDevices();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(devices, response.getBody());
        verify(deviceUseCase, times(1)).getAllDevices();
    }

    @Test
    void getDevicesByStatus_ShouldReturnDeviceList() {
        String status = "active";
        Device device = new Device();
        List<Device> devices = Collections.singletonList(device);
        when(deviceUseCase.getDeviceByStatus(status)).thenReturn(devices);

        ResponseEntity<List<Device>> response = deviceController.getDevicesByStatus(status);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(devices, response.getBody());
        verify(deviceUseCase, times(1)).getDeviceByStatus(status);
    }

    @Test
    void getDevicesByLocation_ShouldReturnDeviceList() {
        String location = "Location A";
        Device device = new Device();
        List<Device> devices = Collections.singletonList(device);
        when(deviceUseCase.getDeviceByLocation(location)).thenReturn(devices);

        ResponseEntity<List<Device>> response = deviceController.getDevicesByLocation(location);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(devices, response.getBody());
        verify(deviceUseCase, times(1)).getDeviceByLocation(location);
    }

    @Test
    void getDevicesByType_ShouldReturnDeviceList() {
        String type = "Sensor";
        Device device = new Device();
        List<Device> devices = Collections.singletonList(device);
        when(deviceUseCase.getDeviceByType(type)).thenReturn(devices);

        ResponseEntity<List<Device>> response = deviceController.getDevicesByType(type);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(devices, response.getBody());
        verify(deviceUseCase, times(1)).getDeviceByType(type);
    }

    @Test
    void getDevicesByZoneId_ShouldReturnDeviceList() {
        UUID zoneId = UUID.randomUUID();
        Device device = new Device();
        List<Device> devices = Collections.singletonList(device);
        when(deviceUseCase.getDeviceByZoneId(zoneId)).thenReturn(devices);

        ResponseEntity<List<Device>> response = deviceController.getDevicesByZoneId(zoneId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(devices, response.getBody());
        verify(deviceUseCase, times(1)).getDeviceByZoneId(zoneId);
    }

    @Test
    void createDevice_ShouldReturnInternalServerError_WhenSensorSavingFails() {
    Device device = new Device();
    device.setId(UUID.randomUUID());
    Sensor sensor = new Sensor();
    device.setSensors(Collections.singletonList(sensor));

    when(deviceUseCase.saveDevice(any(Device.class))).thenReturn(device);
    doThrow(new RuntimeException("Error saving sensor")).when(sensorUseCase).saveSensor(any(Sensor.class));

    ResponseEntity<Device> response = deviceController.createDevice(device);

    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    verify(sensorUseCase, times(1)).saveSensor(any(Sensor.class));
}

@Test
void createDevice_ShouldReturnCreatedDevice_WhenNoSensors() {
    Device device = new Device();
    device.setId(UUID.randomUUID());
    when(deviceUseCase.saveDevice(any(Device.class))).thenReturn(device);

    ResponseEntity<Device> response = deviceController.createDevice(device);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(device, response.getBody());
    verify(deviceUseCase, times(1)).saveDevice(device);
}

}