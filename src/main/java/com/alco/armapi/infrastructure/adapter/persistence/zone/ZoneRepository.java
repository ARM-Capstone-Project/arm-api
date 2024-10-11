package com.alco.armapi.infrastructure.adapter.persistence.zone;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ZoneRepository extends JpaRepository<ZoneEntity, UUID> {
    List<ZoneEntity> findByUsers_Id(String userId);
}