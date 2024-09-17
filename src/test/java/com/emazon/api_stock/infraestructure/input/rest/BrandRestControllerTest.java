package com.emazon.api_stock.infraestructure.input.rest;

import com.emazon.api_stock.application.dto.brand.BrandRequestDto;
import com.emazon.api_stock.application.dto.brand.BrandResponseDto;
import com.emazon.api_stock.application.handler.brand.IBrandHandler;
import com.emazon.api_stock.infraestructure.util.ConstantsInfraestructure;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
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
        brandDto.setName(ConstantsInfraestructure.FIELD_NAME);
        brandDto.setDescription(ConstantsInfraestructure.FIELD_BRAND_DESCRIPTION);

        brandResponseDtoDto = new BrandResponseDto();
        brandResponseDtoDto.setName(ConstantsInfraestructure.FIELD_NAME);
        brandResponseDtoDto.setDescription(ConstantsInfraestructure.FIELD_BRAND_DESCRIPTION);
    }

    @Test
    @WithMockUser(username = ConstantsInfraestructure.USER_NAME, roles = {ConstantsInfraestructure.ADMIN})
    void createBrand_ShouldReturnStatusCreated() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(ConstantsInfraestructure.URL_CREATE_BRAND)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(brandDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        Mockito.verify(brandHandler, Mockito.times(ConstantsInfraestructure.VALUE_1)).saveBrand(brandDto);
    }

    @Test
    @WithMockUser(username = ConstantsInfraestructure.USER_NAME, roles = {ConstantsInfraestructure.ADMIN})
    void getAllBrand_ShouldReturnBrandDto() throws Exception {
        List<BrandResponseDto> brandResponseDtoList = new ArrayList<>();
        brandResponseDtoList.add(brandResponseDtoDto);
        Mockito.when(brandHandler.getAllBrands(ConstantsInfraestructure.VALUE_1, ConstantsInfraestructure.VALUE_1
                ,ConstantsInfraestructure.VALUE_FALSE)).thenReturn(brandResponseDtoList);


        mockMvc.perform(MockMvcRequestBuilders.get(ConstantsInfraestructure.URL_GET_BRAND)
                        .param(ConstantsInfraestructure.PAGE, ConstantsInfraestructure.VALUE_UNO)
                        .param(ConstantsInfraestructure.SIZE, ConstantsInfraestructure.VALUE_UNO)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath(ConstantsInfraestructure.MESSAGESS_NAME)
                        .value(ConstantsInfraestructure.FIELD_NAME))
                .andExpect(MockMvcResultMatchers.jsonPath(ConstantsInfraestructure.MESSAGESS_DESCRIPTION)
                        .value(ConstantsInfraestructure.FIELD_BRAND_DESCRIPTION));

        Mockito.verify(brandHandler, Mockito.times(ConstantsInfraestructure.VALUE_1))
                .getAllBrands(ConstantsInfraestructure.VALUE_1,ConstantsInfraestructure.VALUE_1
                        ,ConstantsInfraestructure.VALUE_FALSE);
    }
}