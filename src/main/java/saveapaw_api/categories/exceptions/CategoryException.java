package saveapaw_api.categories.exceptions;

import org.springframework.dao.DataIntegrityViolationException;

import saveapaw_api.categories.CategoryConstraint;
import saveapaw_api.shared.ApiException;

public abstract class CategoryException extends ApiException {
    public CategoryException(String message) {
        super(message);
    }

    public static boolean isCreatedByNotFound(DataIntegrityViolationException e) {
        return getConstraintName(e).equals(CategoryConstraint.FK_CATEGORIES_USERS_ID);
    }

    public static boolean isNameConflict(DataIntegrityViolationException e) {
        return getConstraintName(e).equals(CategoryConstraint.UNIQUE_NAME);
    }
}