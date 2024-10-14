package com.alco.armapi.infrastructure.adapter.persistence.sensorreading;

import java.util.List;
import java.util.UUID;
import com.alco.armapi.application.port.out.SensorReadingRepositoryPort;
import com.alco.armapi.common.PersistenceAdapter;
import com.alco.armapi.domain.model.SensorReading;
import com.alco.armapi.infrastructure.mapper.SensorReadingMapper;
import lombok.RequiredArgsConstructor;
import java.util.stream.Collectors;
import java.time.LocalDateTime;

@PersistenceAdapter
@RequiredArgsConstructor
public class SensorReadingRepositoryAdapter implements SensorReadingRepositoryPort{
    private final SensorReadingRepository sensorReadingRepository;

    @Override
    public SensorReading getSensorReadingById(UUID id) {
        //UUID uuid = UUID.fromString(id);
        return sensorReadingRepository.findById(id)
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
    public List<SensorReading> findSensorReadingsForPeriod(UUID sensorId, LocalDateTime start, LocalDateTime end) {
        //UUID uuid = UUID.fromString(sensorId);
        List<SensorReadingEntity> entities = sensorReadingRepository.findBySensorIdAndTimestampBetween(sensorId, start, end);
        return entities.stream()
                .map(SensorReadingMapper.INSTANCE::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<SensorReading> findReadingsBySensorId(UUID sensorId) {
        //UUID uuid = UUID.fromString(sensorId);
        List<SensorReadingEntity> entities = sensorReadingRepository.findBySensorId(sensorId);
        return entities.stream()
                .map(SensorReadingMapper.INSTANCE::toDomainModel)
                .collect(Collectors.toList());
    }
}
