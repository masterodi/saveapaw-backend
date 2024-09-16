package saveapaw_api.users;

public class UserDTO {
    public static record Query(String id, String email, String username) {
    }

    public static record Create(String email, String username, String password) {
    }

    public static record Update(String email, String username) {
    }
}
