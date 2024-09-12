package com.emazon.api_stock.infraestructure.output.jpa.mapper;

import com.emazon.api_stock.domain.model.Brand;
import com.emazon.api_stock.infraestructure.output.jpa.entity.BrandEntity;
import com.emazon.api_stock.infraestructure.util.ConstantsInfraestructure;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

class BrandEntityMapperTest {

    private final  BrandEntityMapper brandEntityMapper = Mappers.getMapper( BrandEntityMapper.class);

    @Test
    void testBrandToBrandEntity() {
        Brand brand = new Brand(ConstantsInfraestructure.VALUE_1, ConstantsInfraestructure.FIELD_NAME
                , ConstantsInfraestructure.FIELD_BRAND_DESCRIPTION);

        BrandEntity brandEntity = brandEntityMapper.brandToBrandEntity(brand);

        Assertions.assertNotNull(brandEntity);
        Assertions.assertEquals(ConstantsInfraestructure.FIELD_NAME, brandEntity.getName());
        Assertions.assertEquals(ConstantsInfraestructure.FIELD_BRAND_DESCRIPTION, brandEntity.getDescription());
    }

    @Test
    void testBrandToBrandEntity_NullInput() {
        BrandEntity brandEntity = brandEntityMapper.brandToBrandEntity(null);

        Assertions.assertNull(brandEntity);
    }

    @Test
    void testBrandEntityToBrand() {
        BrandEntity brandEntity = new BrandEntity(ConstantsInfraestructure.VALUE_1, ConstantsInfraestructure.FIELD_NAME
                , ConstantsInfraestructure.FIELD_BRAND_DESCRIPTION,null);

        List<BrandEntity> brandList = new ArrayList<>();
        brandList.add(brandEntity);

        List<Brand> brand = brandEntityMapper.brandEntityToBrand(brandList);

        Assertions.assertNotNull(brand);
        Assertions.assertEquals(ConstantsInfraestructure.FIELD_NAME, brand.get(ConstantsInfraestructure.VALUE_0)
                .getName());
        Assertions.assertEquals(ConstantsInfraestructure.FIELD_BRAND_DESCRIPTION, brand.get(ConstantsInfraestructure
                .VALUE_0).getDescription());
    }

    @Test
    void testBrandEntityToBrand_NullInput() {
        List<Brand> brand = brandEntityMapper.brandEntityToBrand(null);

        Assertions.assertNull(brand);
    }
}
