package com.emazon.api_stock.application.mapper;

import com.emazon.api_stock.application.dto.brand.BrandRequestDto;
import com.emazon.api_stock.application.dto.brand.BrandResponseDto;
import com.emazon.api_stock.application.util.ConstantsTest;
import com.emazon.api_stock.domain.model.Brand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

class BrandMapperTest {

    private final BrandMapper brandMapper = Mappers.getMapper(BrandMapper.class);

    @Test
    void testBrandDtoToBrand() {
        BrandRequestDto brandDto = new BrandRequestDto();
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

    @Test
    void testToBrandDtoList() {
        Brand brand = new Brand(1, ConstantsTest.FIELD_NAME.getMessage()
                , ConstantsTest.FIELD_BRAND_DESCRIPTION.getMessage());

        List<Brand> brandList = new ArrayList<>();
        brandList.add(brand);

        List<BrandResponseDto> brandDtoList = brandMapper.toBrandDtoList(brandList);

        Assertions.assertNotNull(brandDtoList);
        Assertions.assertEquals(ConstantsTest.FIELD_NAME.getMessage(), brandDtoList.get(0).getName());
        Assertions.assertEquals(ConstantsTest.FIELD_BRAND_DESCRIPTION.getMessage(), brandDtoList.get(0).getDescription());
    }

    @Test
    void testToBrandDtoList_NullInput() {
        List<BrandResponseDto> brandDtos = brandMapper.toBrandDtoList(null);

        Assertions.assertNull(brandDtos);
    }
}
