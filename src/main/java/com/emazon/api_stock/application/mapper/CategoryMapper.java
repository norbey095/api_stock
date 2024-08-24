package com.emazon.api_stock.application.mapper;

import com.emazon.api_stock.application.dto.CategoryDto;
import com.emazon.api_stock.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "categoryDto.name", target = "name")
    @Mapping(source = "categoryDto.description", target = "description")
    Category categoryDtoToCategory(CategoryDto categoryDto);

    List<CategoryDto> toCategoryDtoList(List<Category> categorys);

}
