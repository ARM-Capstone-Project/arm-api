package com.alco.armapi.infrastructure.mapper;

import com.alco.armapi.domain.model.SensorReading;
import com.alco.armapi.infrastructure.adapter.persistence.sensorreading.SensorReadingEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SensorReadingMapper {
    SensorReadingMapper INSTANCE = Mappers.getMapper(SensorReadingMapper.class);

    // Map from SensorReadingEntity to SensorReading
    @Mapping(source = "sensor.id", target = "sensorId")
    @Mapping(source = "device.id", target = "deviceId")
    SensorReading toDomainModel(SensorReadingEntity sensorReadingEntity);

    // Map from SensorReading to SensorReadingEntity
    @Mapping(source = "sensorId", target = "sensor.id")
    @Mapping(source = "deviceId", target = "device.id")
    SensorReadingEntity toEntity(SensorReading sensorReading);
}