package com.emazon.api_stock.application.handler;

import com.emazon.api_stock.application.dto.article.ArticleRequestDto;
import com.emazon.api_stock.application.dto.article.ArticleResponseDto;
import com.emazon.api_stock.application.dto.article.ArticleUpdateRequestDto;
import com.emazon.api_stock.application.handler.article.ArticleHandler;
import com.emazon.api_stock.application.mapper.ArticleMapper;
import com.emazon.api_stock.domain.api.IArticleServicePort;
import com.emazon.api_stock.domain.model.ArticleResponse;
import com.emazon.api_stock.domain.model.ArticleSave;
import com.emazon.api_stock.domain.model.Brand;
import com.emazon.api_stock.domain.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.emazon.api_stock.application.util.ConstantsApplication.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ArticleHandlerTest {

    @InjectMocks
    private ArticleHandler articleHandler;

    @Mock
    private IArticleServicePort articleServicePort;

    @Mock
    private ArticleMapper articleMapper;

    private ArticleRequestDto articleRequestDto;

    private ArticleUpdateRequestDto articleUpdateRequestDto;

    private ArticleResponseDto articleResponseDto;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        articleRequestDto = new ArticleRequestDto();
        articleRequestDto.setName(FIELD_NAME);
        articleRequestDto.setDescription(FIELD_ARTICLE_DESCRIPTION);
        articleRequestDto.setQuantity(NUMBER_3);
        articleRequestDto.setPrice(PRICE);

        articleUpdateRequestDto = new ArticleUpdateRequestDto();
        articleUpdateRequestDto.setArticleId(ID);
        articleUpdateRequestDto.setQuantity(NUMBER_3);

        articleResponseDto = new ArticleResponseDto();
        articleResponseDto.setName(FIELD_NAME);
        articleResponseDto.setDescription(FIELD_ARTICLE_DESCRIPTION);
        articleResponseDto.setQuantity(NUMBER_3);
        articleRequestDto.setPrice(PRICE);
    }

    @Test
    void shouldSaveArticle() {
        ArticleSave articleSave = new ArticleSave(NUMBER_1, FIELD_NAME
                , FIELD_ARTICLE_DESCRIPTION
                , NUMBER_1, PRICE, NUMBER_1, Collections.singletonList(NUMBER_1));
        when(articleMapper.articleDtoToArticle(articleRequestDto)).thenReturn(articleSave);

        articleHandler.saveArticle(articleRequestDto);


        verify(articleMapper).articleDtoToArticle(articleRequestDto);
        verify(articleServicePort).saveArticle(articleSave);
    }

    @Test
    void shouldGetAllArticle() {
        List<ArticleResponseDto> articleResponseDtoList = new ArrayList<>();
        articleResponseDtoList.add(articleResponseDto);

        Brand brand = new Brand(NUMBER_1, FIELD_NAME
                , FIELD_ARTICLE_DESCRIPTION);
        List<Category> categories = new ArrayList<>();
        categories.add(new Category());

        ArticleResponse articleResponse = new ArticleResponse(NUMBER_1, FIELD_NAME
                , FIELD_BRAND_DESCRIPTION, NUMBER_1, PRICE, brand,categories);
        List<ArticleResponse> articleResponses = new ArrayList<>();
        articleResponses.add(articleResponse);

        when(articleMapper.toArticleDtoList(articleResponses)).thenReturn(articleResponseDtoList);
        when(articleServicePort.getAllArticles(NUMBER_1, NUMBER_1,false, ARTICLE))
                .thenReturn(articleResponses);

        articleHandler.getAllArticles(NUMBER_1, NUMBER_1,false, ARTICLE);


        verify(articleMapper).toArticleDtoList(articleResponses);
        verify(articleServicePort).getAllArticles(NUMBER_1, NUMBER_1,
                false, ARTICLE);
    }

    @Test
    void shouldUpdateArticle() {
        ArticleSave articleSave = new ArticleSave(ID, FIELD_NAME
                , FIELD_ARTICLE_DESCRIPTION
                , NUMBER_3, PRICE, NUMBER_1, Collections.singletonList(1));
        when(articleMapper.articleUpdateDtoToArticlesave(articleUpdateRequestDto)).thenReturn(articleSave);

        articleHandler.updateArticle(articleUpdateRequestDto);


        Mockito.verify(articleMapper, Mockito.times(NUMBER_1)).
                articleUpdateDtoToArticlesave(articleUpdateRequestDto);
        Mockito.verify(articleServicePort, Mockito.times(NUMBER_1)).
                updateArticle(articleSave);
    }

    @Test
    void shouldGetArticlesById() {
        articleHandler.getArticlesById(ID);

        Mockito.verify(articleServicePort, Mockito.times(NUMBER_1)).
                getArticlesById(ID);
    }
}
