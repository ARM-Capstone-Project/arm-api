package com.alco.armapi.application.service;

import com.alco.armapi.application.port.in.AdminUseCase;
import com.alco.armapi.application.port.out.AdminRepositoryPort;
import com.alco.armapi.common.UseCase;
import com.alco.armapi.domain.model.User;
import com.alco.armapi.domain.model.Zone;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@UseCase
@Transactional
@RequiredArgsConstructor
public class AdminService implements AdminUseCase {

    private final AdminRepositoryPort adminRepositoryPort;


    @Override
    public User assignZoneToUser(String userId, String zoneId) {

        return adminRepositoryPort.assignZoneToUser(userId,zoneId);
    }

    @Override
    public Zone assignDeviceToZone(String zoneId, String deviceId) {

        return adminRepositoryPort.assignDeviceToZone(zoneId,deviceId);
    }

    @Override
    public User assignRoleToUser(String userId, String roleName) {
        return adminRepositoryPort.assignRoleToUser(userId,roleName);
    }

    @Override
    public User removeRoleFromUser(String userId, String roleName) {
        return adminRepositoryPort.removeRoleFromUser(userId,roleName);
    }


}
