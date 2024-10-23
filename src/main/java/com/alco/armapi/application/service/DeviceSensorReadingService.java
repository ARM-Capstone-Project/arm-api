package com.alco.armapi.application.service;

import com.alco.armapi.application.port.in.DeviceSensorReadingUseCase;
import com.alco.armapi.application.port.out.DeviceSensorReadingRepositoryPort;

import com.alco.armapi.common.UseCase;
import com.alco.armapi.domain.model.readings.DeviceSensorReading;
import com.alco.armapi.domain.model.readings.ReadingDevice;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@Transactional
@RequiredArgsConstructor
public class DeviceSensorReadingService implements DeviceSensorReadingUseCase {

    private final DeviceSensorReadingRepositoryPort deviceSensorReadingRepositoryPort;


    @Override
    public void save(DeviceSensorReading deviceSensorReading) {
        deviceSensorReadingRepositoryPort.save(deviceSensorReading);
    }

    @Override
    public List<DeviceSensorReading> findAll() {
       return deviceSensorReadingRepositoryPort.findAll();
    }

    @Override
    public void delete(DeviceSensorReading deviceSensorReading) {
        deviceSensorReadingRepositoryPort.delete(deviceSensorReading);
    }

    @Override
    public void deleteAll() {
        deviceSensorReadingRepositoryPort.deleteAll();
    }

    @Override
    public List<DeviceSensorReading> findByDeviceId(String deviceId) {
        return deviceSensorReadingRepositoryPort.findByDeviceId(deviceId);
    }

    @Override
    public void saveList(List<DeviceSensorReading> deviceSensorReadings) {
         deviceSensorReadingRepositoryPort.saveList(deviceSensorReadings);
    }

    @Override
    public List<ReadingDevice> getDeviceFromReading(){
        return deviceSensorReadingRepositoryPort.getDeviceFromReading();
    };
}
