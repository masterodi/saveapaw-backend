package saveapaw_api.shared;

public final class Constraint {
    private Constraint() {
        throw new Error();
    };

    public static final String USERS_UNIQUE_EMAIL = "users_unique_email";
    public static final String USERS_UNIQUE_USERNAME = "users_unique_username";

    public static final String CATEGORIES_UNIQUE_NAME = "categories_unique_name";
    public static final String FK_CATEGORIES_USERS_ID = "fk_categories_users_id";
}
