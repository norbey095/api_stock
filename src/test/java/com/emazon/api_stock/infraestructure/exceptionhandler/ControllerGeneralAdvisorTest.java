package com.emazon.api_stock.infraestructure.exceptionhandler;

import com.emazon.api_stock.application.handler.category.ICategoryHandler;
import com.emazon.api_stock.domain.exception.NoDataFoundException;
import com.emazon.api_stock.domain.exception.PaginationNotAllowedException;
import com.emazon.api_stock.infraestructure.input.rest.CategoryRestController;
import com.emazon.api_stock.infraestructure.util.ConstantsInfraestructure;
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
                .getAllCategories(ConstantsInfraestructure.VALUE_1,
                        ConstantsInfraestructure.VALUE_1,ConstantsInfraestructure.VALUE_FALSE);

        mockMvc.perform(MockMvcRequestBuilders.get(ConstantsInfraestructure.URL_GET_CATEGORY)
                        .param(ConstantsInfraestructure.PAGE, ConstantsInfraestructure.VALUE_UNO)
                        .param(ConstantsInfraestructure.SIZE, ConstantsInfraestructure.VALUE_UNO)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath(ConstantsInfraestructure.MESSAGESS)
                        .value(ConstantsInfraestructure.NO_DATA_FOUND_EXCEPTION_MESSAGE));
    }

    @Test
    void whenNegativeNotAllowedException_thenReturnsbadRequest() throws Exception {
        Mockito.doThrow(new PaginationNotAllowedException())
                .when(categoryHandler)
                .getAllCategories(ConstantsInfraestructure.VALUE_1,ConstantsInfraestructure.VALUE_1
                        ,ConstantsInfraestructure.VALUE_FALSE);

        mockMvc.perform(MockMvcRequestBuilders.get(ConstantsInfraestructure.URL_GET_CATEGORY)
                        .param(ConstantsInfraestructure.PAGE, ConstantsInfraestructure.VALUE_UNO)
                        .param(ConstantsInfraestructure.SIZE, ConstantsInfraestructure.VALUE_UNO)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath(ConstantsInfraestructure.MESSAGESS)
                        .value(ConstantsInfraestructure.NEGATIVE_NOT_ALLOWED));
    }
}