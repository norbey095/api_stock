package com.emazon.api_stock.domain.model;

import com.emazon.api_stock.domain.util.ConstantsDomain;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArticlePriceResponseTest {

    @Test
    void shouldCreateArticleCartRequestConstructor() {
        ArticlePriceResponse  articlePriceResponse = new ArticlePriceResponse (ConstantsDomain.VALUE_0,
                ConstantsDomain.PRICE
                , ConstantsDomain.VALUE_1);

        assertNotNull(articlePriceResponse);
        assertEquals(ConstantsDomain.VALUE_0, articlePriceResponse.getId());
        assertEquals(ConstantsDomain.VALUE_1, articlePriceResponse.getQuantity());
        assertEquals(ConstantsDomain.PRICE, articlePriceResponse.getPrice());
    }

    @Test
    void shouldCreateArticleCartRequestSet() {
        ArticlePriceResponse  articlePriceResponse = new ArticlePriceResponse();
        articlePriceResponse.setId(ConstantsDomain.VALUE_0);
        articlePriceResponse.setPrice(ConstantsDomain.PRICE);
        articlePriceResponse.setQuantity(ConstantsDomain.VALUE_1);

        assertNotNull(articlePriceResponse);
        assertEquals(ConstantsDomain.VALUE_0, articlePriceResponse.getId());
        assertEquals(ConstantsDomain.VALUE_1, articlePriceResponse.getQuantity());
        assertEquals(ConstantsDomain.PRICE, articlePriceResponse.getPrice());
    }
}
