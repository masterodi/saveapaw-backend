package saveapaw_api.users.exceptions;

public abstract class UserConflictException extends UserException {
    private UserConflictException(String message) {
        super(message);
    }

    public static class Email extends UserConflictException {
        public Email() {
            super("Email is already taken");
        }
    }

    public static class Username extends UserConflictException {
        public Username() {
            super("Username is already taken");
        }
    }
}
