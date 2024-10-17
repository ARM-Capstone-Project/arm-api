package com.alco.armapi.infrastructure.adapter.persistence.sensor;

import com.alco.armapi.common.PersistenceAdapter;
import com.alco.armapi.domain.model.Sensor;
import com.alco.armapi.infrastructure.adapter.persistence.device.DeviceEntity;
import com.alco.armapi.infrastructure.adapter.persistence.device.DeviceRepository;
import com.alco.armapi.infrastructure.mapper.SensorMapper;
import lombok.RequiredArgsConstructor;
import com.alco.armapi.application.port.out.SensorRepositoryPort;
import java.util.List;
import java.util.stream.Collectors;
import java.util.UUID;

@PersistenceAdapter
@RequiredArgsConstructor
public class SensorRepositoryAdapter implements SensorRepositoryPort{
    private final SensorRepository sensorRepository;  // Spring Data JPA repository
    private final DeviceRepository deviceRepository; //sub attribute

    @Override
    public List<Sensor> listSensors() {
        List<SensorEntity> sensorEntities = sensorRepository.findAll();
        return sensorEntities.stream()
                .map(SensorMapper.INSTANCE::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public Sensor getSensorById(UUID sensorId) {
        SensorEntity sensorEntity = sensorRepository.findById(sensorId)
                .orElseThrow(() -> new RuntimeException("Sensor not found with id: " + sensorId));
        return SensorMapper.INSTANCE.toDomainModel(sensorEntity);
    }

    @Override
    public Sensor saveSensor(Sensor sensor) {
        SensorEntity sensorEntity = SensorMapper.INSTANCE.toEntity(sensor);
        SensorEntity savedEntity = sensorRepository.save(sensorEntity);
        return SensorMapper.INSTANCE.toDomainModel(savedEntity);
    }

    @Override
    public void deleteSensor(UUID sensorId) {
        sensorRepository.deleteById(sensorId);
    }

    @Override
    public Sensor updateSensor(UUID sensorId, Sensor sensor) {
        SensorEntity existingEntity = sensorRepository.findById(sensorId)
                .orElseThrow(() -> new RuntimeException("Sensor not found with id: " + sensorId));
        
        // set fields
        existingEntity.setName(sensor.getName());
        existingEntity.setType(sensor.getType());
        existingEntity.setStatus(sensor.getStatus());
        existingEntity.setUnit(sensor.getUnit());

        if (sensor.getDevice() != null) {
            UUID uuid = sensor.getDevice().getId();
            DeviceEntity deviceEntity = deviceRepository.findById(uuid)
                    .orElseThrow(() -> new RuntimeException("Device not found with id: " + sensor.getDevice().getId()));
                    existingEntity.setDevice(deviceEntity);
        }
        
        SensorEntity updatedEntity = sensorRepository.save(existingEntity);
        return SensorMapper.INSTANCE.toDomainModel(updatedEntity);
    }

    @Override
    public List<Sensor> findSensorsByDeviceId(UUID deviceId) {
        List<SensorEntity> sensorEntities = sensorRepository.findByDeviceId(deviceId);
        return sensorEntities.stream()
                .map(SensorMapper.INSTANCE::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<Sensor> findSensorsByStatus(String status) {
        List<SensorEntity> sensorEntities = sensorRepository.findByStatus(status);
        return sensorEntities.stream()
                .map(SensorMapper.INSTANCE::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<Sensor> findSensorsByType(String type) {
        List<SensorEntity> sensorEntities = sensorRepository.findByType(type);
        return sensorEntities.stream()
                .map(SensorMapper.INSTANCE::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<Sensor> findSensorsByName(String name) {
        List<SensorEntity> sensorEntities = sensorRepository.findByName(name);
        return sensorEntities.stream()
        .map(SensorMapper.INSTANCE::toDomainModel)
        .collect(Collectors.toList());
    }
}
