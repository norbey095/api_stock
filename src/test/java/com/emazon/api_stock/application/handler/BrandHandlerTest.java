package com.emazon.api_stock.application.handler;

import com.emazon.api_stock.application.dto.brand.BrandRequestDto;
import com.emazon.api_stock.application.dto.brand.BrandResponseDto;
import com.emazon.api_stock.application.handler.brand.BrandHandler;
import com.emazon.api_stock.application.mapper.BrandMapper;
import com.emazon.api_stock.application.util.ConstantsApplication;
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

    private BrandRequestDto brandRequestDto;

    private BrandResponseDto brandResponseDto;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        brandRequestDto = new BrandRequestDto();
        brandRequestDto.setName(ConstantsApplication.FIELD_NAME);
        brandRequestDto.setDescription(ConstantsApplication.FIELD_BRAND_DESCRIPTION);

        brandResponseDto = new BrandResponseDto();
        brandResponseDto.setName(ConstantsApplication.FIELD_NAME);
        brandResponseDto.setDescription(ConstantsApplication.FIELD_BRAND_DESCRIPTION);
    }

    @Test
    void shouldSaveBrand() {
        Brand brand = new Brand(null, ConstantsApplication.FIELD_NAME
                , ConstantsApplication.FIELD_BRAND_DESCRIPTION);
        when(brandMapper.brandDtoToBrand(brandRequestDto)).thenReturn(brand);

        brandHandler.saveBrand(brandRequestDto);


        verify(brandMapper).brandDtoToBrand(brandRequestDto);
        verify(brandServicePort).saveBrand(brand);
    }

    @Test
    void shouldGetAllCategory() {
        List<BrandResponseDto> brandResponseDtoList = new ArrayList<>();
        brandResponseDtoList.add(brandResponseDto);

        Brand brand = new Brand(null, ConstantsApplication.FIELD_NAME
                , ConstantsApplication.FIELD_BRAND_DESCRIPTION);
        List<Brand> brandList = new ArrayList<>();
        brandList.add(brand);

        when(brandMapper.toBrandDtoList(brandList)).thenReturn(brandResponseDtoList);
        when(brandServicePort.getAllBrands(ConstantsApplication.VALOR_1, ConstantsApplication.VALOR_1, ConstantsApplication.VALUE_FALSE))
                .thenReturn(brandList);

        brandHandler.getAllBrands(ConstantsApplication.VALOR_1, ConstantsApplication.VALOR_1, ConstantsApplication.VALUE_FALSE);


        verify(brandMapper).toBrandDtoList(brandList);
        verify(brandServicePort).getAllBrands(ConstantsApplication.VALOR_1, ConstantsApplication.VALOR_1, ConstantsApplication.VALUE_FALSE);
    }
}
