package saveapaw_api.users.exceptions;

import org.springframework.dao.DataIntegrityViolationException;

import saveapaw_api.shared.Constraint;

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

    public static boolean isEmailConflict(DataIntegrityViolationException e) {
        return Constraint.USERS_UNIQUE_EMAIL.equals(getConstraintName(e));

    }

    public static boolean isUsernameConflict(DataIntegrityViolationException e) {
        return Constraint.USERS_UNIQUE_USERNAME.equals(getConstraintName(e));
    }
}
