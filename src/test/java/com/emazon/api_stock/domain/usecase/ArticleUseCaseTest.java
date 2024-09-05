package com.emazon.api_stock.domain.usecase;

import com.emazon.api_stock.domain.exception.article.InvalidArticleCategoryException;
import com.emazon.api_stock.domain.exception.article.InvalidArticleCategoryNumberException;
import com.emazon.api_stock.domain.exception.article.RepeatedCategoryException;
import com.emazon.api_stock.domain.model.ArticleSave;
import com.emazon.api_stock.domain.spi.IArticlePersistencePort;
import com.emazon.api_stock.domain.spi.IArticleXCategoryPersistencePort;
import com.emazon.api_stock.domain.util.ConstantsTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ArticleUseCaseTest {

    @Mock
    private IArticlePersistencePort articlePersistencePort;

    @Mock
    private IArticleXCategoryPersistencePort articleXCategoryPersistencePort;

    @InjectMocks
    private ArticleUseCase articleUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCallSaveArticleInPersistencePort() {
        ArticleSave article = new ArticleSave(1, ConstantsTest.FIELD_NAME.getMessage()
                , ConstantsTest.FIELD_ARTICLE_DESCRIPTION.getMessage()
                , 1, 2.0, 1, Collections.singletonList(1));

        articleUseCase.saveArticle(article);

        Mockito.verify(articlePersistencePort, Mockito.times(1)).saveArticle(article);
        Mockito.verify(articleXCategoryPersistencePort, Mockito.times(1))
                .saveArticleXCategory(Mockito.any());
    }

    @Test
    void shouldThrowsExceptionWhenCateryIsNull() {
        ArticleSave article = new ArticleSave(1, ConstantsTest.FIELD_NAME.getMessage()
                , ConstantsTest.FIELD_ARTICLE_DESCRIPTION.getMessage()
                , 1, 2.0, 1, null);

        assertThrows(InvalidArticleCategoryException.class, () -> {
            articleUseCase.saveArticle(article);
        });

        Mockito.verify(articlePersistencePort, Mockito.times(0)).saveArticle(article);
        Mockito.verify(articleXCategoryPersistencePort, Mockito.times(0))
                .saveArticleXCategory(Mockito.any());
    }

    @Test
    void shouldThrowsExceptionWhenCateryIsEmpty() {
        ArticleSave article = new ArticleSave(1, ConstantsTest.FIELD_NAME.getMessage()
                , ConstantsTest.FIELD_ARTICLE_DESCRIPTION.getMessage()
                , 1, 2.0, 1, new ArrayList<>());

        assertThrows(InvalidArticleCategoryException.class, () -> {
            articleUseCase.saveArticle(article);
        });

        Mockito.verify(articlePersistencePort, Mockito.times(0)).saveArticle(article);
        Mockito.verify(articleXCategoryPersistencePort, Mockito.times(0))
                .saveArticleXCategory(Mockito.any());
    }

    @Test
    void shouldThrowsExceptionWhenCateryIsGreaterThan3() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4);
        ArticleSave article = new ArticleSave(1, ConstantsTest.FIELD_NAME.getMessage()
                , ConstantsTest.FIELD_ARTICLE_DESCRIPTION.getMessage()
                , 1, 2.0, 1, list);

        assertThrows(InvalidArticleCategoryNumberException.class, () -> {
            articleUseCase.saveArticle(article);
        });

        Mockito.verify(articlePersistencePort, Mockito.times(0)).saveArticle(article);
        Mockito.verify(articleXCategoryPersistencePort, Mockito.times(0))
                .saveArticleXCategory(Mockito.any());
    }

    @Test
    void shouldThrowsExceptionWhenCateryIsRepeated() {
        List<Integer> list = Arrays.asList(2, 2, 3);
        ArticleSave article = new ArticleSave(1, ConstantsTest.FIELD_NAME.getMessage()
                , ConstantsTest.FIELD_ARTICLE_DESCRIPTION.getMessage()
                , 1, 2.0, 1, list);

        assertThrows(RepeatedCategoryException.class, () -> {
            articleUseCase.saveArticle(article);
        });

        Mockito.verify(articlePersistencePort, Mockito.times(0)).saveArticle(article);
        Mockito.verify(articleXCategoryPersistencePort, Mockito.times(0))
                .saveArticleXCategory(Mockito.any());
    }
}
