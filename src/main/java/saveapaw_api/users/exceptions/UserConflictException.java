package saveapaw_api.users.exceptions;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;

import saveapaw_api.users.UserConstraint;

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

    private static String getConstraintName(DataIntegrityViolationException e) {
        return ((ConstraintViolationException) e.getCause()).getConstraintName();
    }

    public static boolean isEmailConflict(DataIntegrityViolationException e) {
        return getConstraintName(e).equals(UserConstraint.UNIQUE_EMAIL);
    }

    public static boolean isUsernameConflict(DataIntegrityViolationException e) {
        return getConstraintName(e).equals(UserConstraint.UNIQUE_USERNAME);
    }
}
