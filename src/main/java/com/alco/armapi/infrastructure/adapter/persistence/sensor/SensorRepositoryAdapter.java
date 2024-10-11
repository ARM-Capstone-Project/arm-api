package com.alco.armapi.infrastructure.adapter.persistence.sensor;

import com.alco.armapi.application.port.out.SensorRepositoryPort;
import com.alco.armapi.domain.model.Sensor;
import com.alco.armapi.infrastructure.adapter.persistence.device.DeviceEntity;
import com.alco.armapi.infrastructure.adapter.persistence.device.DeviceRepository;
import com.alco.armapi.infrastructure.mapper.SensorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class SensorRepositoryAdapter implements SensorRepositoryPort {

    private final SensorRepository sensorRepository;  // Spring Data JPA repository
    private final SensorMapper sensorMapper;  // to convert between entities and domain models
    private final DeviceRepository deviceRepository; //sub attribute

    @Override
    public List<Sensor> listSensors() {
        List<SensorEntity> sensorEntities = sensorRepository.findAll();
        return sensorEntities.stream()
                .map(sensorMapper::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public Sensor getSensorById(String sensorId) {
        SensorEntity sensorEntity = sensorRepository.findById(UUID.fromString(sensorId))
                .orElseThrow(() -> new RuntimeException("Sensor not found with id: " + sensorId));
        return sensorMapper.toDomainModel(sensorEntity);
    }

    @Override
    public Sensor saveSensor(Sensor sensor) {
        SensorEntity sensorEntity = sensorMapper.toEntity(sensor);
        SensorEntity savedEntity = sensorRepository.save(sensorEntity);
        return sensorMapper.toDomainModel(savedEntity);
    }

    @Override
    public void deleteSensor(String sensorId) {
        sensorRepository.deleteById(UUID.fromString(sensorId));
    }

    @Override
    public Sensor updateSensor(String sensorId, Sensor sensor) {
        SensorEntity existingEntity = sensorRepository.findById(UUID.fromString(sensorId))
                .orElseThrow(() -> new RuntimeException("Sensor not found with id: " + sensorId));
        
        // set fields
        existingEntity.setName(sensor.getName());
        existingEntity.setType(sensor.getType());
        existingEntity.setStatus(sensor.getStatus());
        existingEntity.setUnit(sensor.getUnit());

        if (sensor.getDeviceId() != null) {
            DeviceEntity deviceEntity = deviceRepository.findById(sensor.getDeviceId())
                    .orElseThrow(() -> new RuntimeException("Device not found with id: " + sensor.getDeviceId()));
                    existingEntity.setDevice(deviceEntity);
        }
        
        SensorEntity updatedEntity = sensorRepository.save(existingEntity);
        return sensorMapper.toDomainModel(updatedEntity);
    }

    @Override
    public List<Sensor> findSensorsByDeviceId(String deviceId) {
        List<SensorEntity> sensorEntities = sensorRepository.findByDeviceId(UUID.fromString(deviceId));
        return sensorEntities.stream()
                .map(sensorMapper::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<Sensor> findSensorsByStatus(String status) {
        List<SensorEntity> sensorEntities = sensorRepository.findByStatus(status);
        return sensorEntities.stream()
                .map(sensorMapper::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<Sensor> findSensorsByType(String type) {
        List<SensorEntity> sensorEntities = sensorRepository.findByType(type);
        return sensorEntities.stream()
                .map(sensorMapper::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<Sensor> findSensorsByName(String name) {
        List<SensorEntity> sensorEntities = sensorRepository.findByName(name);
        return sensorEntities.stream()
        .map(sensorMapper::toDomainModel)
        .collect(Collectors.toList());
    }
}