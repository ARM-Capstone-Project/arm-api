package com.alco.armapi.infrastructure.adapter.persistence.sensorreading;

import com.alco.armapi.application.port.out.SensorReadingRepositoryPort;
import com.alco.armapi.domain.model.SensorReading;
import com.alco.armapi.infrastructure.mapper.SensorReadingMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SensorReadingRepositoryAdapter implements SensorReadingRepositoryPort {
    private final SensorReadingRepository sensorReadingRepository;

    @Override
    public SensorReading getSensorReadingById(String id) {
        UUID uuid = UUID.fromString(id);
        return sensorReadingRepository.findById(uuid)
                .map(SensorReadingMapper.INSTANCE::toDomainModel)
                .orElse(null); // return Null
    }

    @Override
    public List<SensorReading> listAllSensorReadings() {
        List<SensorReadingEntity> entities = sensorReadingRepository.findAll();
        return entities.stream()
                .map(SensorReadingMapper.INSTANCE::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<SensorReading> findAllSensorReadingsInPeriod(LocalDateTime start, LocalDateTime end) {
        List<SensorReadingEntity> entities = sensorReadingRepository.findAllSensorReadingsInPeriod(start, end);
        return entities.stream()
                .map(SensorReadingMapper.INSTANCE::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<SensorReading> findSensorReadingsForPeriod(String sensorId, LocalDateTime start, LocalDateTime end) {
        UUID uuid = UUID.fromString(sensorId);
        List<SensorReadingEntity> entities = sensorReadingRepository.findBySensorIdAndTimestampBetween(uuid, start, end);
        return entities.stream()
                .map(SensorReadingMapper.INSTANCE::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<SensorReading> findReadingsBySensorId(String sensorId) {
        UUID uuid = UUID.fromString(sensorId);
        List<SensorReadingEntity> entities = sensorReadingRepository.findBySensorId(uuid);
        return entities.stream()
                .map(SensorReadingMapper.INSTANCE::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<SensorReading> findReadingsByDeviceId(String deviceId) {
        UUID uuid = UUID.fromString(deviceId);
        List<SensorReadingEntity> entities = sensorReadingRepository.findByDeviceId(uuid);
        return entities.stream()
                .map(SensorReadingMapper.INSTANCE::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<SensorReading> findReadingsForDeviceInPeriod(String deviceId, LocalDateTime start, LocalDateTime end) {
        UUID uuid = UUID.fromString(deviceId);
        List<SensorReadingEntity> entities = sensorReadingRepository.findByDeviceIdAndTimestampBetween(uuid, start, end);
        return entities.stream()
                .map(SensorReadingMapper.INSTANCE::toDomainModel)
                .collect(Collectors.toList());
    }
}