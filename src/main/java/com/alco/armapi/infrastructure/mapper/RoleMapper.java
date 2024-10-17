package com.alco.armapi.infrastructure.mapper;

import com.alco.armapi.domain.model.Role;
import com.alco.armapi.infrastructure.adapter.persistence.role.RoleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoleMapper {
    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    RoleEntity toEntity(Role role);
    Role toDomainModel(RoleEntity roleEntity);

}
