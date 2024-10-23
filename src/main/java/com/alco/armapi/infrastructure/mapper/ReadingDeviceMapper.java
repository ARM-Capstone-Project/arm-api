package com.alco.armapi.infrastructure.mapper;

import com.alco.armapi.domain.model.readings.ReadingDevice;
import com.alco.armapi.infrastructure.adapter.persistence.readings.DeviceFromReadings;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReadingDeviceMapper {
    ReadingDeviceMapper INSTANCE = Mappers.getMapper(ReadingDeviceMapper.class);

    @Mapping(source="readings", target="readings")
    DeviceFromReadings toEntity(ReadingDevice readingDevice);

    @Mapping(source="readings", target="readings")
    ReadingDevice toDomainModel(DeviceFromReadings readingDevice);    
}