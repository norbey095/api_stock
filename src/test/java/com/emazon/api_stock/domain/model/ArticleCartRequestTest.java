package com.emazon.api_stock.domain.model;

import com.emazon.api_stock.domain.util.ConstantsDomain;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ArticleCartRequestTest {

    @Test
    void shouldCreateArticleCartRequestConstructor() {
        ArticleCartRequest  article = new ArticleCartRequest (ConstantsDomain.VALUE_0, ConstantsDomain.VALUE_1
                , true
                , new ArrayList<>(), ConstantsDomain.ARTICLE, ConstantsDomain.ARTICLE_NAME);

        assertNotNull(article);
        assertEquals(ConstantsDomain.VALUE_0, article.getPage());
        assertEquals(ConstantsDomain.VALUE_1, article.getSize());
        assertTrue(article.isDescending());
        assertEquals(ConstantsDomain.ARTICLE, article.getCategoryName());
        assertEquals(ConstantsDomain.ARTICLE_NAME, article.getBrandName());
    }

    @Test
    void shouldCreateArticleCartRequestSet() {
        ArticleCartRequest  article = new ArticleCartRequest();
        article.setPage(ConstantsDomain.VALUE_0);
        article.setSize(ConstantsDomain.VALUE_1);
        article.setDescending(true);
        article.setArticleIds(new ArrayList<>());
        article.setBrandName(ConstantsDomain.ARTICLE);
        article.setCategoryName(ConstantsDomain.ARTICLE_NAME);


        assertNotNull(article);
        assertEquals(ConstantsDomain.VALUE_0, article.getPage());
        assertEquals(ConstantsDomain.VALUE_1, article.getSize());
        assertTrue(article.isDescending());
        assertEquals(ConstantsDomain.ARTICLE_NAME, article.getCategoryName());
        assertEquals(ConstantsDomain.ARTICLE, article.getBrandName());
    }
}
