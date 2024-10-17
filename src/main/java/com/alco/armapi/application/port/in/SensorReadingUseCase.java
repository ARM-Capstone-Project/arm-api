package com.alco.armapi.application.port.in;

import com.alco.armapi.domain.model.SensorReading;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface SensorReadingUseCase {

    SensorReading getSensorReadingById(UUID id); //for 1 reading
    
    List<SensorReading> getAllSensorReadings();

    List<SensorReading> getAllSensorReadingsInPeriod(LocalDateTime start, LocalDateTime end);
    
    List<SensorReading> getSensorReadingsForPeriod(UUID sensorId, LocalDateTime start, LocalDateTime end); // Get all sensor readings by a specific sensor

    List<SensorReading> getReadingsBySensorId(UUID sensorId);  // Get all readings by a specific sensor
}