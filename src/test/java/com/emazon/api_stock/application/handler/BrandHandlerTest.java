package com.emazon.api_stock.application.handler;

import com.emazon.api_stock.application.dto.BrandDto;
import com.emazon.api_stock.application.handler.brand.BrandHandler;
import com.emazon.api_stock.application.mapper.BrandMapper;
import com.emazon.api_stock.application.util.ConstantsTest;
import com.emazon.api_stock.domain.api.IBrandServicePort;
import com.emazon.api_stock.domain.model.Brand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class BrandHandlerTest {

    @InjectMocks
    private BrandHandler brandHandler;

    @Mock
    private IBrandServicePort brandServicePort;

    @Mock
    private BrandMapper brandMapper;

    private BrandDto brandDto;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        brandDto = new BrandDto();
        brandDto.setName(ConstantsTest.FIELD_NAME.getMessage());
        brandDto.setDescription(ConstantsTest.FIELD_BRAND_DESCRIPTION.getMessage());
    }

    @Test
    void shouldSaveBrand() {
        Brand brand = new Brand(null,ConstantsTest.FIELD_NAME.getMessage()
                ,ConstantsTest.FIELD_BRAND_DESCRIPTION.getMessage());
        when(brandMapper.brandDtoToBrand(brandDto)).thenReturn(brand);

        brandHandler.saveBrand(brandDto);


        verify(brandMapper).brandDtoToBrand(brandDto);
        verify(brandServicePort).saveBrand(brand);
    }

    @Test
    void shouldGetAllCategory() {
        List<BrandDto> brandDtoList = new ArrayList<>();
        brandDtoList.add(brandDto);

        Brand brand = new Brand(null,ConstantsTest.FIELD_NAME.getMessage()
                ,ConstantsTest.FIELD_BRAND_DESCRIPTION.getMessage());
        List<Brand> brandList = new ArrayList<>();
        brandList.add(brand);

        when(brandMapper.toBrandDtoList(brandList)).thenReturn(brandDtoList);
        when(brandServicePort.getAllBrands(1,1,false)).thenReturn(brandList);

        brandHandler.getAllBrands(1,1,false);


        verify(brandMapper).toBrandDtoList(brandList);
        verify(brandServicePort).getAllBrands(1,1,false);
    }
}
