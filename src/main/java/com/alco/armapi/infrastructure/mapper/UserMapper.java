package com.alco.armapi.infrastructure.mapper;

import com.alco.armapi.domain.model.User;
import com.alco.armapi.infrastructure.adapter.persistence.user.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    @Mapping(target = "roles", source = "roles")
    @Mapping(target = "zones", source = "zones")
    // Entity to Domain Model
    User toDomainModel(UserEntity userEntity);

    // Domain Model to Entity
    
    UserEntity toEntity(User user);

}
