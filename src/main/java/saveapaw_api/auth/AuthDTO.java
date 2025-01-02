package saveapaw_api.auth;

public abstract class AuthDTO {
    public static record Login(String username, String password) {
    }

    public static record Register(String email, String username, String password) {
    }

    public static record Session(String email, String username, String createdAt, String role) {
    }
}
