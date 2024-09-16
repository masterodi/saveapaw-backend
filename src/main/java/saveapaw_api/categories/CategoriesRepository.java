package saveapaw_api.categories;

import org.springframework.stereotype.Repository;

import saveapaw_api.shared.CrudRepository;

@Repository
public interface CategoriesRepository extends CrudRepository<Category, String> {
}
