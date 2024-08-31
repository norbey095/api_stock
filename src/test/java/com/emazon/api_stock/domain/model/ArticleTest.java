package com.emazon.api_stock.domain.model;

import com.emazon.api_stock.domain.util.ConstantsTest;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ArticleTest {

    @Test
    void shouldCreateArticleWhenNameAndDescriptionAreValid() {
        Article article = new Article(1, ConstantsTest.FIELD_NAME.getMessage()
                , ConstantsTest.FIELD_ARTICLE_DESCRIPTION.getMessage()
                , 1, 2.0, 1, Collections.singletonList(1));

        assertNotNull(article);
        assertEquals(1, article.getId());
        assertEquals(ConstantsTest.FIELD_NAME.getMessage(), article.getName());
        assertEquals(ConstantsTest.FIELD_ARTICLE_DESCRIPTION.getMessage(), article.getDescription());
        assertEquals(1, article.getQuantity());
        assertEquals(2.0, article.getPrice());
        assertEquals(1, article.getIdbrand());
        List<Integer> expectedCategories = Arrays.asList(1);
        assertEquals(expectedCategories, article.getCategorys());
    }
}
