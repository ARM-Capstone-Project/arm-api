package com.alco.armapi.infrastructure.adapter.persistence.sensorreading;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorReadingRepository extends JpaRepository<SensorReadingEntity, UUID> {
    
}