package com.alco.armapi.infrastructure.mapper;

import com.alco.armapi.domain.model.Device;
import com.alco.armapi.infrastructure.adapter.persistence.device.DeviceEntity;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.mapstruct.Mapping;

@Mapper
public interface DeviceMapper {
    DeviceMapper INSTANCE = Mappers.getMapper(DeviceMapper.class);

    Device toDomainModel(DeviceEntity deviceEntity);

    @Mapping(source="sensors", target="sensors")
    DeviceEntity toEntity(Device device);

    List<Device> toDomainModelList(List<DeviceEntity> deviceEntities);

    List<DeviceEntity> toEntityList(List<Device> devices);
}
