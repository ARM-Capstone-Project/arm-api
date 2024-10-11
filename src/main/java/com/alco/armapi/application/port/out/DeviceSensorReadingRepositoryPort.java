package com.alco.armapi.application.port.out;

import com.alco.armapi.domain.model.readings.DeviceSensorReading;
import java.util.List;

public interface DeviceSensorReadingRepositoryPort {
    void save(DeviceSensorReading deviceSensorReading);
    List<DeviceSensorReading> findAll();
    void delete(DeviceSensorReading deviceSensorReading);
    void deleteAll();
    List<DeviceSensorReading> findByDeviceId(String deviceId);
    void saveList(List<DeviceSensorReading> deviceSensorReadings);
}
