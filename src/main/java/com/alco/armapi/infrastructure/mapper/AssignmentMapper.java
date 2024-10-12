package com.alco.armapi.infrastructure.mapper;

import com.alco.armapi.domain.model.Assignment;
import com.alco.armapi.infrastructure.adapter.persistence.assignment.AssignmentEntity;
import org.mapstruct.factory.Mappers;

public interface AssignmentMapper {
    AssignmentMapper INSTANCE = Mappers.getMapper(AssignmentMapper.class);

    AssignmentEntity toEntity(Assignment assignment);
    Assignment toDomainModel(AssignmentEntity assignmentEntity);

}
