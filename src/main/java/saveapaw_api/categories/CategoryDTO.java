package saveapaw_api.categories;

import saveapaw_api.users.dtos.UserDTO;

public class CategoryDTO {
    public static record Query(String id, String name, UserDTO createdBy) {
    }

    public static record Create(String name, String created_by_id) {
    }

    public static record Update(String name) {
    }
}
