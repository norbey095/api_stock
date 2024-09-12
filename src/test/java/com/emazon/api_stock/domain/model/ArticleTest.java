package com.emazon.api_stock.domain.model;

import com.emazon.api_stock.domain.util.ConstantsDomain;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ArticleTest {

    @Test
    void shouldCreateArticleWhenNameAndDescriptionAreValid() {
        ArticleSave article = new ArticleSave(ConstantsDomain.VALUE_1, ConstantsDomain.FIELD_NAME
                , ConstantsDomain.FIELD_ARTICLE_DESCRIPTION
                , ConstantsDomain.VALUE_1, ConstantsDomain.PRICE, ConstantsDomain.VALUE_1,
                Collections.singletonList(ConstantsDomain.VALUE_1));

        assertNotNull(article);
        assertEquals(ConstantsDomain.VALUE_1, article.getId());
        assertEquals(ConstantsDomain.FIELD_NAME, article.getName());
        assertEquals(ConstantsDomain.FIELD_ARTICLE_DESCRIPTION, article.getDescription());
        assertEquals(ConstantsDomain.VALUE_1, article.getQuantity());
        assertEquals(ConstantsDomain.PRICE, article.getPrice());
        assertEquals(ConstantsDomain.VALUE_1, article.getIdbrand());
        List<Integer> expectedCategories = List.of(ConstantsDomain.VALUE_1);
        assertEquals(expectedCategories, article.getCategories());
    }
}
