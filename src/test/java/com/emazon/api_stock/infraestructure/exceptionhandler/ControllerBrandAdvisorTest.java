package com.emazon.api_stock.infraestructure.exceptionhandler;

import com.emazon.api_stock.application.dto.BrandDto;
import com.emazon.api_stock.application.handler.brand.IBrandHandler;
import com.emazon.api_stock.domain.exception.brand.BrandAlreadyExistsException;
import com.emazon.api_stock.domain.exception.brand.InvalidBrandDescriptionException;
import com.emazon.api_stock.domain.exception.brand.InvalidBrandNameException;
import com.emazon.api_stock.infraestructure.input.rest.BrandRestController;
import com.emazon.api_stock.infraestructure.util.ConstantsTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(controllers = {BrandRestController.class, ControllerBrandAdvisor.class})
class ControllerBrandAdvisorTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IBrandHandler brandHandler;

    @Test
    void whenBrandAlreadyExistsException_thenReturnsConflict() throws Exception {
        Mockito.doThrow(new BrandAlreadyExistsException()).when(brandHandler)
                .saveBrand(Mockito.any( BrandDto.class));

        mockMvc.perform(MockMvcRequestBuilders.post(ConstantsTest.URL_CREATE_BRAND.getMessage())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ConstantsTest.JSON_BRAND_REQUEST.getMessage()))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value(ConstantsTest.BRAND_EXISTS.getMessage()));
    }

    @Test
    void whenInvalidBrandNameException_thenReturnsBadRequest() throws Exception {
        Mockito.doThrow(new InvalidBrandNameException(ConstantsTest.INVALID_NAME.getMessage()))
                .when(brandHandler)
                .saveBrand(Mockito.any(BrandDto.class));

        mockMvc.perform(MockMvcRequestBuilders.post(ConstantsTest.URL_CREATE_BRAND.getMessage())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ConstantsTest.JSON_BRAND_REQUEST.getMessage()))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value(ConstantsTest.INVALID_NAME.getMessage()));
    }

    @Test
    void whenInvalidBrandDescriptionException_thenReturnsBadRequest() throws Exception {
        Mockito.doThrow(new InvalidBrandDescriptionException(ConstantsTest.INVALID_DESCRIPTION.getMessage()))
                .when(brandHandler)
                .saveBrand(Mockito.any(BrandDto.class));

        mockMvc.perform(MockMvcRequestBuilders.post(ConstantsTest.URL_CREATE_BRAND.getMessage())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ConstantsTest.JSON_BRAND_REQUEST.getMessage()))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value(ConstantsTest.INVALID_DESCRIPTION.getMessage()));
    }
}