package com.alco.armapi.infrastructure.adapter.persistence.sensor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface SensorRepository extends JpaRepository<SensorEntity, UUID> {

    // Find all sensors by a specific device ID
    List<SensorEntity> findByDeviceId(UUID deviceId);

    // Find all sensors by a specific status
    List<SensorEntity> findByStatus(String status);

    // Find all sensors by type
    List<SensorEntity> findByType(String type);

    // Find all sensors by name, name is not distinct value
    List<SensorEntity> findByName(String name);
}