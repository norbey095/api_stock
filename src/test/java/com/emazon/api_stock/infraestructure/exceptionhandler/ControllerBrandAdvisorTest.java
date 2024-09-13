package com.emazon.api_stock.infraestructure.exceptionhandler;

import com.emazon.api_stock.application.dto.brand.BrandRequestDto;
import com.emazon.api_stock.application.handler.brand.IBrandHandler;
import com.emazon.api_stock.domain.exception.brand.BrandAlreadyExistsException;
import com.emazon.api_stock.domain.exception.brand.InvalidBrandDescriptionException;
import com.emazon.api_stock.domain.exception.brand.InvalidBrandNameException;
import com.emazon.api_stock.infraestructure.util.ConstantsInfraestructure;
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

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class ControllerBrandAdvisorTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IBrandHandler brandHandler;

    @Test
    @WithMockUser(username = ConstantsInfraestructure.USER_NAME, roles = {ConstantsInfraestructure.ADMIN})
    void whenBrandAlreadyExistsException_thenReturnsConflict() throws Exception {
        Mockito.doThrow(new BrandAlreadyExistsException()).when(brandHandler)
                .saveBrand(Mockito.any(BrandRequestDto.class));

        mockMvc.perform(MockMvcRequestBuilders.post(ConstantsInfraestructure.URL_CREATE_BRAND)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ConstantsInfraestructure.JSON_BRAND_REQUEST))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andExpect(MockMvcResultMatchers.jsonPath(ConstantsInfraestructure.MESSAGESS)
                        .value(ConstantsInfraestructure.BRAND_EXISTS));
    }

    @Test
    @WithMockUser(username = ConstantsInfraestructure.USER_NAME, roles = {ConstantsInfraestructure.ADMIN})
    void whenInvalidBrandNameException_thenReturnsBadRequest() throws Exception {
        Mockito.doThrow(new InvalidBrandNameException(ConstantsInfraestructure.INVALID_NAME))
                .when(brandHandler)
                .saveBrand(Mockito.any(BrandRequestDto.class));

        mockMvc.perform(MockMvcRequestBuilders.post(ConstantsInfraestructure.URL_CREATE_BRAND)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ConstantsInfraestructure.JSON_BRAND_REQUEST))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath(ConstantsInfraestructure.MESSAGESS)
                        .value(ConstantsInfraestructure.INVALID_NAME));
    }

    @Test
    @WithMockUser(username = ConstantsInfraestructure.USER_NAME, roles = {ConstantsInfraestructure.ADMIN})
    void whenInvalidBrandDescriptionException_thenReturnsBadRequest() throws Exception {
        Mockito.doThrow(new InvalidBrandDescriptionException(ConstantsInfraestructure.INVALID_DESCRIPTION))
                .when(brandHandler)
                .saveBrand(Mockito.any(BrandRequestDto.class));

        mockMvc.perform(MockMvcRequestBuilders.post(ConstantsInfraestructure.URL_CREATE_BRAND)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ConstantsInfraestructure.JSON_BRAND_REQUEST))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath(ConstantsInfraestructure.MESSAGESS)
                        .value(ConstantsInfraestructure.INVALID_DESCRIPTION));
    }
}