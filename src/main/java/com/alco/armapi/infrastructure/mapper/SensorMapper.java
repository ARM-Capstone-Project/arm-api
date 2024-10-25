package com.alco.armapi.infrastructure.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.alco.armapi.domain.model.Sensor;
import com.alco.armapi.infrastructure.adapter.persistence.sensor.SensorEntity;

import org.mapstruct.Mapping;

@Mapper
public interface SensorMapper {
    SensorMapper INSTANCE = Mappers.getMapper(SensorMapper.class);

    @Mapping(source = "device.id", target = "deviceId")
    Sensor toDomainModel(SensorEntity sensorEntity);

    @Mapping(source = "deviceId", target = "device.id")
    SensorEntity toEntity(Sensor sensor);

    List<Sensor> toDomainModelList(List<SensorEntity> sensorEntities);

    List<SensorEntity> toEntityList(List<Sensor> sensors);
}
