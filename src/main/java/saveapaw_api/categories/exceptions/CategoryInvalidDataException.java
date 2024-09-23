package saveapaw_api.categories.exceptions;

import org.springframework.dao.DataIntegrityViolationException;

import saveapaw_api.shared.Constraint;

public abstract class CategoryInvalidDataException extends CategoryException {
    private CategoryInvalidDataException(String message) {
        super(message);
    }

    public static class NameConflict extends CategoryInvalidDataException {
        public NameConflict() {
            super("Category name is already used");
        }
    }

    public static boolean isNameConflict(DataIntegrityViolationException e) {
        return getConstraintName(e).equals(Constraint.CATEGORIES_UNIQUE_NAME);
    }

    public static boolean isCreatedByNotFound(DataIntegrityViolationException e) {
        return getConstraintName(e).equals(Constraint.FK_CATEGORIES_USERS_ID);
    }
}
