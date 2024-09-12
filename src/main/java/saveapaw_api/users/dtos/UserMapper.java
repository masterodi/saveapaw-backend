package saveapaw_api.users.dtos;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import saveapaw_api.users.User;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toUser(UserCreateDTO dto);

    void updateUser(UserUpdateDTO dto, @MappingTarget User user);

    void updateUser(UserUpdateManyDTO dto, @MappingTarget User user);

    UserDTO toUserDTO(User user);
}
