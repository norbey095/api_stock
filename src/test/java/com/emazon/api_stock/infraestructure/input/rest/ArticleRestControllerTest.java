package com.emazon.api_stock.infraestructure.input.rest;

import com.emazon.api_stock.application.dto.article.ArticleRequestDto;
import com.emazon.api_stock.application.dto.article.ArticleResponseDto;
import com.emazon.api_stock.application.handler.article.IArticleHandler;
import com.emazon.api_stock.infraestructure.util.ConstantsInfraestructure;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
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
        articleRequestDto.setName(ConstantsInfraestructure.FIELD_NAME);
        articleRequestDto.setDescription(ConstantsInfraestructure.FIELD_ARTICLE_DESCRIPTION);
        articleRequestDto.setQuantity(ConstantsInfraestructure.VALUE_3);
        articleRequestDto.setPrice(ConstantsInfraestructure.PRICE);
        articleRequestDto.setIdbrand(ConstantsInfraestructure.VALUE_1);

        articleResponseDto = new ArticleResponseDto();
        articleResponseDto.setName(ConstantsInfraestructure.FIELD_NAME);
        articleResponseDto.setDescription(ConstantsInfraestructure.FIELD_ARTICLE_DESCRIPTION);
        articleResponseDto.setQuantity(ConstantsInfraestructure.VALUE_3);
        articleResponseDto.setPrice(ConstantsInfraestructure.PRICE);
        articleRequestDto.setIdbrand(ConstantsInfraestructure.VALUE_1);
    }

    @Test
    @WithMockUser(username = ConstantsInfraestructure.USER_NAME, roles = {ConstantsInfraestructure.ADMIN})
    void createArticle_ShouldReturnStatusCreated() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(ConstantsInfraestructure.URL_CREATE_ARTICLE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(articleRequestDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        Mockito.verify(articleHandler, Mockito.times(ConstantsInfraestructure.VALUE_1)).saveArticle(articleRequestDto);
    }

    @Test
    @WithMockUser(username = ConstantsInfraestructure.USER_NAME, roles = {ConstantsInfraestructure.ADMIN})
    void getAllArticle_ShouldReturnArticleResponseDto() throws Exception {
        List<ArticleResponseDto> articleResponseDtoList = new ArrayList<>();
        articleResponseDtoList.add(articleResponseDto);
        Mockito.when(articleHandler.getAllArticles(ConstantsInfraestructure.VALUE_1, ConstantsInfraestructure.VALUE_1
                        ,ConstantsInfraestructure.VALUE_FALSE,ConstantsInfraestructure.ARTICLE))
                .thenReturn(articleResponseDtoList);


        mockMvc.perform(MockMvcRequestBuilders.get(ConstantsInfraestructure.URL_GET_ARTICLE)
                        .param(ConstantsInfraestructure.PAGE, ConstantsInfraestructure.VALUE_UNO)
                        .param(ConstantsInfraestructure.SIZE, ConstantsInfraestructure.VALUE_UNO)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andExpect(MockMvcResultMatchers.jsonPath(ConstantsInfraestructure.MESSAGESS_NAME)
                                .value(ConstantsInfraestructure.FIELD_NAME))
                        .andExpect(MockMvcResultMatchers.jsonPath(ConstantsInfraestructure.MESSAGESS_DESCRIPTION)
                                .value(ConstantsInfraestructure.FIELD_ARTICLE_DESCRIPTION))
                        .andExpect(MockMvcResultMatchers.jsonPath(ConstantsInfraestructure.MESSAGESS_QUANTITY)
                                .value(ConstantsInfraestructure.VALUE_3));

        Mockito.verify(articleHandler, Mockito.times(ConstantsInfraestructure.VALUE_1))
                .getAllArticles(ConstantsInfraestructure.VALUE_1, ConstantsInfraestructure.VALUE_1
                        ,ConstantsInfraestructure.VALUE_FALSE,ConstantsInfraestructure.ARTICLE);
    }
}