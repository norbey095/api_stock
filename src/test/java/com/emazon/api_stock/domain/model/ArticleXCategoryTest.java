package com.emazon.api_stock.domain.model;

import com.emazon.api_stock.domain.util.ConstantsDomain;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ArticleXCategoryTest {

    @Test
    void shouldCreateAnArticleCorrectlyWhitConstructor() {
        ArticleXCategory articleXCategory = new ArticleXCategory(ConstantsDomain.VALUE_1, ConstantsDomain.VALUE_1
                , ConstantsDomain.VALUE_1);

        assertNotNull(articleXCategory);
        assertEquals(ConstantsDomain.VALUE_1, articleXCategory.getId());
        assertEquals(ConstantsDomain.VALUE_1, articleXCategory.getIdcategory());
        assertEquals(ConstantsDomain.VALUE_1, articleXCategory.getIdarticles());
    }

    @Test
    void shouldCreateAnArticleCorrectlyWhitConstructorWhitSet() {
        ArticleXCategory articleXCategory = new ArticleXCategory();
        articleXCategory.setId(ConstantsDomain.VALUE_1);
        articleXCategory.setIdarticles(ConstantsDomain.VALUE_1);
        articleXCategory.setIdcategory(ConstantsDomain.VALUE_1);

        assertNotNull(articleXCategory);
        assertEquals(ConstantsDomain.VALUE_1, articleXCategory.getId());
        assertEquals(ConstantsDomain.VALUE_1, articleXCategory.getIdcategory());
        assertEquals(ConstantsDomain.VALUE_1, articleXCategory.getIdarticles());
    }
}
