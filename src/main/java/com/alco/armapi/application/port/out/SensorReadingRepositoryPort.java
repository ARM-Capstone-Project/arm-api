package com.alco.armapi.application.port.out;

import com.alco.armapi.domain.model.SensorReading;

import java.time.Instant;
import java.util.List;

public interface SensorReadingRepositoryPort {
    
    SensorReading getSensorReadingById(String id);
    
    List<SensorReading> listAllSensorReadings();
    
    List<SensorReading> findSensorReadingsForPeriod(String sensorId, Instant start, Instant end);
    
    List<SensorReading> findReadingsBySensorId(String sensorId);
    
    List<SensorReading> findReadingsByDeviceId(String deviceId);
    
    List<SensorReading> findReadingsForDeviceInPeriod(String deviceId, Instant start, Instant end);
}
