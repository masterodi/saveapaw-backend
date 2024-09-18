package saveapaw_api.categories.exceptions;

public abstract class CategoryConflictException extends CategoryException {
    private CategoryConflictException(String message) {
        super(message);
    }

    public static class Name extends CategoryConflictException {
        public Name() {
            super("Category name is already used");
        }
    }
}