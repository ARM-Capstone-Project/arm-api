package com.alco.armapi.infrastructure.mapper;

import com.alco.armapi.domain.model.Zone;
import com.alco.armapi.infrastructure.adapter.persistence.zone.ZoneEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ZoneMapper {
    ZoneMapper INSTANCE = Mappers.getMapper(ZoneMapper.class);

    @Mapping(target="devices", ignore=true) //added to breake circular reference
    @Mapping(target="users", ignore=true)
    Zone toDomainModel(ZoneEntity zoneEntity);

    @Mapping(target="devices", ignore=true)
    @Mapping(target="users", ignore=true)
    ZoneEntity toEntity(Zone zone);

    default List<Zone> toDomainModelList(List<ZoneEntity> zoneEntities) {
        return zoneEntities.stream()
                .map(this::toDomainModel)
                .collect(Collectors.toList());
    }

    default List<ZoneEntity> toEntityList(List<Zone> zones) {
        return zones.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}