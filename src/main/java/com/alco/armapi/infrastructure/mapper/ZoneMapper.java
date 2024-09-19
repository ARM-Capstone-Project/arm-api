package com.alco.armapi.infrastructure.mapper;

import com.alco.armapi.domain.model.Role;
import com.alco.armapi.domain.model.Zone;
import com.alco.armapi.infrastructure.adapter.persistence.role.RoleEntity;
import com.alco.armapi.infrastructure.adapter.persistence.zone.ZoneEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ZoneMapper {
    ZoneMapper INSTANCE = Mappers.getMapper(ZoneMapper.class);

    ZoneEntity toEntity(Zone zone);
    @Mapping(target = "devices", source = "devices")
    Zone toDomainModel(ZoneEntity zoneEntity);
}
