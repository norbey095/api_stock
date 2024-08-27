package com.emazon.api_stock.domain.model;

import com.emazon.api_stock.domain.util.ConstantsTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class BrandTest {

    @Test
    void shouldCreateBrandWhenNameAndDescriptionAreValid() {
        Brand brand = new Brand(1, ConstantsTest.FIELD_NAME.getMessage(),
                ConstantsTest.FIELD_BRAND_DESCRIPTION.getMessage());

        assertNotNull(brand);
        assertEquals(1, brand.getId());
        assertEquals(ConstantsTest.FIELD_NAME.getMessage(), brand.getName());
        assertEquals(ConstantsTest.FIELD_BRAND_DESCRIPTION.getMessage(), brand.getDescription());
    }
}
