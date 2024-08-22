package com.emazon.api_stock.infraestructure.output.jpa.mapper;

import com.emazon.api_stock.domain.model.Category;
import com.emazon.api_stock.infraestructure.output.jpa.entity.CategoryEntity;
import com.emazon.api_stock.infraestructure.util.ConstantsTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class CategoryEntityMapperTest {

    private final  CategoryEntityMapper categoryEntityMapper = Mappers.getMapper( CategoryEntityMapper.class);

    @Test
    void testCategoryToCategoryEntity() {
        Category category = new Category(1, ConstantsTest.FIELD_NAME.getMessage()
                ,ConstantsTest.FIELD_DESCRIPTION.getMessage());

        CategoryEntity categoryEntity = categoryEntityMapper.categoyToCategoryEntity(category);

        Assertions.assertNotNull(categoryEntity);
        Assertions.assertEquals(ConstantsTest.FIELD_NAME.getMessage(), categoryEntity.getName());
        Assertions.assertEquals(ConstantsTest.FIELD_DESCRIPTION.getMessage(), categoryEntity.getDescription());
    }

    @Test
    void testCategoryToCategoryEntity_NullInput() {
        CategoryEntity categoryEntity = categoryEntityMapper.categoyToCategoryEntity(null);

        Assertions.assertNull(categoryEntity);
    }
}
