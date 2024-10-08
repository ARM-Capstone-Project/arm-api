package com.alco.armapi.application.port.out;

import com.alco.armapi.domain.model.SensorReading;

import java.time.LocalDateTime;
import java.util.List;

public interface SensorReadingRepositoryPort {
    
    SensorReading getSensorReadingById(String id);
    
    List<SensorReading> listAllSensorReadings();

    List<SensorReading> findAllSensorReadingsInPeriod(LocalDateTime start, LocalDateTime end);
    
    List<SensorReading> findSensorReadingsForPeriod(String sensorId, LocalDateTime start, LocalDateTime end);
    
    List<SensorReading> findReadingsBySensorId(String sensorId);
    
    List<SensorReading> findReadingsByDeviceId(String deviceId);
    
    List<SensorReading> findReadingsForDeviceInPeriod(String deviceId, LocalDateTime start, LocalDateTime end);
}
