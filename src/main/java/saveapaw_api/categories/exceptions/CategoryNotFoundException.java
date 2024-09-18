package saveapaw_api.categories.exceptions;

public class CategoryNotFoundException extends CategoryException {
    public CategoryNotFoundException(String id) {
        super("Category with id " + id + " does not exist");
    }
}
