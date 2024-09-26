package com.emazon.api_stock.infraestructure.output.mapper;

import com.emazon.api_stock.domain.model.Category;
import com.emazon.api_stock.infraestructure.output.entity.CategoryEntity;
import com.emazon.api_stock.infraestructure.util.ConstantsInfraestructure;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

class CategoryEntityMapperTest {

    private final  CategoryEntityMapper categoryEntityMapper = Mappers.getMapper( CategoryEntityMapper.class);

    @Test
    void testCategoryToCategoryEntity() {
        Category category = new Category(ConstantsInfraestructure.VALUE_1, ConstantsInfraestructure.FIELD_NAME
                , ConstantsInfraestructure.FIELD_DESCRIPTION);

        CategoryEntity categoryEntity = categoryEntityMapper.categoryToCategoryEntity(category);

        Assertions.assertNotNull(categoryEntity);
        Assertions.assertEquals(ConstantsInfraestructure.FIELD_NAME, categoryEntity.getName());
        Assertions.assertEquals(ConstantsInfraestructure.FIELD_DESCRIPTION, categoryEntity.getDescription());
    }

    @Test
    void testCategoryToCategoryEntity_NullInput() {
        CategoryEntity categoryEntity = categoryEntityMapper.categoryToCategoryEntity(null);

        Assertions.assertNull(categoryEntity);
    }

    @Test
    void testCategoryEntityToCategory() {
        CategoryEntity categoryEntity = new CategoryEntity(ConstantsInfraestructure.VALUE_1
                , ConstantsInfraestructure.FIELD_NAME
                , ConstantsInfraestructure.FIELD_DESCRIPTION,null);

        List<CategoryEntity> categoryList = new ArrayList<>();
        categoryList.add(categoryEntity);

        List<Category> category = categoryEntityMapper.categoryEntityToCategory(categoryList);

        Assertions.assertNotNull(category);
        Assertions.assertEquals(ConstantsInfraestructure.FIELD_NAME
                , category.get(ConstantsInfraestructure.VALUE_0).getName());
        Assertions.assertEquals(ConstantsInfraestructure.FIELD_DESCRIPTION
                , category.get(ConstantsInfraestructure.VALUE_0).getDescription());
    }

    @Test
    void testCategoryEntityToCategory_NullInput() {
        List<Category> category = categoryEntityMapper.categoryEntityToCategory(null);

        Assertions.assertNull(category);
    }
}
