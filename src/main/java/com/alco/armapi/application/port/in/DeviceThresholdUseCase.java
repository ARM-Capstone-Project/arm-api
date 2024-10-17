package com.alco.armapi.application.port.in;

import com.alco.armapi.common.UseCase;
import com.alco.armapi.domain.model.DeviceThreshold;

import java.util.List;

@UseCase
public interface DeviceThresholdUseCase {
    void save(DeviceThreshold deviceThreshold);
    List<DeviceThreshold> findAll();
    void delete(DeviceThreshold deviceThreshold);
    void deleteAll();
    List<DeviceThreshold> findByDeviceId(String deviceId);
}
