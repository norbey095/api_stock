package com.emazon.api_stock.infraestructure.exceptionhandler;

import com.emazon.api_stock.application.dto.category.CategoryRequestDto;
import com.emazon.api_stock.application.handler.category.ICategoryHandler;
import com.emazon.api_stock.domain.exception.category.InvalidCategoryDescriptionException;
import com.emazon.api_stock.domain.exception.category.InvalidCategoryNameException;
import com.emazon.api_stock.domain.exception.category.CategoryAlreadyExistsException;
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
class ControllerCategoryAdvisorTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ICategoryHandler categoryHandler;

    @Test
    @WithMockUser(username = ConstantsInfraestructure.USER_NAME, roles = {ConstantsInfraestructure.ADMIN})
    void whenCategoryAlreadyExistsException_thenReturnsConflict() throws Exception {
        Mockito.doThrow(new CategoryAlreadyExistsException()).when(categoryHandler)
                .saveCategory(Mockito.any(CategoryRequestDto.class));

        mockMvc.perform(MockMvcRequestBuilders.post(ConstantsInfraestructure.URL_CREATE_CATEGORY)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ConstantsInfraestructure.JSON_REQUEST))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andExpect(MockMvcResultMatchers.jsonPath(ConstantsInfraestructure.MESSAGESS)
                        .value(ConstantsInfraestructure.CATEGORY_EXISTS));
    }

    @Test
    @WithMockUser(username = ConstantsInfraestructure.USER_NAME, roles = {ConstantsInfraestructure.ADMIN})
    void whenInvalidCategoryNameException_thenReturnsBadRequest() throws Exception {
        Mockito.doThrow(new InvalidCategoryNameException(ConstantsInfraestructure.INVALID_NAME))
                .when(categoryHandler)
                .saveCategory(Mockito.any(CategoryRequestDto.class));

        mockMvc.perform(MockMvcRequestBuilders.post(ConstantsInfraestructure.URL_CREATE_CATEGORY)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ConstantsInfraestructure.JSON_REQUEST))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath(ConstantsInfraestructure.MESSAGESS)
                        .value(ConstantsInfraestructure.INVALID_NAME));
    }

    @Test
    @WithMockUser(username = ConstantsInfraestructure.USER_NAME, roles = {ConstantsInfraestructure.ADMIN})
    void whenInvalidCategoryDescriptionException_thenReturnsBadRequest() throws Exception {
        Mockito.doThrow(new InvalidCategoryDescriptionException(ConstantsInfraestructure.INVALID_DESCRIPTION))
                .when(categoryHandler)
                .saveCategory(Mockito.any(CategoryRequestDto.class));

        mockMvc.perform(MockMvcRequestBuilders.post(ConstantsInfraestructure.URL_CREATE_CATEGORY)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ConstantsInfraestructure.JSON_REQUEST))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath(ConstantsInfraestructure.MESSAGESS)
                        .value(ConstantsInfraestructure.INVALID_DESCRIPTION));
    }
}