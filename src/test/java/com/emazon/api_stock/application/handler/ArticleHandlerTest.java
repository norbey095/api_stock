package com.emazon.api_stock.application.handler;

import com.emazon.api_stock.application.dto.article.ArticleRequestDto;
import com.emazon.api_stock.application.dto.article.ArticleResponseDto;
import com.emazon.api_stock.application.handler.article.ArticleHandler;
import com.emazon.api_stock.application.mapper.ArticleMapper;
import com.emazon.api_stock.application.util.ConstantsTest;
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
        articleRequestDto.setName(ConstantsTest.FIELD_NAME.getMessage());
        articleRequestDto.setDescription(ConstantsTest.FIELD_ARTICLE_DESCRIPTION.getMessage());

        articleResponseDto = new ArticleResponseDto();
        articleResponseDto.setName(ConstantsTest.FIELD_NAME.getMessage());
        articleResponseDto.setDescription(ConstantsTest.FIELD_ARTICLE_DESCRIPTION.getMessage());
    }

    @Test
    void shouldSaveArticle() {
        ArticleSave articleSave = new ArticleSave(1, ConstantsTest.FIELD_NAME.getMessage()
                , ConstantsTest.FIELD_ARTICLE_DESCRIPTION.getMessage()
                , 1, 2.0, 1, Collections.singletonList(1));
        when(articleMapper.articleDtoToArticle(articleRequestDto)).thenReturn(articleSave);

        articleHandler.saveArticle(articleRequestDto);


        verify(articleMapper).articleDtoToArticle(articleRequestDto);
        verify(articleServicePort).saveArticle(articleSave);
    }

    @Test
    void shouldGetAllArticle() {
        List<ArticleResponseDto> articleResponseDtoList = new ArrayList<>();
        articleResponseDtoList.add(articleResponseDto);

        Brand brand = new Brand(1, ConstantsTest.FIELD_NAME.getMessage()
                , ConstantsTest.FIELD_ARTICLE_DESCRIPTION.getMessage());
        List<Category> categories = new ArrayList<>();
        categories.add(new Category());

        ArticleResponse articleResponse = new ArticleResponse(1, ConstantsTest.FIELD_NAME.getMessage()
                , ConstantsTest.FIELD_BRAND_DESCRIPTION.getMessage(), 1, 1000, brand,categories);
        List<ArticleResponse> articleResponses = new ArrayList<>();
        articleResponses.add(articleResponse);

        when(articleMapper.toArticleDtoList(articleResponses)).thenReturn(articleResponseDtoList);
        when(articleServicePort.getAllArticles(1,1,false,"article"))
                .thenReturn(articleResponses);

        articleHandler.getAllArticles(1,1,false,"article");


        verify(articleMapper).toArticleDtoList(articleResponses);
        verify(articleServicePort).getAllArticles(1,1,false,"article");
    }
}
