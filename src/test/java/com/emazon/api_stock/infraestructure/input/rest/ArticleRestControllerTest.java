package com.emazon.api_stock.infraestructure.input.rest;

import com.emazon.api_stock.application.dto.article.ArticleRequestDto;
import com.emazon.api_stock.application.dto.article.ArticleResponseDto;
import com.emazon.api_stock.application.handler.article.IArticleHandler;
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

import java.util.ArrayList;
import java.util.List;

@WebMvcTest(ArticleRestController.class)
class ArticleRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IArticleHandler articleHandler;

    @Autowired
    private ObjectMapper objectMapper;

    private ArticleRequestDto articleRequestDto;

    private ArticleResponseDto articleResponseDto;

    @BeforeEach
    void setUp() {
        articleRequestDto = new ArticleRequestDto();
        articleRequestDto.setName(ConstantsTest.FIELD_NAME.getMessage());
        articleRequestDto.setDescription(ConstantsTest.FIELD_ARTICLE_DESCRIPTION.getMessage());
        articleRequestDto.setQuantity(3);

        articleResponseDto = new ArticleResponseDto();
        articleResponseDto.setName(ConstantsTest.FIELD_NAME.getMessage());
        articleResponseDto.setDescription(ConstantsTest.FIELD_ARTICLE_DESCRIPTION.getMessage());
        articleResponseDto.setQuantity(3);
    }

    @Test
    void createArticle_ShouldReturnStatusCreated() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(ConstantsTest.URL_CREATE_ARTICLE.getMessage())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(articleRequestDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        Mockito.verify(articleHandler, Mockito.times(1)).saveArticle(articleRequestDto);
    }

    @Test
    void getAllArticle_ShouldReturnArticleResponseDto() throws Exception {
        List<ArticleResponseDto> articleResponseDtoList = new ArrayList<>();
        articleResponseDtoList.add(articleResponseDto);
        Mockito.when(articleHandler.getAllArticles(1, 1,false,"article"))
                .thenReturn(articleResponseDtoList);


        mockMvc.perform(MockMvcRequestBuilders.get(ConstantsTest.URL_GET_ARTICLE.getMessage())
                        .param("page", "1")
                        .param("size", "1")
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andExpect(MockMvcResultMatchers.jsonPath("$[0].name")
                                .value(ConstantsTest.FIELD_NAME.getMessage()))
                        .andExpect(MockMvcResultMatchers.jsonPath("$[0].description")
                                .value(ConstantsTest.FIELD_ARTICLE_DESCRIPTION.getMessage()))
                        .andExpect(MockMvcResultMatchers.jsonPath("$[0].quantity")
                                .value(3));

        Mockito.verify(articleHandler, Mockito.times(1))
                .getAllArticles(1, 1,false,"article");
    }
}