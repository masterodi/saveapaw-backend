package saveapaw_api.users;

import java.time.LocalDate;

public class UserDTO {
    public static record Query(String id, String email, String username, LocalDate createdAt) {
    }

    public static record Create(String email, String username, String password) {
    }

    public static record Update(String email, String username) {
    }
}
