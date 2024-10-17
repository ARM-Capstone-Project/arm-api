package com.alco.armapi.application.port.in;

import com.alco.armapi.common.UseCase;
import com.alco.armapi.domain.model.User;
import com.alco.armapi.domain.model.Zone;

import java.util.UUID;

import com.alco.armapi.infrastructure.adapter.persistence.assignment.AssignmentEntity;

import java.util.List;


@UseCase
public interface AdminUseCase {
    User assignZoneToUser(String userId, UUID zoneId);
    Zone assignDeviceToZone(UUID zoneId, UUID deviceId);
    User assignRoleToUser(String userId, String roleName);

    User removeRoleFromUser(String userId, String roleName);

    AssignmentEntity assignOperatorToManager(String managerId, String operatorId);

    List<User> getOperatorsByManagerId(String managerId);
}
