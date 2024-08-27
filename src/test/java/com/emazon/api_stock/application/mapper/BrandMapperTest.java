package com.emazon.api_stock.application.mapper;

import com.emazon.api_stock.application.dto.BrandDto;
import com.emazon.api_stock.application.util.ConstantsTest;
import com.emazon.api_stock.domain.model.Brand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class BrandMapperTest {

    private final BrandMapper brandMapper = Mappers.getMapper(BrandMapper.class);

    @Test
    void testBrandDtoToBrand() {
        BrandDto brandDto = new BrandDto();
        brandDto.setName(ConstantsTest.FIELD_NAME.getMessage());
        brandDto.setDescription(ConstantsTest.FIELD_BRAND_DESCRIPTION.getMessage());

        Brand brand = brandMapper.brandDtoToBrand(brandDto);

        Assertions.assertNotNull(brand);
        Assertions.assertEquals(ConstantsTest.FIELD_NAME.getMessage(), brand.getName());
        Assertions.assertEquals(ConstantsTest.FIELD_BRAND_DESCRIPTION.getMessage(), brand.getDescription());
    }

    @Test
    void testBrandDtoToBrand_NullInput() {
        Brand brand = brandMapper.brandDtoToBrand(null);

        Assertions.assertNull(brand);
    }
}
