package com.emazon.api_stock.application.mapper;

import com.emazon.api_stock.application.dto.CategoryDto;
import com.emazon.api_stock.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(source = "categoryDto.name", target = "name")
    @Mapping(source = "categoryDto.description", target = "description")
    Category categoryDtoToCategory(CategoryDto categoryDto);

}
