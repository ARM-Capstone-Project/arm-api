package com.alco.armapi.application.service;

import com.alco.armapi.application.port.in.SensorReadingUseCase;
import com.alco.armapi.application.port.out.SensorReadingRepositoryPort;
import com.alco.armapi.common.UseCase;
import com.alco.armapi.domain.model.SensorReading;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.time.LocalDateTime;

@UseCase
@Transactional
@RequiredArgsConstructor
public class SensorReadingService implements SensorReadingUseCase{
    private final SensorReadingRepositoryPort sensorReadingRepositoryPort;
    @Override
    public SensorReading getSensorReadingById(String id){
        return sensorReadingRepositoryPort.getSensorReadingById(id);
    }
    
    @Override
    public List<SensorReading> getAllSensorReadings(){
        return sensorReadingRepositoryPort.listAllSensorReadings();
    };

    @Override
    //Due to large dataset, limit to 1 month as maximum time period
    public List<SensorReading> getAllSensorReadingsInPeriod(){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime oneMonthAgo = now.minusMonths(1);
        return sensorReadingRepositoryPort.findAllSensorReadingsInPeriod(oneMonthAgo, now);
    };
    
    @Override
    // Get all readings for a sensor between two timestamps
    public List<SensorReading> getSensorReadingsForPeriod(String sensorId, LocalDateTime start, LocalDateTime end){
        return sensorReadingRepositoryPort.findSensorReadingsForPeriod(sensorId, start, end);
    };

    @Override
    // Get all readings by a specific sensor
    public List<SensorReading> getReadingsBySensorId(String sensorId){
        return sensorReadingRepositoryPort.findReadingsBySensorId(sensorId);
    };

    @Override
    // Get all readings by a specific device
    public List<SensorReading> getReadingsByDeviceId(String deviceId){
        return sensorReadingRepositoryPort.findReadingsByDeviceId(deviceId);
    };

    @Override
    // Get all readings for a device between two timestamps
    public List<SensorReading> getReadingsForDeviceInPeriod(String deviceId, LocalDateTime start, LocalDateTime end){
        return sensorReadingRepositoryPort.findReadingsForDeviceInPeriod(deviceId, start, end);
    };
}
