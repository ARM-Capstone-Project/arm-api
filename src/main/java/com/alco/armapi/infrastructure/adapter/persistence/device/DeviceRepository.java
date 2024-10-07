package com.alco.armapi.infrastructure.adapter.persistence.device;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DeviceRepository extends JpaRepository<DeviceEntity, String> {
    
    List<DeviceEntity> findByStatus(String status);

    List<DeviceEntity> findByLocation(String location);

    List<DeviceEntity> findByType(String type);

    List<DeviceEntity> findByZoneId(String zoneId);
}