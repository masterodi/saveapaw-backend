package saveapaw_api.categories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import saveapaw_api.categories.exceptions.CategoryInvalidDataException;
import saveapaw_api.categories.exceptions.CategoryNotFoundException;
import saveapaw_api.shared.CrudService;
import saveapaw_api.users.UsersRepository;
import saveapaw_api.users.exceptions.UserNotFoundException;

@Service
public class CategoriesService
        implements CrudService<CategoryDTO.Query, CategoryDTO.Create, CategoryDTO.Update, String> {
    @Autowired
    private CategoriesRepository categoriesRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private CategoryMapper mapper;

    @Override
    public List<CategoryDTO.Query> getMany() {
        var res = categoriesRepository.findAll();
        return mapper.toCategoryDTO(res);
    }

    @Override
    public CategoryDTO.Query getOne(String id) throws CategoryNotFoundException {
        var res = categoriesRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));
        return mapper.toCategoryDTO(res);

    }

    @Override
    public CategoryDTO.Query create(CategoryDTO.Create dto) throws UserNotFoundException, CategoryInvalidDataException {
        var userRef = usersRepository.getReferenceById(dto.created_by_id());

        var category = mapper.fromCategoryCreateDTO(dto);
        category.setCreatedBy(userRef);
        try {
            var res = categoriesRepository.save(category);

            return mapper.toCategoryDTO(res);
        } catch (DataIntegrityViolationException e) {
            if (CategoryInvalidDataException.isCreatedByNotFound(e)) {
                throw new UserNotFoundException(dto.created_by_id());
            }
            if (CategoryInvalidDataException.isNameConflict(e)) {
                throw new CategoryInvalidDataException.NameConflict();
            }
            throw e;
        }

    }

    @Override
    public List<CategoryDTO.Query> createMany(List<CategoryDTO.Create> dtos) {
        var categories = mapper.fromCategoryCreateDTO(dtos);
        var res = categoriesRepository.saveAll(categories);
        return mapper.toCategoryDTO(res);
    }

    @Override
    public CategoryDTO.Query update(String id, CategoryDTO.Update dto)
            throws CategoryNotFoundException, CategoryInvalidDataException {
        var category = categoriesRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));
        mapper.updateCategory(dto, category);
        try {
            categoriesRepository.save(category);
            return mapper.toCategoryDTO(category);
        } catch (DataIntegrityViolationException e) {
            if (CategoryInvalidDataException.isNameConflict(e)) {
                throw new CategoryInvalidDataException.NameConflict();
            }
            throw e;
        }

    }

    @Override
    public void deleteAll() {
        categoriesRepository.deleteAll();
    }

    @Override
    public void deleteById(String id) {
        categoriesRepository.deleteById(id);
    }

    @Override
    public void deleteManyById(List<String> ids) {
        categoriesRepository.deleteAllById(ids);
    }
}
