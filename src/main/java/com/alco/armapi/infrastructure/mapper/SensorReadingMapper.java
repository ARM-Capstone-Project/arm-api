package com.alco.armapi.infrastructure.mapper;

import com.alco.armapi.domain.model.SensorReading;
import com.alco.armapi.infrastructure.adapter.persistence.sensorreading.SensorReadingEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {SensorMapper.class, DeviceMapper.class}) // Use necessary mappers
public interface SensorReadingMapper {
    SensorReadingMapper INSTANCE = Mappers.getMapper(SensorReadingMapper.class);

    // Map from Entity to Domain model
    @Mapping(source = "sensor.id", target = "sensorId") // Map sensor ID
    @Mapping(source = "device.id", target = "deviceId") // Map device ID
    SensorReading toDomainModel(SensorReadingEntity sensorReadingEntity);

    // Map from Domain model to Entity
    @Mapping(source = "sensorId", target = "sensor.id") // Map sensor ID
    @Mapping(source = "deviceId", target = "device.id") // Map device ID
    SensorReadingEntity toEntity(SensorReading sensorReading);
}