package saveapaw_api.categories;

import java.time.LocalDate;

import saveapaw_api.users.UserDTO;

public class CategoryDTO {
    public static record Query(String id, String name, LocalDate createdAt, UserDTO.Query createdBy) {
    }

    public static record Create(String name, String created_by_id) {
    }

    public static record Update(String name) {
    }
}
