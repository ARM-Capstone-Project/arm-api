package com.alco.armapi.application.port.out;

import com.alco.armapi.domain.model.User;
import com.alco.armapi.domain.model.Zone;
import com.alco.armapi.infrastructure.adapter.payload.request.AssignRoleRequest;

public interface AdminRepositoryPort {
    User assignRoleToUser(String userId, String roleName);

    User assignZoneToUser(String userId, String zoneId);

    Zone assignDeviceToZone(String zoneId, String deviceId);

    User removeRoleFromUser(String userId, String roleName);
}
