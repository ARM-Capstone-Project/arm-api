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
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/reading")
public class SensorReadingController {
    
    @Autowired
    private SensorReadingUseCase sensorReadingUseCase;

    @GetMapping("/{sensorId}")
    public ResponseEntity<SensorReading> getSensorReadingById(@PathVariable UUID sensorId) {
        SensorReading sensorReading = sensorReadingUseCase.getSensorReadingById(sensorId);
        return ResponseEntity.ok(sensorReading);
    }

    @GetMapping("/{sensorId}/period")
    public ResponseEntity<List<SensorReading>> getSensorReadingsForPeriod(
        @PathVariable UUID sensorId,
        @RequestParam("start") String startDate,
        @RequestParam("end") String endDate
    ) {        
        LocalDateTime start = LocalDateTime.parse(startDate);  // Parse date from query param
        LocalDateTime end = LocalDateTime.parse(endDate);      // Parse date from query param
        List<SensorReading> readings = sensorReadingUseCase.getSensorReadingsForPeriod(sensorId, start, end);
        return ResponseEntity.ok(readings);
    }

    //to use with period, by default set for 1 month data
    @GetMapping("/{sensorId}/all")
    public ResponseEntity<List<SensorReading>> getReadingsBySensorId(@PathVariable UUID sensorId) {
        List<SensorReading> sensorReadings = sensorReadingUseCase.getReadingsBySensorId(sensorId);
        return ResponseEntity.ok(sensorReadings);
    }

    //recent 24hours readings
    @GetMapping("/recent")
    public ResponseEntity<List<SensorReading>> getAllSensorReadingsInPeriod() {
        LocalDateTime end = LocalDateTime.now();
        LocalDateTime start = end.minusHours(24);
        List<SensorReading> sensorReadings = sensorReadingUseCase.getAllSensorReadingsInPeriod(start, end);
        return ResponseEntity.ok(sensorReadings);
    }
}