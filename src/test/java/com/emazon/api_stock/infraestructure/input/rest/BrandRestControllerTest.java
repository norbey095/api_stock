package com.emazon.api_stock.infraestructure.input.rest;

import com.emazon.api_stock.application.dto.brand.BrandRequestDto;
import com.emazon.api_stock.application.dto.brand.BrandResponseDto;
import com.emazon.api_stock.application.handler.brand.IBrandHandler;
import com.emazon.api_stock.infraestructure.util.ConstantsTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

@WebMvcTest(BrandRestController.class)
class BrandRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IBrandHandler brandHandler;

    @Autowired
    private ObjectMapper objectMapper;

    private BrandRequestDto brandDto;

    private BrandResponseDto brandResponseDtoDto;

    @BeforeEach
    void setUp() {
        brandDto = new BrandRequestDto();
        brandDto.setName(ConstantsTest.FIELD_NAME.getMessage());
        brandDto.setDescription(ConstantsTest.FIELD_BRAND_DESCRIPTION.getMessage());

        brandResponseDtoDto = new BrandResponseDto();
        brandResponseDtoDto.setName(ConstantsTest.FIELD_NAME.getMessage());
        brandResponseDtoDto.setDescription(ConstantsTest.FIELD_BRAND_DESCRIPTION.getMessage());
    }

    @Test
    void createBrand_ShouldReturnStatusCreated() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(ConstantsTest.URL_CREATE_BRAND.getMessage())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(brandDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        Mockito.verify(brandHandler, Mockito.times(1)).saveBrand(brandDto);
    }

    @Test
    void getAllBrand_ShouldReturnBrandDto() throws Exception {
        List<BrandResponseDto> brandResponseDtoList = new ArrayList<>();
        brandResponseDtoList.add(brandResponseDtoDto);
        Mockito.when(brandHandler.getAllBrands(1, 1,false)).thenReturn(brandResponseDtoList);


        mockMvc.perform(MockMvcRequestBuilders.get(ConstantsTest.URL_GET_BRAND.getMessage())
                        .param("page", "1")
                        .param("size", "1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name")
                        .value(ConstantsTest.FIELD_NAME.getMessage()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].description")
                        .value(ConstantsTest.FIELD_BRAND_DESCRIPTION.getMessage()));

        Mockito.verify(brandHandler, Mockito.times(1))
                .getAllBrands(1,1,false);
    }
}