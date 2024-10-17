package com.alco.armapi.infrastructure.adapter.persistence.device;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.UUID;
import java.util.List;

@Repository
public interface DeviceRepository extends JpaRepository<DeviceEntity, UUID> {
    List<DeviceEntity> findByStatus(String status);

    @Query("SELECT d FROM DeviceEntity d WHERE LOWER(d.location) = LOWER(:location)")
    List<DeviceEntity> findByLocation(String location);

    @Query("SELECT d FROM DeviceEntity d WHERE LOWER(d.type) = LOWER(:type)")
    List<DeviceEntity> findByType(String type);

    List<DeviceEntity> findByZoneId(UUID zoneId);
}