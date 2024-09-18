package saveapaw_api.categories;

public final class CategoryConstraint {
    private CategoryConstraint() {
        throw new Error();
    }

    public static final String UNIQUE_NAME = "categories_unique_name";
    public static final String FK_CATEGORIES_USERS_ID = "fk_categories_users_id";
}