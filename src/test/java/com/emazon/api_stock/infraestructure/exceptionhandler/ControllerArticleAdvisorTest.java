package com.emazon.api_stock.infraestructure.exceptionhandler;

import com.emazon.api_stock.application.dto.article.ArticleRequestDto;
import com.emazon.api_stock.application.handler.article.IArticleHandler;
import com.emazon.api_stock.domain.exception.article.*;
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
class ControllerArticleAdvisorTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IArticleHandler articleHandler;

    @Test
    @WithMockUser(username = ConstantsInfraestructure.USER_NAME, roles = {ConstantsInfraestructure.ADMIN})
    void whenArticleAlreadyExistsException_thenReturnsConflict() throws Exception {
        Mockito.doThrow(new ArticleAlreadyExistsException()).when(articleHandler)
                .saveArticle(Mockito.any(ArticleRequestDto.class));

        mockMvc.perform(MockMvcRequestBuilders.post(ConstantsInfraestructure.URL_CREATE_ARTICLE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ConstantsInfraestructure.JSON_ARTICLE_REQUEST))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andExpect(MockMvcResultMatchers.jsonPath(ConstantsInfraestructure.MESSAGESS)
                        .value(ConstantsInfraestructure.ARTICLE_EXISTS));
    }

    @Test
    @WithMockUser(username = ConstantsInfraestructure.USER_NAME, roles = {ConstantsInfraestructure.ADMIN})
    void whenInvalidArticleCategoryException_thenReturnsBadRequest() throws Exception {
        Mockito.doThrow(new InvalidArticleCategoryException(ConstantsInfraestructure.FIELD_CATEGORIES_NULL))
                .when(articleHandler)
                .saveArticle(Mockito.any(ArticleRequestDto.class));

        mockMvc.perform(MockMvcRequestBuilders.post(ConstantsInfraestructure.URL_CREATE_ARTICLE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ConstantsInfraestructure.JSON_ARTICLE_REQUEST))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath(ConstantsInfraestructure.MESSAGESS)
                        .value(ConstantsInfraestructure.FIELD_CATEGORIES_NULL));
    }

    @Test
    @WithMockUser(username = ConstantsInfraestructure.USER_NAME, roles = {ConstantsInfraestructure.ADMIN})
    void whenInvalidArticleCategoryNumberException_thenReturnsBadRequest() throws Exception {
        Mockito.doThrow(new InvalidArticleCategoryNumberException(
                ConstantsInfraestructure.FIELD_CATEGORIES_INVALID_NUMBER))
                .when(articleHandler)
                .saveArticle(Mockito.any(ArticleRequestDto.class));

        mockMvc.perform(MockMvcRequestBuilders.post(ConstantsInfraestructure.URL_CREATE_ARTICLE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ConstantsInfraestructure.JSON_ARTICLE_REQUEST))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath(ConstantsInfraestructure.MESSAGESS)
                        .value(ConstantsInfraestructure.FIELD_CATEGORIES_INVALID_NUMBER));
    }

    @Test
    @WithMockUser(username = ConstantsInfraestructure.USER_NAME, roles = {ConstantsInfraestructure.ADMIN})
    void whenRepeatedCategoryException_thenReturnsBadRequest() throws Exception {
        Mockito.doThrow(new RepeatedCategoryException(
                        ConstantsInfraestructure.REPEATED_CATEGORIES))
                .when(articleHandler)
                .saveArticle(Mockito.any(ArticleRequestDto.class));

        mockMvc.perform(MockMvcRequestBuilders.post(ConstantsInfraestructure.URL_CREATE_ARTICLE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ConstantsInfraestructure.JSON_ARTICLE_REQUEST))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath(ConstantsInfraestructure.MESSAGESS)
                        .value(ConstantsInfraestructure.REPEATED_CATEGORIES));
    }

    @Test
    @WithMockUser(username = ConstantsInfraestructure.USER_NAME, roles = {ConstantsInfraestructure.ADMIN})
    void whenTheArticleDoesNotExistException_thenReturnsConflict() throws Exception {
        Mockito.doThrow(new TheArticleDoesNotExistException()).when(articleHandler)
                .saveArticle(Mockito.any(ArticleRequestDto.class));

        mockMvc.perform(MockMvcRequestBuilders.post(ConstantsInfraestructure.URL_CREATE_ARTICLE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ConstantsInfraestructure.JSON_ARTICLE_REQUEST))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andExpect(MockMvcResultMatchers.jsonPath(ConstantsInfraestructure.MESSAGESS)
                        .value(ConstantsInfraestructure.ARTICLE_DOES_EXISTS));
    }
}