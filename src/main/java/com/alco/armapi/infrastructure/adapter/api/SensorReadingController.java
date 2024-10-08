package com.alco.armapi.infrastructure.adapter.api;

import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alco.armapi.application.port.in.SensorReadingUseCase;
import com.alco.armapi.domain.model.SensorReading;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/reading")
public class SensorReadingController {
    
    @Autowired
    private SensorReadingUseCase sensorReadingUseCase;

    @GetMapping("/{id}")
    public ResponseEntity<SensorReading> getSensorReadingById(String id) {
        SensorReading sensorReading = sensorReadingUseCase.getSensorReadingById(id);
        return ResponseEntity.ok(sensorReading);
    }


}