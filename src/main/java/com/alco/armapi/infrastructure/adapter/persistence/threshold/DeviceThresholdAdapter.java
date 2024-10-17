package com.alco.armapi.infrastructure.adapter.persistence.threshold;

import com.alco.armapi.application.port.out.DeviceThresholdRepositoryPort;
import com.alco.armapi.common.PersistenceAdapter;
import com.alco.armapi.domain.model.DeviceThreshold;
import com.alco.armapi.infrastructure.mapper.DeviceThresholdMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
public class DeviceThresholdAdapter implements DeviceThresholdRepositoryPort {

    private final DeviceThresholdRepository deviceThresholdRepository;

    @Override
    public void save(DeviceThreshold deviceThreshold) {
        deviceThresholdRepository.save(DeviceThresholdMapper.INSTANCE.toEntity(deviceThreshold));
    }

    @Override
    public List<DeviceThreshold> findAll() {
        return deviceThresholdRepository.findAll().stream()
                .map(DeviceThresholdMapper.INSTANCE::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(DeviceThreshold deviceThreshold) {
        deviceThresholdRepository.delete(DeviceThresholdMapper.INSTANCE.toEntity(deviceThreshold));
    }

    @Override
    public void deleteAll() {
        deviceThresholdRepository.deleteAll();
    }

    @Override
    public List<DeviceThreshold> findByDeviceId(String deviceId) {
        return deviceThresholdRepository.findByDeviceId(deviceId).stream()
                .map(DeviceThresholdMapper.INSTANCE::toDomainModel)
                .collect(Collectors.toList());
    }
}
