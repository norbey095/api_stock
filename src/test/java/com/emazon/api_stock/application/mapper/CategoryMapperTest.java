package com.emazon.api_stock.application.mapper;

import com.emazon.api_stock.application.dto.CategoryDto;
import com.emazon.api_stock.application.util.ConstantsTest;
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
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName(ConstantsTest.FIELD_NAME.getMessage());
        categoryDto.setDescription(ConstantsTest.FIELD_DESCRIPTION.getMessage());

        Category category = categoryMapper.categoryDtoToCategory(categoryDto);

        Assertions.assertNotNull(category);
        Assertions.assertEquals(ConstantsTest.FIELD_NAME.getMessage(), category.getName());
        Assertions.assertEquals(ConstantsTest.FIELD_DESCRIPTION.getMessage(), category.getDescription());
    }

    @Test
    void testCategoryDtoToCategory_NullInput() {
        Category category = categoryMapper.categoryDtoToCategory(null);

        Assertions.assertNull(category);
    }

    @Test
    void testToCategoryDtoList() {
        Category category = new Category(1, ConstantsTest.FIELD_NAME.getMessage()
                , ConstantsTest.FIELD_DESCRIPTION.getMessage());

        List<Category> categoryList = new ArrayList<>();
        categoryList.add(category);

        List<CategoryDto> categoryDtoList = categoryMapper.toCategoryDtoList(categoryList);

        Assertions.assertNotNull(categoryDtoList);
        Assertions.assertEquals(ConstantsTest.FIELD_NAME.getMessage(), categoryDtoList.get(0).getName());
        Assertions.assertEquals(ConstantsTest.FIELD_DESCRIPTION.getMessage(), categoryDtoList.get(0).getDescription());
    }

    @Test
    void testToCategoryDtoList_NullInput() {
        List<CategoryDto> categoryDtos = categoryMapper.toCategoryDtoList(null);

        Assertions.assertNull(categoryDtos);
    }
}
