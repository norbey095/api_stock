package com.emazon.api_stock.infraestructure.exceptionhandler;

import com.emazon.api_stock.application.handler.category.ICategoryHandler;
import com.emazon.api_stock.infraestructure.exception.NoDataFoundException;
import com.emazon.api_stock.infraestructure.exception.PaginationNotAllowedException;
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
class ControllerGeneralAdvisorTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ICategoryHandler categoryHandler;

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
        Mockito.doThrow(new PaginationNotAllowedException())
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