package com.emazon.api_stock.infraestructure.output.jpa.mapper;

import com.emazon.api_stock.domain.model.ArticleXCategory;
import com.emazon.api_stock.infraestructure.output.jpa.entity.ArticleXCategoryEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class ArticleXCategoryEntityMapperTest {

    private final  ArticleXCategoryEntityMapper articleXCategoryEntityMapper = Mappers.getMapper( ArticleXCategoryEntityMapper.class);

    @Test
    void testBrandToBrandEntity() {
        ArticleXCategory articleXCategory = new ArticleXCategory(1, 1, 1);

        ArticleXCategoryEntity articleXCategoryEntity = articleXCategoryEntityMapper.articleXCategoryToArticleXCategoryEntity(articleXCategory);

        Assertions.assertNotNull(articleXCategoryEntity);
        Assertions.assertEquals(1, articleXCategoryEntity.getIdarticles());
        Assertions.assertEquals(1, articleXCategoryEntity.getIdcategory());
    }

    @Test
    void testArticleToArticleEntity_NullInput() {
        ArticleXCategoryEntity articleXCategoryEntity = articleXCategoryEntityMapper.articleXCategoryToArticleXCategoryEntity(null);

        Assertions.assertNull(articleXCategoryEntity);
    }
}
