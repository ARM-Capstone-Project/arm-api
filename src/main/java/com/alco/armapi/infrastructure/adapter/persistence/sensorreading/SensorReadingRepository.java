package com.alco.armapi.infrastructure.adapter.persistence.sensorreading;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface SensorReadingRepository extends JpaRepository<SensorReadingEntity, UUID> {

    // Get all readings by a specific sensor
    List<SensorReadingEntity> findBySensorId(UUID sensorId);

    // Get all readings by a specific device
    List<SensorReadingEntity> findByDeviceId(UUID deviceId);

    // Get all readings between two timestamps (time series data)
    List<SensorReadingEntity> findAllSensorReadingsInPeriod(LocalDateTime start, LocalDateTime end);

    // Get all readings for a specific sensor between two timestamps (time series data)
    List<SensorReadingEntity> findBySensorIdAndTimestampBetween(UUID sensorId, LocalDateTime start, LocalDateTime end);

    // Get all readings for a device between two timestamps
    List<SensorReadingEntity> findByDeviceIdAndTimestampBetween(UUID deviceId, LocalDateTime start, LocalDateTime end);
}