package com.alco.armapi.infrastructure.adapter.persistence.device;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import java.util.List;

public interface DeviceRepository extends JpaRepository<DeviceEntity, UUID> {
}