package com.emazon.api_stock.application.mapper;

import com.emazon.api_stock.application.dto.CategoryDto;
import com.emazon.api_stock.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(source = "CategoryDto.name", target = "name")
    @Mapping(source = "CategoryDto.description", target = "description")
    Category categoryDtoToCategory(CategoryDto categoryDto);

    @Mapping(source = "Category.name", target = "name")
    @Mapping(source = "Category.description", target = "description")
    CategoryDto categoryToCategoryDto(Category category);

}
