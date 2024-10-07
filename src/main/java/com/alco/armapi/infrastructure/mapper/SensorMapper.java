package com.alco.armapi.infrastructure.mapper;

import com.alco.armapi.domain.model.Sensor;
import com.alco.armapi.infrastructure.adapter.persistence.sensor.SensorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = SensorReadingMapper.class) // Use SensorReadingMapper for SensorReading mappings
public interface SensorMapper {
    SensorMapper INSTANCE = Mappers.getMapper(SensorMapper.class);

    // Map from SensorEntity to Sensor
    @Mapping(source = "device.id", target = "deviceId") // Map device ID
    Sensor toDomainModel(SensorEntity sensorEntity);

    // Map from Sensor to SensorEntity
    @Mapping(source = "deviceId", target = "device.id") // Map device ID
    SensorEntity toEntity(Sensor sensor);

    // Map List of SensorEntity to List of Sensor
    List<Sensor> toDomainModelList(List<SensorEntity> sensorEntities);

    // Map List of Sensor to List of SensorEntity
    List<SensorEntity> toEntityList(List<Sensor> sensors);
}