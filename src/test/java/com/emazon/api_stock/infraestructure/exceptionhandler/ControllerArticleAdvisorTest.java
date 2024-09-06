package com.emazon.api_stock.infraestructure.exceptionhandler;

import com.emazon.api_stock.application.dto.article.ArticleRequestDto;
import com.emazon.api_stock.application.handler.article.IArticleHandler;
import com.emazon.api_stock.domain.exception.article.ArticleAlreadyExistsException;
import com.emazon.api_stock.domain.exception.article.InvalidArticleCategoryException;
import com.emazon.api_stock.domain.exception.article.InvalidArticleCategoryNumberException;
import com.emazon.api_stock.domain.exception.article.RepeatedCategoryException;
import com.emazon.api_stock.infraestructure.input.rest.ArticleRestController;
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

@WebMvcTest(controllers = {ArticleRestController.class, ControllerArticleAdvisor.class})
class ControllerArticleAdvisorTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IArticleHandler articleHandler;

    @Test
    void whenArticleAlreadyExistsException_thenReturnsConflict() throws Exception {
        Mockito.doThrow(new ArticleAlreadyExistsException()).when(articleHandler)
                .saveArticle(Mockito.any(ArticleRequestDto.class));

        mockMvc.perform(MockMvcRequestBuilders.post(ConstantsTest.URL_CREATE_ARTICLE.getMessage())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ConstantsTest.JSON_ARTICLE_REQUEST.getMessage()))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value(ConstantsTest.ARTICLE_EXISTS.getMessage()));
    }

    @Test
    void whenInvalidArticleCategoryException_thenReturnsBadRequest() throws Exception {
        Mockito.doThrow(new InvalidArticleCategoryException(ConstantsTest.FIELD_CATEGORYS_NULL.getMessage()))
                .when(articleHandler)
                .saveArticle(Mockito.any(ArticleRequestDto.class));

        mockMvc.perform(MockMvcRequestBuilders.post(ConstantsTest.URL_CREATE_ARTICLE.getMessage())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ConstantsTest.JSON_ARTICLE_REQUEST.getMessage()))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value(ConstantsTest.FIELD_CATEGORYS_NULL.getMessage()));
    }

    @Test
    void whenInvalidArticleCategoryNumberException_thenReturnsBadRequest() throws Exception {
        Mockito.doThrow(new InvalidArticleCategoryNumberException(
                ConstantsTest.FIELD_CATEGORYS_INVALID_NUMBER.getMessage()))
                .when(articleHandler)
                .saveArticle(Mockito.any(ArticleRequestDto.class));

        mockMvc.perform(MockMvcRequestBuilders.post(ConstantsTest.URL_CREATE_ARTICLE.getMessage())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ConstantsTest.JSON_ARTICLE_REQUEST.getMessage()))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value(ConstantsTest.FIELD_CATEGORYS_INVALID_NUMBER.getMessage()));
    }

    @Test
    void whenRepeatedCategoryException_thenReturnsBadRequest() throws Exception {
        Mockito.doThrow(new RepeatedCategoryException(
                        ConstantsTest.REPEATED_CATEGORIES.getMessage()))
                .when(articleHandler)
                .saveArticle(Mockito.any(ArticleRequestDto.class));

        mockMvc.perform(MockMvcRequestBuilders.post(ConstantsTest.URL_CREATE_ARTICLE.getMessage())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ConstantsTest.JSON_ARTICLE_REQUEST.getMessage()))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value(ConstantsTest.REPEATED_CATEGORIES.getMessage()));
    }
}