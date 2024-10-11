package com.alco.armapi.infrastructure.mapper;

import com.alco.armapi.domain.model.User;
import com.alco.armapi.domain.model.Zone;
import com.alco.armapi.infrastructure.adapter.persistence.user.UserEntity;
import com.alco.armapi.infrastructure.adapter.persistence.zone.ZoneEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {RoleMapper.class, ZoneMapper.class})
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "roles", source = "roles")
    @Mapping(target = "devices", ignore=true)
    @Mapping(target = "zones", expression = "java(zoneMapper.toDomainModelSet(userEntity.getZones()))")
    User toDomainModel(UserEntity userEntity);

    @Mapping(target = "roles", source = "roles")
    @Mapping(target = "devices", ignore=true)
    @Mapping(target = "zones", expression = "java(zoneMapper.toEntitySet(user.getZones()))")
    UserEntity toEntity(User user);

    default List<User> toDomainModelList(List<UserEntity> userEntities) {
        return userEntities.stream()
                .map(this::toDomainModel)
                .collect(Collectors.toList());
    }

    default List<UserEntity> toEntityList(List<User> users) {
        return users.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    // default Set<Zone> mapZones(Set<ZoneEntity> zoneEntities) {
    //     if (zoneEntities == null) {
    //         return null; 
    //     }
    //     return zoneEntities.stream()
    //             .map(zoneEntity -> {
    //                 Zone zone = new Zone();
    //                 zone.setId(zoneEntity.getId());
    //                 zone.setName(zoneEntity.getName());
    //                 // Set other properties as needed
    //                 return zone;
    //             })
    //             .collect(Collectors.toSet());
    // }
}