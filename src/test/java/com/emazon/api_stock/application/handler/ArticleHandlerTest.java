package com.emazon.api_stock.application.handler;

import com.emazon.api_stock.application.dto.article.ArticleRequestDto;
import com.emazon.api_stock.application.dto.article.ArticleResponseDto;
import com.emazon.api_stock.application.handler.article.ArticleHandler;
import com.emazon.api_stock.application.mapper.ArticleMapper;
import com.emazon.api_stock.application.util.ConstantsApplication;
import com.emazon.api_stock.domain.api.IArticleServicePort;
import com.emazon.api_stock.domain.model.ArticleResponse;
import com.emazon.api_stock.domain.model.ArticleSave;
import com.emazon.api_stock.domain.model.Brand;
import com.emazon.api_stock.domain.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    private ArticleResponseDto articleResponseDto;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        articleRequestDto = new ArticleRequestDto();
        articleRequestDto.setName(ConstantsApplication.FIELD_NAME);
        articleRequestDto.setDescription(ConstantsApplication.FIELD_ARTICLE_DESCRIPTION);
        articleRequestDto.setQuantity(ConstantsApplication.VALOR_3);
        articleRequestDto.setPrice(ConstantsApplication.PRICE);

        articleResponseDto = new ArticleResponseDto();
        articleResponseDto.setName(ConstantsApplication.FIELD_NAME);
        articleResponseDto.setDescription(ConstantsApplication.FIELD_ARTICLE_DESCRIPTION);
        articleResponseDto.setQuantity(ConstantsApplication.VALOR_3);
        articleRequestDto.setPrice(ConstantsApplication.PRICE);
    }

    @Test
    void shouldSaveArticle() {
        ArticleSave articleSave = new ArticleSave(ConstantsApplication.VALOR_1, ConstantsApplication.FIELD_NAME
                , ConstantsApplication.FIELD_ARTICLE_DESCRIPTION
                , ConstantsApplication.VALOR_1, ConstantsApplication.PRICE, ConstantsApplication.VALOR_1, Collections.singletonList(1));
        when(articleMapper.articleDtoToArticle(articleRequestDto)).thenReturn(articleSave);

        articleHandler.saveArticle(articleRequestDto);


        verify(articleMapper).articleDtoToArticle(articleRequestDto);
        verify(articleServicePort).saveArticle(articleSave);
    }

    @Test
    void shouldGetAllArticle() {
        List<ArticleResponseDto> articleResponseDtoList = new ArrayList<>();
        articleResponseDtoList.add(articleResponseDto);

        Brand brand = new Brand(ConstantsApplication.VALOR_1, ConstantsApplication.FIELD_NAME
                , ConstantsApplication.FIELD_ARTICLE_DESCRIPTION);
        List<Category> categories = new ArrayList<>();
        categories.add(new Category());

        ArticleResponse articleResponse = new ArticleResponse(ConstantsApplication.VALOR_1, ConstantsApplication.FIELD_NAME
                , ConstantsApplication.FIELD_BRAND_DESCRIPTION, ConstantsApplication.VALOR_1, ConstantsApplication.PRICE, brand,categories);
        List<ArticleResponse> articleResponses = new ArrayList<>();
        articleResponses.add(articleResponse);

        when(articleMapper.toArticleDtoList(articleResponses)).thenReturn(articleResponseDtoList);
        when(articleServicePort.getAllArticles(ConstantsApplication.VALOR_1, ConstantsApplication.VALOR_1,false, ConstantsApplication.ARTICLE))
                .thenReturn(articleResponses);

        articleHandler.getAllArticles(ConstantsApplication.VALOR_1, ConstantsApplication.VALOR_1,false, ConstantsApplication.ARTICLE);


        verify(articleMapper).toArticleDtoList(articleResponses);
        verify(articleServicePort).getAllArticles(ConstantsApplication.VALOR_1, ConstantsApplication.VALOR_1,
                false, ConstantsApplication.ARTICLE);
    }
}
