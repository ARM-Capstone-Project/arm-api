package com.alco.armapi.infrastructure.mapper;

import com.alco.armapi.domain.model.Device;
import com.alco.armapi.domain.model.Zone;
import com.alco.armapi.infrastructure.adapter.persistence.device.DeviceEntity;
import com.alco.armapi.infrastructure.adapter.persistence.zone.ZoneEntity;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.mapstruct.Mapping;

@Mapper (uses={ZoneMapper.class})
public interface DeviceMapper {
    DeviceMapper INSTANCE = Mappers.getMapper(DeviceMapper.class);

    @Mapping(source="zone.id", target="zoneId")
    Device toDomainModel(DeviceEntity deviceEntity);

    @Mapping(source="sensors", target="sensors")
    @Mapping(source="zoneId", target="zone.id")
    DeviceEntity toEntity(Device device);

    List<Device> toDomainModelList(List<DeviceEntity> deviceEntities);

    List<DeviceEntity> toEntityList(List<Device> devices);
}
