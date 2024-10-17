package com.alco.armapi.infrastructure.adapter.persistence.sensorreading;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface SensorReadingRepository extends JpaRepository<SensorReadingEntity, UUID> {
    // Get all readings by a specific sensor
    List<SensorReadingEntity> findBySensorId(UUID sensorId);

    // Get all readings between two timestamps (time series data)
    @Query("SELECT s FROM SensorReadingEntity s WHERE s.timestamp BETWEEN :start AND :end")
    List<SensorReadingEntity> findAllSensorReadingsInPeriod(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    // Get all readings for a specific sensor between two timestamps (time series data)
    @Query("SELECT s FROM SensorReadingEntity s WHERE s.sensor.id = :sensorId AND s.timestamp BETWEEN :start AND :end")
    List<SensorReadingEntity> findBySensorIdAndTimestampBetween(@Param("sensorId") UUID sensorId, @Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
}