package saveapaw_api.users.exceptions;

import org.springframework.dao.DataIntegrityViolationException;

import saveapaw_api.shared.Constraint;

public class UserInvalidDataException extends UserException {
    private UserInvalidDataException(String message) {
        super(message);
    }

    public static class EmailConflict extends UserInvalidDataException {
        public EmailConflict() {
            super("Email is already taken");
        }
    }

    public static class UsernameConflict extends UserInvalidDataException {
        public UsernameConflict() {
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
