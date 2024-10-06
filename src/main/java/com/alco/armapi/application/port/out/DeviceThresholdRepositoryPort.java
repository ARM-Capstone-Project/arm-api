package com.alco.armapi.application.port.out;

import com.alco.armapi.domain.model.DeviceThreshold;

import java.util.List;

public interface DeviceThresholdRepositoryPort {
    void save(DeviceThreshold deviceThreshold);
    List<DeviceThreshold> findAll();
    void delete(DeviceThreshold deviceThreshold);
    void deleteAll();
    List<DeviceThreshold> findByDeviceId(String deviceId);
}
