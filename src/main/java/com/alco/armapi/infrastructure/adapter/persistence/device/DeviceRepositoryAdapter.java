package com.alco.armapi.infrastructure.adapter.persistence.device;

import com.alco.armapi.application.port.out.DeviceRepositoryPort;
import com.alco.armapi.infrastructure.mapper.DeviceMapper;
import com.alco.armapi.infrastructure.adapter.persistence.zone.ZoneEntity;
import com.alco.armapi.infrastructure.adapter.persistence.zone.ZoneRepository;
import com.alco.armapi.common.PersistenceAdapter;
import com.alco.armapi.domain.model.Device;
import java.util.List;
import lombok.RequiredArgsConstructor;
import java.util.stream.Collectors;
import java.util.UUID;

@PersistenceAdapter
@RequiredArgsConstructor
public class DeviceRepositoryAdapter implements DeviceRepositoryPort{
    private final DeviceRepository deviceRepository;
    private final ZoneRepository zoneRepository;

    @Override
    public List<Device> listDevices() {
        List<DeviceEntity> deviceEntities = deviceRepository.findAll();
        return deviceEntities.stream()
                .map(DeviceMapper.INSTANCE::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public Device getDeviceById(UUID deviceId) {
        
        DeviceEntity deviceEntity = deviceRepository.findById(deviceId)
                .orElseThrow(() -> new RuntimeException("Device not found with id: " + deviceId));
        return DeviceMapper.INSTANCE.toDomainModel(deviceEntity);
    }

    @Override
    public Device saveDevice(Device device) {
        DeviceEntity deviceEntity = DeviceMapper.INSTANCE.toEntity(device);
        DeviceEntity savedEntity = deviceRepository.save(deviceEntity);
        return DeviceMapper.INSTANCE.toDomainModel(savedEntity);
    }

    @Override
    public void deleteDevice(UUID id) {        
        deviceRepository.deleteById(id);
    }

    @Override
    public Device updateDevice(UUID id, Device device) {
        
        DeviceEntity existingDeviceEntity = deviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Device not found with id: " + id));

        existingDeviceEntity.setName(device.getName());
        existingDeviceEntity.setBatchNo(device.getBatchNo());
        existingDeviceEntity.setDescription(device.getDescription());
        // existingDeviceEntity.setType(device.getType());
        // existingDeviceEntity.setLocation(device.getLocation());
        // existingDeviceEntity.setLatitude(device.getLatitude());
        // existingDeviceEntity.setLongitude(device.getLongitude());
        // existingDeviceEntity.setStatus(device.getStatus());

        // if (device.getZoneId() != null) {
        //     //UUID zone_id = device.getZone(zoneId);
        //     ZoneEntity zoneEntity = zoneRepository.findById(device.getZoneId())
        //             .orElseThrow(() -> new RuntimeException("Zone not found with id: " + device.getZoneId()));
        //     existingDeviceEntity.setZone(zoneEntity);
        // }

        DeviceEntity updatedDeviceEntity = deviceRepository.save(existingDeviceEntity);
        return DeviceMapper.INSTANCE.toDomainModel(updatedDeviceEntity);
    }

    // @Override
    // public List<Device> findDevicesByStatus(String status) {
    //     List<DeviceEntity> deviceEntities = deviceRepository.findByStatus(status);
    //     return deviceEntities.stream()
    //             .map(DeviceMapper.INSTANCE::toDomainModel)
    //             .collect(Collectors.toList());
    // }

    // @Override
    // public List<Device> findDevicesByLocation(String location) {
    //     List<DeviceEntity> deviceEntities = deviceRepository.findByLocation(location);
    //     return deviceEntities.stream()
    //             .map(DeviceMapper.INSTANCE::toDomainModel)
    //             .collect(Collectors.toList());
    // }

    // @Override
    // public List<Device> findDevicesByType(String type) {
    //     List<DeviceEntity> deviceEntities = deviceRepository.findByType(type);
    //     return deviceEntities.stream()
    //             .map(DeviceMapper.INSTANCE::toDomainModel)
    //             .collect(Collectors.toList());
    // }

    // @Override
    // public List<Device> findDevicesByZoneId(UUID zoneId) {
    //     List<DeviceEntity> deviceEntities = deviceRepository.findByZoneId(zoneId);
    //     return deviceEntities.stream()
    //             .map(DeviceMapper.INSTANCE::toDomainModel)
    //             .collect(Collectors.toList());
    // }
}