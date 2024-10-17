package com.alco.armapi.infrastructure.mapper;


import com.alco.armapi.domain.model.readings.DeviceSensorReading;
import com.alco.armapi.infrastructure.adapter.persistence.readings.DeviceSensorReadingDocument;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DeviceSensorReadingMapper {
    DeviceSensorReadingMapper INSTANCE = Mappers.getMapper(DeviceSensorReadingMapper.class);

    DeviceSensorReadingDocument toEntity(DeviceSensorReading deviceSensorReading);
    DeviceSensorReading toDomainModel(DeviceSensorReadingDocument deviceSensorReadingDocument);
}
