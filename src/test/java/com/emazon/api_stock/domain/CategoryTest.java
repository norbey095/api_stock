package com.emazon.api_stock.domain;

import com.emazon.api_stock.domain.model.Category;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CategoryTest {

    private static final String NAME_INVALID = "This name is way too long and should trigger an exception";
    private static final String DESCRIPTION_INVALID = "This description is way too long and exceeds the maximum " +
            "allowed length of 90 characters...";
    private static final String NAME_VALID = "Toilet";
    private static final String DESCRIPTION_VALID = "Toilet area";
    private static final String MSN_NAME = "The name must be less than 50 characters.";
    private static final String MSN_DESCRIPTION = "The description must be less than 90 characters.";

    @Test
    void shouldThrowExceptionWhenNameExceeds50Characters() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Category(1, NAME_INVALID, DESCRIPTION_VALID);
        });
        assertEquals(MSN_NAME, exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenDescriptionExceeds90Characters() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Category(1, NAME_VALID, DESCRIPTION_INVALID);
        });
        assertEquals(MSN_DESCRIPTION, exception.getMessage());
    }

    @Test
    void shouldCreateCategoryWhenNameAndDescriptionAreValid() {
        Category category = new Category(1, NAME_VALID, DESCRIPTION_VALID);

        assertNotNull(category);
        assertEquals(1, category.getId());
        assertEquals(NAME_VALID, category.getName());
        assertEquals(DESCRIPTION_VALID, category.getDescription());
    }
}
