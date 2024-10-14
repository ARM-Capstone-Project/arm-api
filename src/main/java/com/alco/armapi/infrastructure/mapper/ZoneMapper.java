package com.alco.armapi.infrastructure.mapper;

import com.alco.armapi.domain.model.Zone;
import com.alco.armapi.infrastructure.adapter.persistence.zone.ZoneEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import java.util.List;
@Mapper
public interface ZoneMapper {
    ZoneMapper INSTANCE = Mappers.getMapper(ZoneMapper.class);

    ZoneEntity toEntity(Zone zone);
    
    @Mapping(target = "devices", source = "devices")
    Zone toDomainModel(ZoneEntity zoneEntity);

    List<ZoneEntity> toEntitySet(List<Zone> zones);
    List<Zone> toDomainModelSet(List<ZoneEntity> zoneEntities);
}
