package com.alco.armapi.infrastructure.mapper;

import com.alco.armapi.domain.model.Device;
import com.alco.armapi.infrastructure.adapter.persistence.device.DeviceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {SensorMapper.class, UserMapper.class, ZoneMapper.class}) // Include necessary mappers
public interface DeviceMapper {

    DeviceMapper INSTANCE = Mappers.getMapper(DeviceMapper.class);

    // Map from DeviceEntity to Device
    @Mapping(source = "zone", target = "zone") // Map ZoneEntity to Zone
    @Mapping(source = "users", target = "users") // Map UserEntity List to User List
    @Mapping(source = "sensors", target = "sensors") // Map SensorEntity List to Sensor List
    Device toDomainModel(DeviceEntity deviceEntity);

    // Map from Device to DeviceEntity
    @Mapping(source = "zone", target = "zone") // Map Zone to ZoneEntity
    @Mapping(source = "users", target = "users") // Map User List to UserEntity List
    @Mapping(source = "sensors", target = "sensors") // Map Sensor List to SensorEntity List
    DeviceEntity toEntity(Device device);

    // Map List of DeviceEntity to List of Device
    List<Device> toDomainModelList(List<DeviceEntity> deviceEntities);

    // Map List of Device to List of DeviceEntity
    List<DeviceEntity> toEntityList(List<Device> devices);
}