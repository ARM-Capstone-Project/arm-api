package com.alco.armapi.infrastructure.mapper;

import com.alco.armapi.domain.model.Device;
import com.alco.armapi.infrastructure.adapter.persistence.device.DeviceEntity;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.mapstruct.Mapping;

@Mapper (uses={ZoneMapper.class})
public interface DeviceMapper {
    DeviceMapper INSTANCE = Mappers.getMapper(DeviceMapper.class);

    @Mapping(source="zone.id", target="zoneId")
    Device toDomainModel(DeviceEntity deviceEntity);

    @Mapping(target="sensors", ignore=true)
    //did not map zoneId since mapstruct is creating random zoneId though input is null or empty
    //@Mapping(source = "zoneId", target = "zone.id", conditionExpression = "java(device.getZoneId() != null && !device.getZoneId().equals(java.util.UUID.fromString(\"00000000-0000-0000-0000-000000000000\")))")
    DeviceEntity toEntity(Device device);

    List<Device> toDomainModelList(List<DeviceEntity> deviceEntities);

    List<DeviceEntity> toEntityList(List<Device> devices);
}
