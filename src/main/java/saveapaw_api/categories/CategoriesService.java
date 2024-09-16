package saveapaw_api.categories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import saveapaw_api.shared.CrudService;
import saveapaw_api.users.UsersRepository;

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
    public CategoryDTO.Query getOne(String id) {
        var res = categoriesRepository.findById(id).orElseThrow(() -> new IllegalAccessError());
        return mapper.toCategoryDTO(res);
    }

    @Override
    public CategoryDTO.Query create(CategoryDTO.Create dto) {
        var userRef = usersRepository.getReferenceById(dto.created_by_id());

        var category = mapper.fromCategoryCreateDTO(dto);
        category.setCreatedBy(userRef);
        var res = categoriesRepository.save(category);

        return mapper.toCategoryDTO(res);
    }

    @Override
    public List<CategoryDTO.Query> createMany(List<CategoryDTO.Create> dtos) {
        var categories = mapper.fromCategoryCreateDTO(dtos);
        var res = categoriesRepository.saveAll(categories);
        return mapper.toCategoryDTO(res);
    }

    @Override
    public CategoryDTO.Query update(String id, CategoryDTO.Update dto) {
        var category = categoriesRepository.findById(id).orElseThrow(() -> new IllegalAccessError());
        mapper.updateCategory(dto, category);
        return mapper.toCategoryDTO(category);
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
