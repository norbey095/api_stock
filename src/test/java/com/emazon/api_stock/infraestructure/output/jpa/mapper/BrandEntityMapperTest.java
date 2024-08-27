package com.emazon.api_stock.infraestructure.output.jpa.mapper;

import com.emazon.api_stock.domain.model.Brand;
import com.emazon.api_stock.infraestructure.output.jpa.entity.BrandEntity;
import com.emazon.api_stock.infraestructure.util.ConstantsTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class BrandEntityMapperTest {

    private final  BrandEntityMapper brandEntityMapper = Mappers.getMapper( BrandEntityMapper.class);

    @Test
    void testBrandToBrandEntity() {
        Brand brand = new Brand(1, ConstantsTest.FIELD_NAME.getMessage()
                ,ConstantsTest.FIELD_BRAND_DESCRIPTION.getMessage());

        BrandEntity brandEntity = brandEntityMapper.brandToBrandEntity(brand);

        Assertions.assertNotNull(brandEntity);
        Assertions.assertEquals(ConstantsTest.FIELD_NAME.getMessage(), brandEntity.getName());
        Assertions.assertEquals(ConstantsTest.FIELD_BRAND_DESCRIPTION.getMessage(), brandEntity.getDescription());
    }

    @Test
    void testBrandToBrandEntity_NullInput() {
        BrandEntity brandEntity = brandEntityMapper.brandToBrandEntity(null);

        Assertions.assertNull(brandEntity);
    }
}
