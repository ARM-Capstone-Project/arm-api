package com.alco.armapi.infrastructure.mapper;

import com.alco.armapi.domain.model.DeviceThreshold;
import com.alco.armapi.infrastructure.adapter.persistence.threshold.DeviceThresholdDocument;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DeviceThresholdMapper {
    DeviceThresholdMapper INSTANCE = Mappers.getMapper(DeviceThresholdMapper.class);

    DeviceThresholdDocument toEntity(DeviceThreshold deviceThreshold);
    DeviceThreshold toDomainModel(DeviceThresholdDocument deviceThresholdDocument);
}
