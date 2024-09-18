package com.alco.armapi.infrastructure.mapper;

import com.alco.armapi.domain.model.Role;
import com.alco.armapi.domain.model.User;
import com.alco.armapi.infrastructure.adapter.persistence.role.RoleEntity;
import com.alco.armapi.infrastructure.adapter.persistence.user.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    // Entity to Domain Model
    User toDomainModel(UserEntity userEntity);

    // Domain Model to Entity
    UserEntity toEntity(User user);

}
