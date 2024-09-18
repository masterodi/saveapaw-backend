package saveapaw_api.users.exceptions;

import org.springframework.dao.DataIntegrityViolationException;

import saveapaw_api.shared.ApiException;
import saveapaw_api.users.UserConstraint;

public abstract class UserException extends ApiException {
    public UserException(String message) {
        super(message);
    }

    public static boolean isEmailConflict(DataIntegrityViolationException e) {
        return getConstraintName(e).equals(UserConstraint.UNIQUE_EMAIL);
    }

    public static boolean isUsernameConflict(DataIntegrityViolationException e) {
        return getConstraintName(e).equals(UserConstraint.UNIQUE_USERNAME);
    }
}
