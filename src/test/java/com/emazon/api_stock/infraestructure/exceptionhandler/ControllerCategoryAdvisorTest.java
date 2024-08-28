package com.emazon.api_stock.infraestructure.exceptionhandler;

import com.emazon.api_stock.application.dto.category.CategoryRequestDto;
import com.emazon.api_stock.application.handler.category.ICategoryHandler;
import com.emazon.api_stock.domain.exception.category.InvalidCategoryDescriptionException;
import com.emazon.api_stock.domain.exception.category.InvalidCategoryNameException;
import com.emazon.api_stock.domain.exception.category.CategoryAlreadyExistsException;
import com.emazon.api_stock.infraestructure.input.rest.CategoryRestController;
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

@WebMvcTest(controllers = {CategoryRestController.class, ControllerCategoryAdvisor.class})
class ControllerCategoryAdvisorTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ICategoryHandler categoryHandler;

    @Test
    void whenCategoryAlreadyExistsException_thenReturnsConflict() throws Exception {
        Mockito.doThrow(new CategoryAlreadyExistsException()).when(categoryHandler)
                .saveCategory(Mockito.any(CategoryRequestDto.class));

        mockMvc.perform(MockMvcRequestBuilders.post(ConstantsTest.URL_CREATE_CATEGORY.getMessage())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ConstantsTest.JSON_REQUEST.getMessage()))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value(ConstantsTest.CATEGORY_EXISTS.getMessage()));
    }

    @Test
    void whenInvalidCategoryNameException_thenReturnsBadRequest() throws Exception {
        Mockito.doThrow(new InvalidCategoryNameException(ConstantsTest.INVALID_NAME.getMessage()))
                .when(categoryHandler)
                .saveCategory(Mockito.any(CategoryRequestDto.class));

        mockMvc.perform(MockMvcRequestBuilders.post(ConstantsTest.URL_CREATE_CATEGORY.getMessage())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ConstantsTest.JSON_REQUEST.getMessage()))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value(ConstantsTest.INVALID_NAME.getMessage()));
    }

    @Test
    void whenInvalidCategoryDescriptionException_thenReturnsBadRequest() throws Exception {
        Mockito.doThrow(new InvalidCategoryDescriptionException(ConstantsTest.INVALID_DESCRIPTION.getMessage()))
                .when(categoryHandler)
                .saveCategory(Mockito.any(CategoryRequestDto.class));

        mockMvc.perform(MockMvcRequestBuilders.post(ConstantsTest.URL_CREATE_CATEGORY.getMessage())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ConstantsTest.JSON_REQUEST.getMessage()))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value(ConstantsTest.INVALID_DESCRIPTION.getMessage()));
    }
}