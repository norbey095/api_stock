package com.emazon.api_stock.domain.model;

import com.emazon.api_stock.domain.util.ConstantsTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CategoryTest {

    @Test
    void shouldCreateCategoryWhenNameAndDescriptionAreValid() {
        Category category = new Category(1, ConstantsTest.FIELD_NAME.getMessage(),
                ConstantsTest.FIELD_DESCRIPTION.getMessage());

        assertNotNull(category);
        assertEquals(1, category.getId());
        assertEquals(ConstantsTest.FIELD_NAME.getMessage(), category.getName());
        assertEquals(ConstantsTest.FIELD_DESCRIPTION.getMessage(), category.getDescription());
    }
}
