package saveapaw_api.shared;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;

public abstract class ApiException extends Exception {
    public ApiException(String message) {
        super(message);
    }

    protected static String getConstraintName(DataIntegrityViolationException e) {
        return ((ConstraintViolationException) e.getCause()).getConstraintName();
    }
}
