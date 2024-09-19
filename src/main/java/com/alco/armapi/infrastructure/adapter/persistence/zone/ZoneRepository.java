package com.alco.armapi.infrastructure.adapter.persistence.zone;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ZoneRepository extends JpaRepository<ZoneEntity, String> {
    List<ZoneEntity> findByUsers_Id(String userId);
}