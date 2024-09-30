package com.emazon.api_stock.domain.usecase;

import com.emazon.api_stock.domain.exception.NoDataFoundException;
import com.emazon.api_stock.domain.exception.PaginationNotAllowedException;
import com.emazon.api_stock.domain.exception.article.*;
import com.emazon.api_stock.domain.model.*;
import com.emazon.api_stock.domain.spi.IArticlePersistencePort;
import com.emazon.api_stock.domain.spi.IArticleXCategoryPersistencePort;
import com.emazon.api_stock.domain.util.ConstantsDomain;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ArticleUseCaseTest {

    @Mock
    private IArticlePersistencePort articlePersistencePort;

    @Mock
    private IArticleXCategoryPersistencePort articleXCategoryPersistencePort;

    @InjectMocks
    private ArticleUseCase articleUseCase;

    private ArticleResponse articleDataBase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        Brand brand = new Brand(ConstantsDomain.VALUE_1, ConstantsDomain.FIELD_NAME
                , ConstantsDomain.FIELD_DESCRIPTION);

        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category(ConstantsDomain.VALUE_1, ConstantsDomain.FIELD_NAME
                , ConstantsDomain.FIELD_DESCRIPTION));

        articleDataBase = new ArticleResponse(ConstantsDomain.VALUE_1, ConstantsDomain.FIELD_NAME
                , ConstantsDomain.FIELD_ARTICLES_DESCRIPTION,ConstantsDomain.VALUE_2
                , ConstantsDomain.PRICE, brand,categoryList);
    }

    @Test
    void shouldCallSaveArticleInPersistencePort() {
        ArticleSave article = new ArticleSave(ConstantsDomain.VALUE_1, ConstantsDomain.FIELD_NAME
                , ConstantsDomain.FIELD_ARTICLE_DESCRIPTION
                , ConstantsDomain.VALUE_1, ConstantsDomain.PRICE, ConstantsDomain.VALUE_1,
                Collections.singletonList(ConstantsDomain.VALUE_1));

        articleUseCase.saveArticle(article);

        Mockito.verify(articlePersistencePort, Mockito.times(ConstantsDomain.VALUE_1)).saveArticle(article);
        Mockito.verify(articleXCategoryPersistencePort, Mockito.times(ConstantsDomain.VALUE_1))
                .saveArticleXCategory(Mockito.any());
    }

    @Test
    void shouldThrowsExceptionWhenArticleExists() {
        ArticleSave article = new ArticleSave(ConstantsDomain.VALUE_1, ConstantsDomain.FIELD_NAME
                , ConstantsDomain.FIELD_ARTICLE_DESCRIPTION
                , ConstantsDomain.VALUE_1, ConstantsDomain.PRICE, ConstantsDomain.VALUE_1, null);
        Mockito.when(articlePersistencePort.getArticleByName(article.getName()))
                .thenReturn(ConstantsDomain.VALUE_TRUE);

        assertThrows(ArticleAlreadyExistsException.class, () -> {
            articleUseCase.saveArticle(article);
        });

        Mockito.verify(articlePersistencePort, Mockito.times(ConstantsDomain.VALUE_1))
                .getArticleByName(article.getName());
    }

    @Test
    void shouldThrowsExceptionWhenCateryIsNull() {
        ArticleSave article = new ArticleSave(ConstantsDomain.VALUE_1, ConstantsDomain.FIELD_NAME
                , ConstantsDomain.FIELD_ARTICLE_DESCRIPTION
                , ConstantsDomain.VALUE_1, ConstantsDomain.PRICE, ConstantsDomain.VALUE_1, null);

        assertThrows(InvalidArticleCategoryException.class, () -> {
            articleUseCase.saveArticle(article);
        });

        Mockito.verify(articlePersistencePort, Mockito.times(ConstantsDomain.VALUE_0)).saveArticle(article);
        Mockito.verify(articleXCategoryPersistencePort, Mockito.times(ConstantsDomain.VALUE_0))
                .saveArticleXCategory(Mockito.any());
    }

    @Test
    void shouldThrowsExceptionWhenCateryIsEmpty() {
        ArticleSave article = new ArticleSave(ConstantsDomain.VALUE_1, ConstantsDomain.FIELD_NAME
                , ConstantsDomain.FIELD_ARTICLE_DESCRIPTION
                , ConstantsDomain.VALUE_1, ConstantsDomain.PRICE, ConstantsDomain.VALUE_1, new ArrayList<>());

        assertThrows(InvalidArticleCategoryException.class, () -> {
            articleUseCase.saveArticle(article);
        });

        Mockito.verify(articlePersistencePort, Mockito.times(ConstantsDomain.VALUE_0)).saveArticle(article);
        Mockito.verify(articleXCategoryPersistencePort, Mockito.times(ConstantsDomain.VALUE_0))
                .saveArticleXCategory(Mockito.any());
    }

    @Test
    void shouldThrowsExceptionWhenCateryIsGreaterThan3() {
        List<Integer> list = Arrays.asList(ConstantsDomain.VALUE_1, ConstantsDomain.VALUE_2,
                ConstantsDomain.VALUE_3, ConstantsDomain.VALUE_4);
        ArticleSave article = new ArticleSave(ConstantsDomain.VALUE_1, ConstantsDomain.FIELD_NAME
                , ConstantsDomain.FIELD_ARTICLE_DESCRIPTION
                , ConstantsDomain.VALUE_1, ConstantsDomain.PRICE, ConstantsDomain.VALUE_1, list);

        assertThrows(InvalidArticleCategoryNumberException.class, () -> {
            articleUseCase.saveArticle(article);
        });

        Mockito.verify(articlePersistencePort, Mockito.times(ConstantsDomain.VALUE_0)).saveArticle(article);
        Mockito.verify(articleXCategoryPersistencePort, Mockito.times(ConstantsDomain.VALUE_0))
                .saveArticleXCategory(Mockito.any());
    }

    @Test
    void shouldThrowsExceptionWhenCateryIsRepeated() {
        List<Integer> list = Arrays.asList(ConstantsDomain.VALUE_2, ConstantsDomain.VALUE_2, ConstantsDomain.VALUE_3);
        ArticleSave article = new ArticleSave(ConstantsDomain.VALUE_1, ConstantsDomain.FIELD_NAME
                , ConstantsDomain.FIELD_ARTICLE_DESCRIPTION
                , ConstantsDomain.VALUE_1, ConstantsDomain.PRICE, ConstantsDomain.VALUE_1, list);

        assertThrows(RepeatedCategoryException.class, () -> {
            articleUseCase.saveArticle(article);
        });

        Mockito.verify(articlePersistencePort, Mockito.times(ConstantsDomain.VALUE_0)).saveArticle(article);
        Mockito.verify(articleXCategoryPersistencePort, Mockito.times(ConstantsDomain.VALUE_0))
                .saveArticleXCategory(Mockito.any());
    }

    @Test
    void shouldGetAllArticle() {
        Brand brand = new Brand(ConstantsDomain.VALUE_1, ConstantsDomain.FIELD_NAME
                , ConstantsDomain.FIELD_ARTICLE_DESCRIPTION);
        List<Category> categories = new ArrayList<>();
        categories.add(new Category());

        ArticleResponse articleResponse = new ArticleResponse(ConstantsDomain.VALUE_1, ConstantsDomain.FIELD_NAME
                , ConstantsDomain.FIELD_ARTICLE_DESCRIPTION, ConstantsDomain.VALUE_1, ConstantsDomain.PRICE,
                brand,categories);
        List<ArticleResponse> articleList = new ArrayList<>();
        articleList.add(articleResponse);

        Mockito.when(articlePersistencePort.getAllArticles(ConstantsDomain.VALUE_1,ConstantsDomain.VALUE_1
                ,ConstantsDomain.VALUE_FALSE,ConstantsDomain.ARTICLE)).thenReturn(articleList);

        List<ArticleResponse> result = articleUseCase.getAllArticles(ConstantsDomain.VALUE_1,ConstantsDomain.VALUE_1
                ,ConstantsDomain.VALUE_FALSE, ConstantsDomain.ARTICLE);

        Mockito.verify(articlePersistencePort, Mockito.times(ConstantsDomain.VALUE_1))
                .getAllArticles(ConstantsDomain.VALUE_1,ConstantsDomain.VALUE_1,ConstantsDomain.VALUE_FALSE
                        , ConstantsDomain.ARTICLE);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.get(ConstantsDomain.VALUE_0).getName(), articleResponse.getName());
        Assertions.assertEquals(result.get(ConstantsDomain.VALUE_0).getDescription(), articleResponse.getDescription());
    }

    @Test
    void testGetAllBrand_WhitPageNegative() {
        Integer page = ConstantsDomain.VALUE_N1;
        Integer size = ConstantsDomain.VALUE_1;

        assertThrows(PaginationNotAllowedException.class, () -> {
            articleUseCase.getAllArticles(page, size, ConstantsDomain.VALUE_FALSE, ConstantsDomain.ARTICLE_NAME);
        });

        Mockito.verify(articlePersistencePort, Mockito.times(ConstantsDomain.VALUE_0))
                .getAllArticles(page,size,ConstantsDomain.VALUE_FALSE,  ConstantsDomain.ARTICLE_NAME);
    }

    @Test
    void testGetAllBrand_WhitPageNull() {
        Integer page = null;
        Integer size = ConstantsDomain.VALUE_1;

        assertThrows(PaginationNotAllowedException.class, () -> {
            articleUseCase.getAllArticles(page, size, ConstantsDomain.VALUE_FALSE, ConstantsDomain.ARTICLE_NAME);
        });

        Mockito.verify(articlePersistencePort, Mockito.times(ConstantsDomain.VALUE_0))
                .getAllArticles(page,size,ConstantsDomain.VALUE_FALSE,  ConstantsDomain.ARTICLE_NAME);
    }

    @Test
    void testGetAllBrand_WhitSizeNegative() {
        Integer page = ConstantsDomain.VALUE_1;
        Integer size = ConstantsDomain.VALUE_N1;

        assertThrows(PaginationNotAllowedException.class, () -> {
            articleUseCase.getAllArticles(page, size, ConstantsDomain.VALUE_FALSE,  ConstantsDomain.ARTICLE_NAME);
        });

        Mockito.verify(articlePersistencePort, Mockito.times(ConstantsDomain.VALUE_0))
                .getAllArticles(page,size,ConstantsDomain.VALUE_FALSE, ConstantsDomain.ARTICLE_NAME);
    }

    @Test
    void testGetAllBrand_WhitSizeNull() {
        Integer page = ConstantsDomain.VALUE_1;
        Integer size = null;

        assertThrows(PaginationNotAllowedException.class, () -> {
            articleUseCase.getAllArticles(page, size, ConstantsDomain.VALUE_FALSE,  ConstantsDomain.ARTICLE_NAME);
        });

        Mockito.verify(articlePersistencePort, Mockito.times(ConstantsDomain.VALUE_0))
                .getAllArticles(page,size,ConstantsDomain.VALUE_FALSE, ConstantsDomain.ARTICLE_NAME);
    }

    @Test
    void shouldGetAllArticleNoData() {
        Mockito.when(articlePersistencePort.getAllArticles(ConstantsDomain.VALUE_1,ConstantsDomain.VALUE_1
                ,ConstantsDomain.VALUE_FALSE,ConstantsDomain.ARTICLE)).thenReturn(new ArrayList<>());

        assertThrows(NoDataFoundException.class, () -> {
            articleUseCase.getAllArticles(ConstantsDomain.VALUE_1,ConstantsDomain.VALUE_1
                    ,ConstantsDomain.VALUE_FALSE, ConstantsDomain.ARTICLE);
        });

        Mockito.verify(articlePersistencePort, Mockito.times(ConstantsDomain.VALUE_1))
                .getAllArticles(ConstantsDomain.VALUE_1,ConstantsDomain.VALUE_1,ConstantsDomain.VALUE_FALSE
                        , ConstantsDomain.ARTICLE);
    }


    @Test
    void shouldThrowsUpdateSuccessfully() {
        ArticleSave articleRequest = new ArticleSave(ConstantsDomain.VALUE_1,null
                ,null,ConstantsDomain.VALUE_4, 0.0
                ,null, new ArrayList<>());

        when(articlePersistencePort.getArticleById(ConstantsDomain.VALUE_1)).thenReturn(articleDataBase);

        articleUseCase.updateQuantity(articleRequest);

        Mockito.verify(articlePersistencePort, Mockito.times(ConstantsDomain.VALUE_1))
                .getArticleById(ConstantsDomain.VALUE_1);
        Mockito.verify(articlePersistencePort, Mockito.times(ConstantsDomain.VALUE_1))
                .updateArticle(articleDataBase);
    }

    @Test
    void shouldThrowsTheArticleDoesNotExistException() {
        ArticleSave articleRequest = new ArticleSave(ConstantsDomain.VALUE_1,null
                ,null,ConstantsDomain.VALUE_4, 0.0
                ,null, new ArrayList<>());

        when(articlePersistencePort.getArticleById(ConstantsDomain.VALUE_1)).thenReturn(null);

        assertThrows(TheArticleDoesNotExistException.class, () -> {
            articleUseCase.updateQuantity(articleRequest);
        });

        Mockito.verify(articlePersistencePort, Mockito.times(ConstantsDomain.VALUE_1))
                .getArticleById(ConstantsDomain.VALUE_1);
        Mockito.verify(articlePersistencePort, Mockito.times(ConstantsDomain.VALUE_0))
                .updateArticle(Mockito.any(ArticleResponse.class));
    }

    @Test
    void getArticlesById_ShouldReturnTrue_WhenArticleExists() {
        Integer articleId = ConstantsDomain.VALUE_1;
        when(articlePersistencePort.getArticleById(articleId)).thenReturn(articleDataBase);

        ArticleResponse articleResponse = articleUseCase.getArticlesById(articleId);
        assertNotNull(articleResponse);
    }

    @Test
    void getArticlesById_ShouldReturnFalse_WhenArticleDoesNotExist() {
        Integer articleId =ConstantsDomain.VALUE_1;

        when(articlePersistencePort.getArticleById(articleId)).thenReturn(null);

        ArticleResponse result = articleUseCase.getArticlesById(articleId);
        assertNull(result);
    }

    @Test
    void getArticleByIds() {
        when(articlePersistencePort.getArticleByIds(ConstantsDomain.VALUE_0, ConstantsDomain.VALUE_1
                , true
                , new ArrayList<>(), ConstantsDomain.ARTICLE, ConstantsDomain.ARTICLE_NAME))
                .thenReturn(new ArrayList<ArticleResponse>());

        List<ArticleResponse> result = articleUseCase.getArticleByIds(ConstantsDomain.VALUE_0, ConstantsDomain.VALUE_1
                , true
                , new ArrayList<>(), ConstantsDomain.ARTICLE, ConstantsDomain.ARTICLE_NAME);

        assertNotNull(result);
    }

    @Test
    void testGetPriceByIds() {
        List<Integer> articleIds = new ArrayList<>();
        articleIds.add(ConstantsDomain.VALUE_1);

        when(articlePersistencePort.getPriceByIds(articleIds)).thenReturn(new ArrayList<>());

        List<ArticlePriceResponse> result = articleUseCase.getPriceByIds(articleIds);
        assertNotNull(result);
    }

    @Test
    void testSubtractQuantityArticle() {
        SubtractArticleRequest subtractArticleRequest = new SubtractArticleRequest(ConstantsDomain.VALUE_1
                , ConstantsDomain.VALUE_1);

        List<SubtractArticleRequest> subtractArticleRequestsList = new ArrayList<>();
        subtractArticleRequestsList.add(subtractArticleRequest);

        ArticleResponse articleResponse = new ArticleResponse(ConstantsDomain.VALUE_1, ConstantsDomain.FIELD_NAME
                , ConstantsDomain.FIELD_ARTICLE_DESCRIPTION, ConstantsDomain.VALUE_1, ConstantsDomain.PRICE,
                null,null);

        when(articlePersistencePort.getArticleById(ConstantsDomain.VALUE_1)).thenReturn(articleResponse);


        articleUseCase.subtractQuantityArticle(subtractArticleRequestsList);

        Mockito.verify(articlePersistencePort, Mockito.times(ConstantsDomain.VALUE_1))
                .getArticleById(ConstantsDomain.VALUE_1);
    }
}
