package com.emazon.api_stock.infraestructure.output.mapper;

import com.emazon.api_stock.domain.model.ArticleXCategory;
import com.emazon.api_stock.infraestructure.output.entity.ArticleXCategoryEntity;
import com.emazon.api_stock.infraestructure.util.ConstantsInfraestructure;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class ArticleXCategoryEntityMapperTest {

    private final  ArticleXCategoryEntityMapper articleXCategoryEntityMapper =
            Mappers.getMapper( ArticleXCategoryEntityMapper.class);

    @Test
    void testBrandToBrandEntity() {
        ArticleXCategory articleXCategory = new ArticleXCategory(ConstantsInfraestructure.VALUE_1
                , ConstantsInfraestructure.VALUE_1, ConstantsInfraestructure.VALUE_1);

        ArticleXCategoryEntity articleXCategoryEntity = articleXCategoryEntityMapper
                .articleXCategoryToArticleXCategoryEntity(articleXCategory);

        Assertions.assertNotNull(articleXCategoryEntity);
        Assertions.assertEquals(ConstantsInfraestructure.VALUE_1, articleXCategoryEntity.getIdarticles());
        Assertions.assertEquals(ConstantsInfraestructure.VALUE_1, articleXCategoryEntity.getIdcategory());
    }

    @Test
    void testArticleToArticleEntity_NullInput() {
        ArticleXCategoryEntity articleXCategoryEntity = articleXCategoryEntityMapper
                .articleXCategoryToArticleXCategoryEntity(null);

        Assertions.assertNull(articleXCategoryEntity);
    }
}
