package saveapaw_api.categories;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CategoryMapper {
    Category fromCategoryCreateDTO(CategoryDTO.Create dto);

    List<Category> fromCategoryCreateDTO(List<CategoryDTO.Create> dto);

    CategoryDTO.Query toCategoryDTO(Category category);

    List<CategoryDTO.Query> toCategoryDTO(List<Category> category);

    void updateCategory(CategoryDTO.Update dto, @MappingTarget Category category);
}
