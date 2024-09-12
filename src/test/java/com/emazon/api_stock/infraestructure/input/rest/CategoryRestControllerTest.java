package com.emazon.api_stock.infraestructure.input.rest;

import com.emazon.api_stock.application.dto.category.CategoryRequestDto;
import com.emazon.api_stock.application.dto.category.CategoryResponseDto;
import com.emazon.api_stock.application.handler.category.ICategoryHandler;
import com.emazon.api_stock.infraestructure.util.ConstantsInfraestructure;
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

@WebMvcTest(CategoryRestController.class)
class CategoryRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ICategoryHandler categoryHandler;

    @Autowired
    private ObjectMapper objectMapper;

    private CategoryRequestDto categoryDto;

    private CategoryResponseDto categoryResponseDto;

    @BeforeEach
    void setUp() {
        categoryDto = new CategoryRequestDto();
        categoryDto.setName(ConstantsInfraestructure.FIELD_NAME);
        categoryDto.setDescription(ConstantsInfraestructure.FIELD_DESCRIPTION);

        categoryResponseDto = new CategoryResponseDto();
        categoryResponseDto.setName(ConstantsInfraestructure.FIELD_NAME);
        categoryResponseDto.setDescription(ConstantsInfraestructure.FIELD_DESCRIPTION);
    }

    @Test
    void createCategory_ShouldReturnStatusCreated() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(ConstantsInfraestructure.URL_CREATE_CATEGORY)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(categoryDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        Mockito.verify(categoryHandler, Mockito.times(ConstantsInfraestructure.VALUE_1)).saveCategory(categoryDto);
    }

    @Test
    void getAllCategory_ShouldReturnCategoryDto() throws Exception {
        List<CategoryResponseDto> categoryResponseDtoList = new ArrayList<>();
        categoryResponseDtoList.add(categoryResponseDto);
        Mockito.when(categoryHandler.getAllCategories(ConstantsInfraestructure.VALUE_1,
                ConstantsInfraestructure.VALUE_1,ConstantsInfraestructure.VALUE_FALSE))
                .thenReturn(categoryResponseDtoList);


        mockMvc.perform(MockMvcRequestBuilders.get(ConstantsInfraestructure.URL_GET_CATEGORY)
                        .param(ConstantsInfraestructure.PAGE, ConstantsInfraestructure.VALUE_UNO)
                        .param(ConstantsInfraestructure.SIZE, ConstantsInfraestructure.VALUE_UNO)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andExpect(MockMvcResultMatchers.jsonPath(ConstantsInfraestructure.MESSAGESS_NAME)
                                .value(ConstantsInfraestructure.FIELD_NAME))
                        .andExpect(MockMvcResultMatchers.jsonPath(ConstantsInfraestructure.MESSAGESS_DESCRIPTION)
                                .value(ConstantsInfraestructure.FIELD_DESCRIPTION));

        Mockito.verify(categoryHandler, Mockito.times(ConstantsInfraestructure.VALUE_1))
                .getAllCategories(ConstantsInfraestructure.VALUE_1,ConstantsInfraestructure.VALUE_1,
                        ConstantsInfraestructure.VALUE_FALSE);
    }
}