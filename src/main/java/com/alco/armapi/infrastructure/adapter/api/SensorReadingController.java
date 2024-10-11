package com.alco.armapi.infrastructure.adapter.api;


import com.alco.armapi.application.port.in.DeviceSensorReadingUseCase;

import com.alco.armapi.domain.model.readings.DeviceSensorReading;
import com.alco.armapi.util.DHT22DataGenerator;
import com.alco.armapi.util.MethaneDataGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/sensor-readings")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // Allow all origins
public class SensorReadingController {

    private  final DeviceSensorReadingUseCase deviceSensorReadingUseCase;

    @PostMapping()
    public ResponseEntity<String> createSensorReading(@RequestBody DeviceSensorReading deviceSensorReading) {
        log.info("Creating device threshold: {}", deviceSensorReading);
        deviceSensorReadingUseCase.save(deviceSensorReading);
        return ResponseEntity.ok("SensorReading saved successfully");
    }

    @GetMapping()
    public ResponseEntity<List<DeviceSensorReading>> getAllSensorReadings() {
        log.info("Fetching all sensor reading");
        List<DeviceSensorReading> thresholds = deviceSensorReadingUseCase.findAll();
        return ResponseEntity.ok(thresholds);
    }

    @GetMapping("/by-device-id/{deviceId}")
    public ResponseEntity<List<DeviceSensorReading>> getByDeviceId(@PathVariable String deviceId) {
        log.info("Fetching sensor reading for deviceId: {}", deviceId);
        List<DeviceSensorReading> thresholds = deviceSensorReadingUseCase.findByDeviceId(deviceId);
        return ResponseEntity.ok(thresholds);
    }

    @DeleteMapping("/all")
    public ResponseEntity<String> deleteAllSensorReadings() {
        log.info("Deleting all sensor reading");
        deviceSensorReadingUseCase.deleteAll();
        return ResponseEntity.ok("All SensorReadings deleted successfully");
    }

    @PostMapping("/saveDHT22")
    public ResponseEntity<String> saveDHT22() {
        log.info("Creating DHT22 sensor reading test data");
        deviceSensorReadingUseCase.saveList(DHT22DataGenerator.get());
        return ResponseEntity.ok("DHT22 Sensor Readings saved successfully");
    }

    @PostMapping("/saveMethane")
    public ResponseEntity<String> saveMethaneSensorReadingTest() {
        log.info("Creating Methane sensor reading test data");
        deviceSensorReadingUseCase.saveList(MethaneDataGenerator.get());
        return ResponseEntity.ok("Methane Sensor Readings saved successfully");
    }

}

