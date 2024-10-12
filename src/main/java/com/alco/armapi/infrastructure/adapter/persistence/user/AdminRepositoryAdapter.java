package com.alco.armapi.infrastructure.adapter.persistence.user;

import com.alco.armapi.application.port.out.AdminRepositoryPort;
import com.alco.armapi.common.PersistenceAdapter;
import com.alco.armapi.domain.model.User;
import com.alco.armapi.domain.model.Zone;
import com.alco.armapi.infrastructure.adapter.persistence.assignment.AssignmentEntity;
import com.alco.armapi.infrastructure.adapter.persistence.assignment.AssignmentRepository;
import com.alco.armapi.infrastructure.adapter.persistence.device.DeviceEntity;
import com.alco.armapi.infrastructure.adapter.persistence.device.DeviceRepository;
import com.alco.armapi.infrastructure.adapter.persistence.role.RoleEntity;
import com.alco.armapi.infrastructure.adapter.persistence.role.RoleRepository;
import com.alco.armapi.infrastructure.adapter.persistence.zone.ZoneEntity;
import com.alco.armapi.infrastructure.adapter.persistence.zone.ZoneRepository;
import com.alco.armapi.infrastructure.mapper.AssignmentMapper;
import com.alco.armapi.infrastructure.mapper.UserMapper;
import com.alco.armapi.infrastructure.mapper.ZoneMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
public class AdminRepositoryAdapter implements AdminRepositoryPort {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ZoneRepository zoneRepository;
    private final DeviceRepository deviceRepository;
    private final AssignmentRepository assignmentRepository;

    @Override
    public User assignRoleToUser(String userId, String roleName) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        RoleEntity role = roleRepository.findByName(roleName).orElseThrow(() -> new RuntimeException("Role not found"));
        user.getRoles().add(role); // Assign the role to the user
        userRepository.save(user);
        return UserMapper.INSTANCE.toDomainModel(user);
    }

    @Override
    public User removeRoleFromUser(String userId, String roleName) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        RoleEntity role = roleRepository.findByName(roleName).orElseThrow(() -> new RuntimeException("Role not found"));
        user.getRoles().remove(role); // Assign the role to the user
        userRepository.save(user);
        return UserMapper.INSTANCE.toDomainModel(user);
    }

    @Override
    public AssignmentEntity assignOperatorToManager(String managerId, String operatorId) {
        UserEntity manager = userRepository.findById(managerId).orElseThrow(() -> new RuntimeException("User not found"));
        UserEntity operator = userRepository.findById(operatorId).orElseThrow(() -> new RuntimeException("User not found"));
        AssignmentEntity assignment = new AssignmentEntity(manager, operator);
        return assignmentRepository.save(assignment);
    }

    @Override
    public List<User> getOperatorsByManagerId(String managerId) {
        // Fetch the manager
        UserEntity manager = userRepository.findById(managerId).orElseThrow(() -> new RuntimeException("Manager not found"));

        // Get the list of assignments for the manager
        List<AssignmentEntity> assignments = assignmentRepository.findByManager(manager);

        // Extract the operators from the assignments
        return assignments.stream()
                .map(AssignmentEntity::getOperator)
                .map(UserMapper.INSTANCE::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public User assignZoneToUser(String userId, String zoneId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        ZoneEntity zone = zoneRepository.findById(zoneId).orElseThrow(() -> new RuntimeException("Zone not found"));

        user.getZones().add(zone); // Assign the zone to the user
        userRepository.save(user);
        return UserMapper.INSTANCE.toDomainModel(user);
    }

    @Override
    public Zone assignDeviceToZone(String zoneId, String deviceId) {
        ZoneEntity zone = zoneRepository.findById(zoneId).orElseThrow(() -> new RuntimeException("Zone not found"));
        DeviceEntity device = deviceRepository.findById(deviceId).orElseThrow(() -> new RuntimeException("Device not found"));

        zone.getDevices().add(device);
        zoneRepository.save(zone);

        return ZoneMapper.INSTANCE.toDomainModel(zone);
    }


}
