package saveapaw_api.users.exceptions;

import saveapaw_api.shared.ApiException;

public abstract class UserException extends ApiException {
    public UserException(String message) {
        super(message);
    }
}
