package saveapaw_api.categories.exceptions;

public class CategoryNotFoundException extends CategoryException {
    private String id;

    public CategoryNotFoundException(String id) {
        super("Category with id " + id + " does not exist");
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
