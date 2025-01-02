package saveapaw_api.auth;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import saveapaw_api.users.User;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AuthMapper {
    User fromRegisterDTO(AuthDTO.Register dto);

    AuthDTO.Session toSessionDTO(User user);
}
