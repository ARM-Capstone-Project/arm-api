package com.alco.armapi.infrastructure.adapter.persistence.device;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface DeviceRepository extends JpaRepository<DeviceEntity, UUID> {
    
    List<DeviceEntity> findByStatus(String status);

    List<DeviceEntity> findByLocation(String location);

    List<DeviceEntity> findByType(String type);

    List<DeviceEntity> findByZoneId(UUID zoneId);
}