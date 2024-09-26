package com.emazon.api_stock.application.mapper;

import com.emazon.api_stock.application.dto.category.CategoryRequestDto;
import com.emazon.api_stock.application.dto.category.CategoryResponseDto;
import com.emazon.api_stock.application.util.ConstantsApplication;
import com.emazon.api_stock.domain.model.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

class CategoryMapperTest {

    private final CategoryMapper categoryMapper = Mappers.getMapper(CategoryMapper.class);

    @Test
    void testCategoryDtoToCategory() {
        CategoryRequestDto categoryDto = new CategoryRequestDto();
        categoryDto.setName(ConstantsApplication.FIELD_NAME);
        categoryDto.setDescription(ConstantsApplication.FIELD_DESCRIPTION);

        Category category = categoryMapper.categoryDtoToCategory(categoryDto);

        Assertions.assertNotNull(category);
        Assertions.assertEquals(categoryDto.getName(), category.getName());
        Assertions.assertEquals(categoryDto.getDescription(), category.getDescription());
    }

    @Test
    void testCategoryDtoToCategory_NullInput() {
        Category category = categoryMapper.categoryDtoToCategory(null);

        Assertions.assertNull(category);
    }

    @Test
    void testToCategoryDtoList() {
        Category category = new Category(ConstantsApplication.NUMBER_1, ConstantsApplication.FIELD_NAME
                , ConstantsApplication.FIELD_DESCRIPTION);

        List<Category> categoryList = new ArrayList<>();
        categoryList.add(category);

        List<CategoryResponseDto> categoryDtoList = categoryMapper.toCategoryDtoList(categoryList);

        Assertions.assertNotNull(categoryDtoList);
        Assertions.assertEquals(ConstantsApplication.FIELD_NAME, categoryDtoList.get(ConstantsApplication.NUMBER_0)
                .getName());
        Assertions.assertEquals(ConstantsApplication.FIELD_DESCRIPTION, categoryDtoList
                .get(ConstantsApplication.NUMBER_0).getDescription());
    }

    @Test
    void testToCategoryDtoList_NullInput() {
        List<CategoryResponseDto> categoryDtos = categoryMapper.toCategoryDtoList(null);

        Assertions.assertNull(categoryDtos);
    }
}
