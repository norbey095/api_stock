package com.emazon.api_stock.infraestructure.output.mapper;

import com.emazon.api_stock.domain.model.Category;
import com.emazon.api_stock.infraestructure.output.entity.CategoryEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryEntityMapper {

    CategoryEntity categoryToCategoryEntity(Category category);

    List<Category> categoryEntityToCategory(List<CategoryEntity> categoryEntity);

}
