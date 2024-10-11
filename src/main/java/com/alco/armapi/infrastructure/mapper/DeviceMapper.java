package com.alco.armapi.infrastructure.mapper;

import com.alco.armapi.domain.model.Device;
import com.alco.armapi.infrastructure.adapter.persistence.device.DeviceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import com.alco.armapi.infrastructure.adapter.persistence.user.UserEntity;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", uses = {SensorMapper.class})
public interface DeviceMapper {
    DeviceMapper INSTANCE = Mappers.getMapper(DeviceMapper.class);

    @Mapping(source = "zone.id", target="zoneId")
    @Mapping(target ="users", expression = "java(deviceEntity.getUsers().stream().map(UserEntity::getId).collect(Collectors.toSet()))")
    @Mapping(source="sensors", target="sensors")
    Device toDomainModel(DeviceEntity deviceEntity);

    @Mapping(source = "zoneId", target="zone.id")
    @Mapping(target ="users", expression = "java(device.getUsers().stream().map(id -> { UserEntity user = new UserEntity(); user.setId(id); return user; }).collect(Collectors.toSet()))")
    @Mapping(source="sensors", target="sensors")
    DeviceEntity toEntity(Device device);

    List<Device> toDomainModelList(List<DeviceEntity> deviceEntities);

    List<DeviceEntity> toEntityList(List<Device> devices);
}