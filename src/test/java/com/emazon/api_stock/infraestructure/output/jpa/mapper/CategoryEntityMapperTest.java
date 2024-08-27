package com.emazon.api_stock.infraestructure.output.jpa.mapper;

import com.emazon.api_stock.domain.model.Category;
import com.emazon.api_stock.infraestructure.output.jpa.entity.CategoryEntity;
import com.emazon.api_stock.infraestructure.util.ConstantsTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

class CategoryEntityMapperTest {

    private final  CategoryEntityMapper categoryEntityMapper = Mappers.getMapper( CategoryEntityMapper.class);

    @Test
    void testCategoryToCategoryEntity() {
        Category category = new Category(1, ConstantsTest.FIELD_NAME.getMessage()
                ,ConstantsTest.FIELD_DESCRIPTION.getMessage());

        CategoryEntity categoryEntity = categoryEntityMapper.categoryToCategoryEntity(category);

        Assertions.assertNotNull(categoryEntity);
        Assertions.assertEquals(ConstantsTest.FIELD_NAME.getMessage(), categoryEntity.getName());
        Assertions.assertEquals(ConstantsTest.FIELD_DESCRIPTION.getMessage(), categoryEntity.getDescription());
    }

    @Test
    void testCategoryToCategoryEntity_NullInput() {
        CategoryEntity categoryEntity = categoryEntityMapper.categoryToCategoryEntity(null);

        Assertions.assertNull(categoryEntity);
    }

    @Test
    void testCategoryEntityToCategory() {
        CategoryEntity categoryEntity = new CategoryEntity(1, ConstantsTest.FIELD_NAME.getMessage()
                ,ConstantsTest.FIELD_DESCRIPTION.getMessage());

        List<CategoryEntity> categoryList = new ArrayList<>();
        categoryList.add(categoryEntity);

        List<Category> category = categoryEntityMapper.categoryEntityToCategory(categoryList);

        Assertions.assertNotNull(category);
        Assertions.assertEquals(ConstantsTest.FIELD_NAME.getMessage(), category.get(0).getName());
        Assertions.assertEquals(ConstantsTest.FIELD_DESCRIPTION.getMessage(), category.get(0).getDescription());
    }

    @Test
    void testCategoryEntityToCategory_NullInput() {
        List<Category> category = categoryEntityMapper.categoryEntityToCategory(null);

        Assertions.assertNull(category);
    }
}
