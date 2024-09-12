package com.emazon.api_stock.domain.model;

import com.emazon.api_stock.domain.util.ConstantsDomain;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class BrandTest {

    @Test
    void shouldCreateBrandWhenNameAndDescriptionAreValid() {
        Brand brand = new Brand(ConstantsDomain.VALUE_1, ConstantsDomain.FIELD_NAME,
                ConstantsDomain.FIELD_BRAND_DESCRIPTION);

        assertNotNull(brand);
        assertEquals(ConstantsDomain.VALUE_1, brand.getId());
        assertEquals(ConstantsDomain.FIELD_NAME, brand.getName());
        assertEquals(ConstantsDomain.FIELD_BRAND_DESCRIPTION, brand.getDescription());
    }
}
