package com.alco.armapi.infrastructure.adapter.persistence.user;

import com.alco.armapi.application.port.out.UserRepositoryPort;
import com.alco.armapi.common.Constants;
import com.alco.armapi.common.PersistenceAdapter;
import com.alco.armapi.domain.model.User;
import com.alco.armapi.infrastructure.adapter.persistence.role.RoleEntity;
import com.alco.armapi.infrastructure.adapter.persistence.role.RoleRepository;
import com.alco.armapi.infrastructure.adapter.persistence.zone.ZoneEntity;
import com.alco.armapi.infrastructure.adapter.persistence.zone.ZoneRepository;
import com.alco.armapi.infrastructure.mapper.UserMapper;
import lombok.RequiredArgsConstructor;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepositoryPort {
    private static final Logger log = LoggerFactory.getLogger(UserRepositoryAdapter.class);
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    public static final String ERROR_ROLE_NOT_FOUND = "Error: Role is not found.";
    @Autowired
    private ZoneRepository zoneRepository;


    @Override
    public List<User> listUsers() {
        List<UserEntity> userEntities = userRepository.findAll();
        return userEntities.stream()
                .map(UserMapper.INSTANCE::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public User getUserById(String userId){
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

//        List<ZoneEntity> zoneEntities = zoneRepository.findByUsers_Id(userId);
//        if (!zoneEntities.isEmpty()) {
//            userEntity.setZones(new HashSet<>(zoneEntities));
//        }
        return UserMapper.INSTANCE.toDomainModel(userEntity);
    }

    @Override
    public User saveUser(User user){
        UserEntity newUser = createUserEntity(user);
        assignDefaultRoles(newUser);
        userRepository.save(newUser);
        return UserMapper.INSTANCE.toDomainModel(newUser);
    }

    private void assignDefaultRoles(UserEntity newUser) {
        RoleEntity userRole = roleRepository.findByName(Constants.ROLE_USER)
                .orElseThrow(() -> new RuntimeException(ERROR_ROLE_NOT_FOUND));
        newUser.setRoles(Set.of(userRole));
    }


    private UserEntity createUserEntity(User user) {
        UserEntity newUser = new UserEntity();
        newUser.setUsername(user.getUsername());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(encoder.encode(user.getPassword()));
        return newUser;
    }


    @Override
    public void deleteUser(String id) {
       userRepository.deleteById(id);
    }

    @Override
    public User updateUser(String id, User user) {
        // Retrieve the existing user by id
        UserEntity existingUserEntity = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        existingUserEntity.setUsername(isNotEmpty(user.getUsername()) ? user.getUsername() : existingUserEntity.getUsername());
        existingUserEntity.setEmail(isNotEmpty(user.getEmail()) ? user.getEmail() : existingUserEntity.getEmail());
        existingUserEntity.setPassword(isNotEmpty(user.getPassword()) ? encoder.encode(user.getPassword()) : existingUserEntity.getPassword());  // Ensure password is encoded if provided

//        existingUserEntity.setRoles(user.getRoles() != null && !user.getRoles().isEmpty() ?
//                user.getRoles().stream().map(role -> new RoleEntity(role.getId(), role.getName(), role.getDescription())).collect(Collectors.toSet())
//                : existingUserEntity.getRoles());

//        if (user.getAssignedZones() != null) {
//            existingUserEntity.setAssignedZones(
//                    user.getAssignedZones().stream()
//                            .map(zone -> new ZoneEntity(zone.getZoneId(), zone.getZoneName()))
//                            .collect(Collectors.toList())
//            );
//        }
//
//        if (user.getAssignedDevices() != null) {
//            existingUserEntity.setAssignedDevices(
//                    user.getAssignedDevices().stream()
//                            .map(device -> new DeviceEntity(device.getSensorId(), device.getDeviceName()))
//                            .collect(Collectors.toList())
//            );
//        }

        UserEntity updatedUserEntity = userRepository.save(existingUserEntity);
        return UserMapper.INSTANCE.toDomainModel(updatedUserEntity);
    }
    // Utility method to check if a string is not null and not empty
    private boolean isNotEmpty(String value) {
        return value != null && !value.trim().isEmpty();
    }
}
