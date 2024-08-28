package com.emazon.api_stock.application.mapper;

import com.emazon.api_stock.application.dto.category.CategoryRequestDto;
import com.emazon.api_stock.application.dto.category.CategoryResponseDto;
import com.emazon.api_stock.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "categoryDto.name", target = "name")
    @Mapping(source = "categoryDto.description", target = "description")
    Category categoryDtoToCategory(CategoryRequestDto categoryDto);

    List<CategoryResponseDto> toCategoryDtoList(List<Category> categorys);

}
