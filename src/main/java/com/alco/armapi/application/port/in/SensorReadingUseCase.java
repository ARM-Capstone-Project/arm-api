package com.alco.armapi.application.port.in;

import com.alco.armapi.domain.model.SensorReading;
import java.time.Instant;
import java.util.List;

public interface SensorReadingUseCase {

    SensorReading getSensorReadingById(String id); //for 1 reading
    
    List<SensorReading> getAllSensorReadings();
    
    List<SensorReading> getSensorReadingsForPeriod(String sensorId, Instant start, Instant end); // Get all sensor readings by a specific sensor

    List<SensorReading> getReadingsBySensorId(String sensorId);  // Get all readings by a specific sensor

    List<SensorReading> getReadingsByDeviceId(String deviceId);  // Get all readings by a specific device

    List<SensorReading> getReadingsForDeviceInPeriod(String deviceId, Instant start, Instant end);  // Get all readings for a device between two timestamps
}