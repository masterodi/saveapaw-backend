package saveapaw_api.users.exceptions;

public class UserNotFoundException extends UserException {
    private String id;

    public UserNotFoundException(String id) {
        super("User with id " + id + " not found");
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
