package com.emazon.api_stock.domain.model;

import com.emazon.api_stock.domain.util.ConstantsDomain;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CategoryTest {

    @Test
    void shouldCreateCategoryWhenNameAndDescriptionAreValid() {
        Category category = new Category(ConstantsDomain.VALUE_1, ConstantsDomain.FIELD_NAME,
                ConstantsDomain.FIELD_DESCRIPTION);

        assertNotNull(category);
        assertEquals(ConstantsDomain.VALUE_1, category.getId());
        assertEquals(ConstantsDomain.FIELD_NAME, category.getName());
        assertEquals(ConstantsDomain.FIELD_DESCRIPTION, category.getDescription());
    }

}
