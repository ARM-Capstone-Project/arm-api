package com.alco.armapi.infrastructure.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.alco.armapi.domain.model.SensorReading;
import com.alco.armapi.infrastructure.adapter.persistence.sensorreading.SensorReadingEntity;

import org.mapstruct.Mapping;

@Mapper
public interface SensorReadingMapper {
    SensorReadingMapper INSTANCE = Mappers.getMapper(SensorReadingMapper.class);

    // Map from SensorReadingEntity to SensorReading
    @Mapping(source = "sensor.id", target = "sensorId")
    SensorReading toDomainModel(SensorReadingEntity sensorReadingEntity);

    // Map from SensorReading to SensorReadingEntity
    @Mapping(source = "sensorId", target = "sensor.id")
    SensorReadingEntity toEntity(SensorReading sensorReading);
}
