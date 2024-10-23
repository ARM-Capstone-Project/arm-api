package com.alco.armapi.application.port.in;

import com.alco.armapi.common.UseCase;
import com.alco.armapi.domain.model.readings.DeviceSensorReading;
import com.alco.armapi.domain.model.readings.ReadingDevice;

import java.util.List;

@UseCase
public interface DeviceSensorReadingUseCase {
    void save(DeviceSensorReading deviceSensorReading);
    List<DeviceSensorReading> findAll();
    void delete(DeviceSensorReading deviceThreshold);
    void deleteAll();
    List<DeviceSensorReading> findByDeviceId(String deviceId);
    void saveList(List<DeviceSensorReading> deviceSensorReadings);
    
    List<ReadingDevice> getDeviceFromReading();
}
