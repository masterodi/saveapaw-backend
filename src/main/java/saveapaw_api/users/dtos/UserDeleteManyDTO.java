package saveapaw_api.users.dtos;

import java.util.List;

public record UserDeleteManyDTO(String op, List<String> ids) {
}
