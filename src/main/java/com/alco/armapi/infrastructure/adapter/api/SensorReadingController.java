package com.alco.armapi.infrastructure.adapter.api;

import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.alco.armapi.application.port.in.SensorReadingUseCase;
import com.alco.armapi.domain.model.SensorReading;
import java.util.List;
import java.time.LocalDateTime;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/reading")
public class SensorReadingController {
    
    @Autowired
    private SensorReadingUseCase sensorReadingUseCase;

    @GetMapping("/{sensorId}")
    public ResponseEntity<SensorReading> getSensorReadingById(@PathVariable String sensorId) {
        SensorReading sensorReading = sensorReadingUseCase.getSensorReadingById(sensorId);
        return ResponseEntity.ok(sensorReading);
    }

    @GetMapping("/{sensorId}/period")
    public ResponseEntity<List<SensorReading>> getSensorReadingsForPeriod(
        @PathVariable String sensorId,
        @RequestParam("start") String startDate,
        @RequestParam("end") String endDate
    ) {        
        LocalDateTime start = LocalDateTime.parse(startDate);  // Parse date from query param
        LocalDateTime end = LocalDateTime.parse(endDate);      // Parse date from query param
        List<SensorReading> readings = sensorReadingUseCase.getSensorReadingsForPeriod(sensorId, start, end);
        return ResponseEntity.ok(readings);
    }

    //to use with period
    @GetMapping("/{sensorId}/all")
    public ResponseEntity<List<SensorReading>> getReadingsBySensorId(@PathVariable String sensorId) {
        List<SensorReading> sensorReadings = sensorReadingUseCase.getReadingsBySensorId(sensorId);
        return ResponseEntity.ok(sensorReadings);
    }

    @GetMapping("/device/{deviceId}/all")
    public ResponseEntity<List<SensorReading>> getReadingsByDeviceId(@PathVariable String deviceId) {
        List<SensorReading> sensorReadings = sensorReadingUseCase.getReadingsByDeviceId(deviceId);
        return ResponseEntity.ok(sensorReadings);
    }

    @GetMapping("/device/{deviceId}/period")
    public ResponseEntity<List<SensorReading>> getReadingsForDeviceInPeriod(
            @PathVariable String deviceId,
            @RequestParam("start") String startDate,
            @RequestParam("end") String endDate) {
        
        LocalDateTime start = LocalDateTime.parse(startDate);
        LocalDateTime end = LocalDateTime.parse(endDate);
        List<SensorReading> readings = sensorReadingUseCase.getReadingsForDeviceInPeriod(deviceId, start, end);
        return ResponseEntity.ok(readings);
    }

    //recent 1 month all readings
    @GetMapping("/recent")
    public ResponseEntity<List<SensorReading>> getAllSensorReadingsInPeriod() {
        List<SensorReading> sensorReadings = sensorReadingUseCase.getAllSensorReadingsInPeriod();
        return ResponseEntity.ok(sensorReadings);
    }
}