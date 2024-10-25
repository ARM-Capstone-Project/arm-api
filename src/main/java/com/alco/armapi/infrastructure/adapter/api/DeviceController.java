package com.alco.armapi.infrastructure.adapter.api;

import com.alco.armapi.application.port.in.DeviceUseCase;
import com.alco.armapi.application.port.in.SensorUseCase;
import com.alco.armapi.application.port.in.DeviceSensorReadingUseCase;
import com.alco.armapi.domain.model.Device;
import com.alco.armapi.domain.model.Sensor;
import com.alco.armapi.domain.model.readings.ReadingDevice;
import com.alco.armapi.domain.model.readings.ReadingSensor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.transaction.annotation.Transactional;

@CrossOrigin(origins = "*", maxAge = 3600)
@Slf4j
@RestController
@RequestMapping("/api/devices")
public class DeviceController {

    @Autowired
    private DeviceUseCase deviceUseCase;

    @Autowired
    private SensorUseCase sensorUseCase;

    @Autowired
    private DeviceSensorReadingUseCase deviceSensorReadingUseCase;

    // Create device
    @PostMapping
    public ResponseEntity<Device> createDevice(@RequestBody Device device) {
        try {
            Device savedDevice = deviceUseCase.saveDevice(device);
            if (device.getSensors() != null && !device.getSensors().isEmpty()) {
                for (Sensor sensorNew : device.getSensors()) {
                    Sensor sensor = new Sensor();
                    sensor.setName(sensorNew.getName());
                    sensor.setType(sensorNew.getType());
                    sensor.setStatus(sensorNew.getStatus());
                    sensor.setUnit(sensorNew.getUnit());
                    sensor.setDeviceId(device.getId());
                    sensorUseCase.saveSensor(sensor);
                }
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(savedDevice);
        } catch (Exception e) {
            log.error("Error creating device: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Delete device
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDevice(@PathVariable UUID id) {
        try {
            deviceUseCase.deleteDevice(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            log.error("Error deleting device with ID {}: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Update device
    @PutMapping("/{id}")
    public ResponseEntity<Device> updateDevice(@PathVariable UUID id, @RequestBody Device device) {
        try {
            Device updatedDevice = deviceUseCase.updateDevice(id, device);
            return ResponseEntity.ok(updatedDevice);
        } catch (Exception e) {
            log.error("Error updating device with ID {}: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Get device by ID
    @GetMapping("/{id}")
    public ResponseEntity<Device> getDeviceById(@PathVariable UUID id) {
        try {
            Optional<Device> optionalDevice = deviceUseCase.getDeviceById(id);
            log.info("Fetching device with ID: {}", id);

            Device device = new Device();
            if (optionalDevice.isPresent()) {
                device = optionalDevice.get();
                } 
            else {
                log.warn("No device found for ID {}", id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
                }

            // Update sensor changes from readings when calling device for update
            List<ReadingDevice> devicesFromReading = deviceSensorReadingUseCase.getDeviceFromReading();
            String tagNo = device.getTagNo();
            
            ReadingDevice readingDevice = devicesFromReading.stream()
                .filter(rd -> rd.getDeviceId().equals(tagNo))
                .findFirst()
                .orElse(null);

             if(readingDevice != null && readingDevice.getReadings() != null){
                log.info("update sensor from getDeviceByID");
                updateSensorFromReading(tagNo, readingDevice);
            }
            return ResponseEntity.status(HttpStatus.OK).body(device);
        } catch (Exception e) { 
            log.error("Error fetching device with ID {}: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Get all devices
    @GetMapping
    public ResponseEntity<List<Device>> getAllDevices() {
        List<Device> devices = deviceUseCase.getAllDevices();
        return ResponseEntity.ok(devices);
    }

    // Get devices by status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Device>> getDevicesByStatus(@PathVariable String status) {
        List<Device> devices = deviceUseCase.getDeviceByStatus(status);
        return ResponseEntity.ok(devices);
    }

    // Get devices by location
    @GetMapping("/location/{location}")
    public ResponseEntity<List<Device>> getDevicesByLocation(@PathVariable String location) {
        List<Device> devices = deviceUseCase.getDeviceByLocation(location);
        return ResponseEntity.ok(devices);
    }

    // Get devices by type
    @GetMapping("/type/{type}")
    public ResponseEntity<List<Device>> getDevicesByType(@PathVariable String type) {
        List<Device> devices = deviceUseCase.getDeviceByType(type);
        return ResponseEntity.ok(devices);
    }

    // Get devices by zone ID
    @GetMapping("/zone/{zoneId}")
    public ResponseEntity<List<Device>> getDevicesByZoneId(@PathVariable UUID zoneId) {
        List<Device> devices = deviceUseCase.getDeviceByZoneId(zoneId);
        return ResponseEntity.ok(devices);
    }

    // Get new devices from reading
    @GetMapping("/new")
    public ResponseEntity<List<ReadingDevice>> getNewDevicesFromReading() {
        List<ReadingDevice> devicesFromReading = deviceSensorReadingUseCase.getDeviceFromReading();
        List<ReadingDevice> newDevicesFromReading = new ArrayList<>();

        for (ReadingDevice readingDevice : devicesFromReading) {
            //UUID deviceId = UUID.fromString(readingDevice.getId());
            String tagNoFromReading = readingDevice.getDeviceId();
            Device existingDevice = deviceUseCase.getDeviceByTagNo(tagNoFromReading);

            if (existingDevice == null) {
            //     if (!existingDevice.getTagNo().equals(readingDevice.getDeviceId())) {
            //         existingDevice.setTagNo(readingDevice.getDeviceId()); // Update TagNo, unique for user
            //     }
            //     updateSensorFromReading(deviceId, readingDevice); // Update sensor changes
            // } else {
                newDevicesFromReading.add(readingDevice);
            }
            updateSensorFromReading(tagNoFromReading, readingDevice);//create new sensors
        }
        return ResponseEntity.ok(newDevicesFromReading);
    }

    // Update sensors based on reading
    @Transactional
    private void updateSensorFromReading(String tagNo, ReadingDevice readingDevice) {
        Device device = deviceUseCase.getDeviceByTagNo(tagNo);

        if (device == null) {
            Device defaultDevice = new Device();
                defaultDevice.setTagNo(tagNo);
                defaultDevice.setName("Default Device");

                deviceUseCase.saveDevice(defaultDevice);//create default device

                device = deviceUseCase.getDeviceByTagNo(tagNo);
        }
        UUID deviceId = device.getId();

        List<Sensor> existingSensors = sensorUseCase.getSensorsByDeviceId(deviceId);
        List<ReadingSensor> readingSensors = readingDevice.getReadings();

        // Check all sensors
        if(!readingSensors.isEmpty() || readingDevice != null){
        for (ReadingSensor readingSensor : readingSensors) {
            Sensor existingSensor = findExistingSensor(existingSensors, readingSensor);

            if (existingSensor == null || existingSensors.isEmpty()) { // New sensors from readings
                Sensor newSensor = new Sensor();
                newSensor.setName(readingSensor.getSensor());
                newSensor.setType(readingSensor.getSensor());
                newSensor.setUnit(readingSensor.getUnit());
                newSensor.setDeviceId(deviceId);

                sensorUseCase.saveSensor(newSensor);
            }
        }

        // Check sensor status
        for (Sensor sensor : existingSensors) {
            boolean active = isSensorActive(sensor, readingSensors);
            if (!active) {
                sensor.setStatus("inactive");
            }
        }
    }
    }

    // Find existing sensor
    private Sensor findExistingSensor(List<Sensor> existingSensors, ReadingSensor readingSensor) {
        return existingSensors.stream()
            .filter(sensor -> sensor.getName().equals(readingSensor.getSensor()) &&
                    sensor.getUnit().equals(readingSensor.getUnit()))
            .findFirst()
            .orElse(null);
    }

    // Check if sensor is active
    private boolean isSensorActive(Sensor existingSensor, List<ReadingSensor> readingSensors) {
        return readingSensors.stream()
            .anyMatch(readingSensor -> readingSensor.getSensor().equals(existingSensor.getName()) &&
                    readingSensor.getUnit().equals(existingSensor.getUnit()));
    }
}