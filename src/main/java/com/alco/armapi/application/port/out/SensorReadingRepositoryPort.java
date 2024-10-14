package com.alco.armapi.application.port.out;

import com.alco.armapi.domain.model.SensorReading;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface SensorReadingRepositoryPort {
    
    SensorReading getSensorReadingById(UUID id);
    
    List<SensorReading> listAllSensorReadings();

    List<SensorReading> findAllSensorReadingsInPeriod(LocalDateTime start, LocalDateTime end);
    
    List<SensorReading> findSensorReadingsForPeriod(UUID sensorId, LocalDateTime start, LocalDateTime end);
    
    List<SensorReading> findReadingsBySensorId(UUID sensorId);
}