package com.emazon.api_stock.application.mapper;

import com.emazon.api_stock.application.dto.PaginationDto;
import com.emazon.api_stock.application.dto.category.CategoryRequestDto;
import com.emazon.api_stock.application.dto.category.CategoryResponseDto;
import com.emazon.api_stock.application.util.ConstantsMapper;
import com.emazon.api_stock.domain.model.Category;
import com.emazon.api_stock.domain.model.Pagination;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(target = ConstantsMapper.ID, ignore = true)
    Category categoryDtoToCategory(CategoryRequestDto categoryDto);

    PaginationDto<CategoryResponseDto> toCategoryDtoList(Pagination<Category> categorys);

}
