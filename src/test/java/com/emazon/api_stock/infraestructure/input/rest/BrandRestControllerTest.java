package com.emazon.api_stock.infraestructure.input.rest;

import com.emazon.api_stock.application.dto.BrandDto;
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

@WebMvcTest(BrandRestController.class)
class BrandRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IBrandHandler brandHandler;

    @Autowired
    private ObjectMapper objectMapper;

    private BrandDto brandDto;

    @BeforeEach
    void setUp() {
        brandDto = new BrandDto();
        brandDto.setName(ConstantsTest.FIELD_NAME.getMessage());
        brandDto.setDescription(ConstantsTest.FIELD_BRAND_DESCRIPTION.getMessage());
    }

    @Test
    void createBrand_ShouldReturnStatusCreated() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(ConstantsTest.URL_CREATE_BRAND.getMessage())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(brandDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        Mockito.verify(brandHandler, Mockito.times(1)).saveBrand(brandDto);
    }
}