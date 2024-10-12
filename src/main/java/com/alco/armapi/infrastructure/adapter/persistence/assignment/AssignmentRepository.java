package com.alco.armapi.infrastructure.adapter.persistence.assignment;

import com.alco.armapi.infrastructure.adapter.persistence.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignmentRepository extends JpaRepository<AssignmentEntity, String> {
    List<AssignmentEntity> findByManager(UserEntity manager);
}