package saveapaw_api.users.dtos;

import java.time.LocalDate;

public record UserDTO(String id, String email, String username, LocalDate createdAt) {
}
