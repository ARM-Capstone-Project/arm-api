package com.alco.armapi.application.service;

import com.alco.armapi.application.port.in.SensorReadingUseCase;
import com.alco.armapi.application.port.out.SensorReadingRepositoryPort;
import com.alco.armapi.common.UseCase;
import com.alco.armapi.domain.model.SensorReading;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.UUID;
import java.time.LocalDateTime;

@UseCase
@Transactional
@RequiredArgsConstructor
public class SensorReadingService implements SensorReadingUseCase{
    private final SensorReadingRepositoryPort sensorReadingRepositoryPort;
    @Override
    public SensorReading getSensorReadingById(UUID id){
        return sensorReadingRepositoryPort.getSensorReadingById(id);
    }
    
    @Override
    public List<SensorReading> getAllSensorReadings(){
        return sensorReadingRepositoryPort.listAllSensorReadings();
    };

    @Override
    public List<SensorReading> getAllSensorReadingsInPeriod(LocalDateTime start, LocalDateTime end){
        return sensorReadingRepositoryPort.findAllSensorReadingsInPeriod(start, end);
    };
    
    @Override
    // Get all readings for a sensor between two timestamps
    public List<SensorReading> getSensorReadingsForPeriod(UUID sensorId, LocalDateTime start, LocalDateTime end){
        return sensorReadingRepositoryPort.findSensorReadingsForPeriod(sensorId, start, end);
    };

    @Override
    // Get all readings by a specific sensor for 1 month
    public List<SensorReading> getReadingsBySensorId(UUID sensorId){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime oneMonthAgo = now.minusMonths(1);
        return sensorReadingRepositoryPort.findSensorReadingsForPeriod(sensorId, oneMonthAgo, now);
    };
}