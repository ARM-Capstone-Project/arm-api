package com.alco.armapi.application.service;

import com.alco.armapi.application.port.in.DeviceThresholdUseCase;
import com.alco.armapi.application.port.out.DeviceThresholdRepositoryPort;
import com.alco.armapi.common.UseCase;
import com.alco.armapi.domain.model.DeviceThreshold;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@Transactional
@RequiredArgsConstructor
public class DeviceThresholdService implements DeviceThresholdUseCase {

    private final DeviceThresholdRepositoryPort deviceThresholdRepository;

    @Override
    public void save(DeviceThreshold deviceThreshold) {
        deviceThresholdRepository.save(deviceThreshold);
    }

    @Override
    public List<DeviceThreshold> findAll() {
        return deviceThresholdRepository.findAll();
    }

    @Override
    public void delete(DeviceThreshold deviceThreshold) {
        deviceThresholdRepository.delete(deviceThreshold);
    }

    @Override
    public void deleteAll() {
        deviceThresholdRepository.deleteAll();
    }

    @Override
    public List<DeviceThreshold> findByDeviceId(String deviceId) {
        return deviceThresholdRepository.findByDeviceId(deviceId);
    }
}
