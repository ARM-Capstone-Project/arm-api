package com.alco.armapi.infrastructure.mapper;

import com.alco.armapi.domain.model.Zone;
import com.alco.armapi.infrastructure.adapter.persistence.zone.ZoneEntity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {UserMapper.class, DeviceMapper.class}) // Use UserMapper for user mappings
public interface ZoneMapper {
    ZoneMapper INSTANCE = Mappers.getMapper(ZoneMapper.class);

    // Map from ZoneEntity to Zone
    @Mapping(source = "users", target = "users") // Map UserEntity List to User List
    Zone toDomainModel(ZoneEntity zoneEntity);

    // Map from Zone to ZoneEntity
    @Mapping(source = "users", target = "users") // Map User List to UserEntity List
    ZoneEntity toEntity(Zone zone);

    // Map List of ZoneEntity to List of Zone
    List<Zone> toDomainModelList(List<ZoneEntity> zoneEntities);

    // Map List of Zone to List of ZoneEntity
    List<ZoneEntity> toEntityList(List<Zone> zones);
}
