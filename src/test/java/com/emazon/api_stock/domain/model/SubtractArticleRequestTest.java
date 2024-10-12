package com.emazon.api_stock.domain.model;

import com.emazon.api_stock.domain.util.ConstantsDomain;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubtractArticleRequestTest {

    @Test
    void shouldSubtractArticleRequestConstructor() {
        SubtractArticleRequest  article = new SubtractArticleRequest (ConstantsDomain.VALUE_0, ConstantsDomain.VALUE_1);

        assertNotNull(article);
        assertEquals(ConstantsDomain.VALUE_0, article.getArticleId());
        assertEquals(ConstantsDomain.VALUE_1, article.getQuantity());
    }

    @Test
    void shouldSubtractArticleRequestSet() {
        SubtractArticleRequest  article = new SubtractArticleRequest();
        article.setArticleId(ConstantsDomain.VALUE_0);
        article.setQuantity(ConstantsDomain.VALUE_1);

        assertNotNull(article);
        assertEquals(ConstantsDomain.VALUE_0, article.getArticleId());
        assertEquals(ConstantsDomain.VALUE_1, article.getQuantity());
    }
}
