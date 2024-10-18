package com.alco.armapi.infrastructure.adapter.api;

import com.alco.armapi.application.port.in.DeviceUseCase;
import com.alco.armapi.application.port.in.SensorUseCase;
import com.alco.armapi.domain.model.Device;
import com.alco.armapi.domain.model.Sensor;
import com.alco.armapi.infrastructure.adapter.persistence.sensor.SensorEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/devices")
public class DeviceController {

    @Autowired
    private DeviceUseCase deviceUseCase;

    @Autowired
    private SensorUseCase sensorUseCase;

    //create
    @PostMapping
    public ResponseEntity<Device> createDevice(@RequestBody Device device) {
        Device savedDevice = deviceUseCase.saveDevice(device);

        if (device.getSensors() != null && !device.getSensors().isEmpty()) {
        for (Sensor sensorNew : device.getSensors()) {
            Sensor sensor = new Sensor();
            sensor.setName(sensorNew.getName());
            sensor.setType(sensorNew.getType());
            sensor.setStatus(sensorNew.getStatus());
            sensor.setUnit(sensorNew.getUnit());
            sensor.setDevice(savedDevice);
            sensorUseCase.saveSensor(sensor);
        }
    }
        return ResponseEntity.ok(savedDevice);
    }

    //delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDevice(@PathVariable UUID id) {
        deviceUseCase.deleteDevice(id);
        return ResponseEntity.noContent().build();
    }

    //update
    @PutMapping("/{id}")
    public ResponseEntity<Device> updateDevice(@PathVariable UUID id, @RequestBody Device device) {
        Device updatedDevice = deviceUseCase.updateDevice(id, device);
        return ResponseEntity.ok(updatedDevice);
    }

    //get methods
    @GetMapping("/{id}")
    public ResponseEntity<Device> getDeviceById(@PathVariable UUID id) {
        Device device = deviceUseCase.getDeviceById(id);
        return ResponseEntity.ok(device);
    }

    // Get all devices
    @GetMapping
    public ResponseEntity<List<Device>> getAllDevices() {
        List<Device> devices = deviceUseCase.getAllDevices();
        return ResponseEntity.ok(devices);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Device>> getDevicesByStatus(@PathVariable String status) {
        List<Device> devices = deviceUseCase.getDeviceByStatus(status);
        return ResponseEntity.ok(devices);
    }

    @GetMapping("/location/{location}")
    public ResponseEntity<List<Device>> getDevicesByLocation(@PathVariable String location) {
        List<Device> devices = deviceUseCase.getDeviceByLocation(location);
        return ResponseEntity.ok(devices);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<Device>> getDevicesByType(@PathVariable String type) {
        List<Device> devices = deviceUseCase.getDeviceByType(type);
        return ResponseEntity.ok(devices);
    }

    @GetMapping("/zone/{zoneId}")
    public ResponseEntity<List<Device>> getDevicesByZoneId(@PathVariable UUID zoneId) {
        List<Device> devices = deviceUseCase.getDeviceByZoneId(zoneId);
        return ResponseEntity.ok(devices);
    }
}