package com.emazon.api_stock.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ArticleXCategoryTest {

    @Test
    void shouldCreateAnArticleCorrectlyWhitConstructor() {
        ArticleXCategory articleXCategory = new ArticleXCategory(1, 1, 1);

        assertNotNull(articleXCategory);
        assertEquals(1, articleXCategory.getId());
        assertEquals(1, articleXCategory.getIdcategory());
        assertEquals(1, articleXCategory.getIdarticles());
    }

    @Test
    void shouldCreateAnArticleCorrectlyWhitConstructorWhitSet() {
        ArticleXCategory articleXCategory = new ArticleXCategory();
        articleXCategory.setId(1);
        articleXCategory.setIdarticles(1);
        articleXCategory.setIdcategory(1);

        assertNotNull(articleXCategory);
        assertEquals(1, articleXCategory.getId());
        assertEquals(1, articleXCategory.getIdcategory());
        assertEquals(1, articleXCategory.getIdarticles());
    }
}
