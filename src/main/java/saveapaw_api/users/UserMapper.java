package saveapaw_api.users;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {
    User fromUserCreateDTO(UserDTO.Create dto);

    void updateUser(UserDTO.Update dto, @MappingTarget User user);

    UserDTO.Query toUserDTO(User user);
}
