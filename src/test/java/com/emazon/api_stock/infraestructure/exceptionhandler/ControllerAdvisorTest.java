package com.emazon.api_stock.infraestructure.exceptionhandler;

import com.emazon.api_stock.application.dto.CategoryDto;
import com.emazon.api_stock.application.handler.ICategoryHandler;
import com.emazon.api_stock.domain.exception.InvalidCategoryDescriptionException;
import com.emazon.api_stock.domain.exception.InvalidCategoryNameException;
import com.emazon.api_stock.infraestructure.exception.CategoryAlreadyExistsException;
import com.emazon.api_stock.infraestructure.exception.NegativeNotAllowedException;
import com.emazon.api_stock.infraestructure.exception.NoDataFoundException;
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

@WebMvcTest(controllers = {CategoryRestController.class, ControllerAdvisor.class})
class ControllerAdvisorTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ICategoryHandler categoryHandler;

    @Test
    void whenCategoryAlreadyExistsException_thenReturnsConflict() throws Exception {
        Mockito.doThrow(new CategoryAlreadyExistsException()).when(categoryHandler)
                .saveCategory(Mockito.any(CategoryDto.class));

        mockMvc.perform(MockMvcRequestBuilders.post(ConstantsTest.URL_CREATE_CATEGORY.getMessage())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ConstantsTest.JSON_REQUEST.getMessage()))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value(ConstantsTest.CATEGORY_EXISTS.getMessage()));
    }

    @Test
    void whenInvalidCategoryNameException_thenReturnsBadRequest() throws Exception {
        Mockito.doThrow(new InvalidCategoryNameException(ConstantsTest.INVALID_CATEGORY_NAME.getMessage()))
                .when(categoryHandler)
                .saveCategory(Mockito.any(CategoryDto.class));

        mockMvc.perform(MockMvcRequestBuilders.post(ConstantsTest.URL_CREATE_CATEGORY.getMessage())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ConstantsTest.JSON_REQUEST.getMessage()))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value(ConstantsTest.INVALID_CATEGORY_NAME.getMessage()));
    }

    @Test
    void whenInvalidCategoryDescriptionException_thenReturnsBadRequest() throws Exception {
        Mockito.doThrow(new InvalidCategoryDescriptionException(ConstantsTest.INVALID_CATEGORY_DESCRIPTION.getMessage()))
                .when(categoryHandler)
                .saveCategory(Mockito.any(CategoryDto.class));

        mockMvc.perform(MockMvcRequestBuilders.post(ConstantsTest.URL_CREATE_CATEGORY.getMessage())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ConstantsTest.JSON_REQUEST.getMessage()))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value(ConstantsTest.INVALID_CATEGORY_DESCRIPTION.getMessage()));
    }

    @Test
    void whenNoDataFoundException_thenReturnsNotFound() throws Exception {
        Mockito.doThrow(new NoDataFoundException())
                .when(categoryHandler)
                .getAllCategorys(1,1,false);

        mockMvc.perform(MockMvcRequestBuilders.get(ConstantsTest.URL_GET_CATEGORY.getMessage())
                        .param("page", "1")
                        .param("size", "1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value(ConstantsTest.NO_DATA_FOUND_EXCEPTION_MESSAGE.getMessage()));
    }

    @Test
    void whenNegativeNotAllowedException_thenReturnsbadRequest() throws Exception {
        Mockito.doThrow(new NegativeNotAllowedException())
                .when(categoryHandler)
                .getAllCategorys(1,1,false);

        mockMvc.perform(MockMvcRequestBuilders.get(ConstantsTest.URL_GET_CATEGORY.getMessage())
                        .param("page", "1")
                        .param("size", "1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value(ConstantsTest.NEGATIVE_NOT_ALLOWED.getMessage()));
    }
}