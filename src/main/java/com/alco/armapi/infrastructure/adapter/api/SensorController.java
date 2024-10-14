package com.alco.armapi.infrastructure.adapter.api;

import com.alco.armapi.application.port.in.SensorUseCase;
import com.alco.armapi.domain.model.Sensor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/sensors")
public class SensorController {

    @Autowired
    private SensorUseCase sensorUseCase;

    @PostMapping
    //create
    public ResponseEntity<Sensor> createSensor(@RequestBody Sensor sensor) {
        Sensor savedSensor = sensorUseCase.saveSensor(sensor);
        return ResponseEntity.ok(savedSensor);
    }

    @DeleteMapping("/{id}")
    //delete
    public ResponseEntity<Void> deleteSensor(@PathVariable UUID id) {
        sensorUseCase.deleteSensor(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    //update
    public ResponseEntity<Sensor> updateSensor(@PathVariable UUID id, @RequestBody Sensor sensor) {
        Sensor updatedSensor = sensorUseCase.updateSensor(id, sensor);
        return ResponseEntity.ok(updatedSensor);
    }

    //get methods
    @GetMapping("/{id}")
    public ResponseEntity<Sensor> getSensorById(@PathVariable UUID id) {
        Sensor sensor = sensorUseCase.getSensorById(id);
        return ResponseEntity.ok(sensor);
    }

    @GetMapping
    public ResponseEntity<List<Sensor>> getAllSensors() {
        List<Sensor> sensors = sensorUseCase.getAllSensors();
        return ResponseEntity.ok(sensors);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Sensor>> getSensorsByStatus(@PathVariable String status) {
        List<Sensor> sensors = sensorUseCase.getSensorByStatus(status);
        return ResponseEntity.ok(sensors);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<Sensor>> getSensorsByType(@PathVariable String type) {
        List<Sensor> sensors = sensorUseCase.getSensorByType(type);
        return ResponseEntity.ok(sensors);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Sensor>> getSensorsByName(@PathVariable String name) {
        List<Sensor> sensors = sensorUseCase.getSensorByName(name);
        return ResponseEntity.ok(sensors);
    }
}