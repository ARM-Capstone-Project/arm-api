package com.alco.armapi.infrastructure.mapper;

//import com.alco.armapi.domain.model.Role;
import com.alco.armapi.domain.model.User;
//import com.alco.armapi.infrastructure.adapter.persistence.role.RoleEntity;
import com.alco.armapi.infrastructure.adapter.persistence.user.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

//import java.util.Set;
import java.util.stream.Collectors;
import java.util.List;

@Mapper(uses = {RoleMapper.class, SensorReadingMapper.class, DeviceMapper.class, ZoneMapper.class})
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "roles", source = "roles")
    @Mapping(target = "zones", source = "zones")
    User toDomainModel(UserEntity userEntity);

    @Mapping(target = "roles", source = "roles")
    @Mapping(target = "zones", source = "zones")
    // Domain Model to Entity
    UserEntity toEntity(User user);    

    // Method to map List<UserEntity> to List<User>
    default List<User> toDomainModelList(List<UserEntity> userEntities) {
        return userEntities.stream()
                .map(this::toDomainModel)
                .collect(Collectors.toList());
    }

    // Method to map List<User> to List<UserEntity>
    default List<UserEntity> toEntityList(List<User> users) {
        return users.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

}
