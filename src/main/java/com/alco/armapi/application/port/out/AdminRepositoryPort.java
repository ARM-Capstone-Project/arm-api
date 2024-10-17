package com.alco.armapi.application.port.out;

import com.alco.armapi.domain.model.User;
import com.alco.armapi.domain.model.Zone;

import java.util.UUID;

import com.alco.armapi.infrastructure.adapter.persistence.assignment.AssignmentEntity;
import com.alco.armapi.infrastructure.adapter.persistence.user.UserEntity;

import java.util.List;


public interface AdminRepositoryPort {
    User assignRoleToUser(String userId, String roleName);

    User assignZoneToUser(String userId, UUID zoneId);

    Zone assignDeviceToZone(UUID zoneId, UUID deviceId);

    User removeRoleFromUser(String userId, String roleName);

    AssignmentEntity assignOperatorToManager(String manager, String operator);

    List<User> getOperatorsByManagerId(String managerId);
}
