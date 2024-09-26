package com.emazon.api_stock.application.mapper;

import com.emazon.api_stock.application.dto.brand.BrandRequestDto;
import com.emazon.api_stock.application.dto.brand.BrandResponseDto;
import com.emazon.api_stock.application.util.ConstantsApplication;
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
        brandDto.setName(ConstantsApplication.FIELD_NAME);
        brandDto.setDescription(ConstantsApplication.FIELD_BRAND_DESCRIPTION);

        Brand brand = brandMapper.brandDtoToBrand(brandDto);

        Assertions.assertNotNull(brand);
        Assertions.assertEquals(brandDto.getName(), brand.getName());
        Assertions.assertEquals(brandDto.getDescription(), brand.getDescription());
    }

    @Test
    void testBrandDtoToBrand_NullInput() {
        Brand brand = brandMapper.brandDtoToBrand(null);

        Assertions.assertNull(brand);
    }

    @Test
    void testToBrandDtoList() {
        Brand brand = new Brand(ConstantsApplication.NUMBER_1, ConstantsApplication.FIELD_NAME
                , ConstantsApplication.FIELD_BRAND_DESCRIPTION);

        List<Brand> brandList = new ArrayList<>();
        brandList.add(brand);

        List<BrandResponseDto> brandDtoList = brandMapper.toBrandDtoList(brandList);

        Assertions.assertNotNull(brandDtoList);
        Assertions.assertEquals(ConstantsApplication.FIELD_NAME, brandDtoList.get(ConstantsApplication.NUMBER_0)
                .getName());
        Assertions.assertEquals(ConstantsApplication.FIELD_BRAND_DESCRIPTION, brandDtoList.get(ConstantsApplication
                        .NUMBER_0)
                .getDescription());
    }

    @Test
    void testToBrandDtoList_NullInput() {
        List<BrandResponseDto> brandDtos = brandMapper.toBrandDtoList(null);

        Assertions.assertNull(brandDtos);
    }
}
