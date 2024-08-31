package com.emazon.api_stock.infraestructure.output.jpa.mapper;

import com.emazon.api_stock.domain.model.Article;
import com.emazon.api_stock.infraestructure.output.jpa.entity.ArticleEntity;
import com.emazon.api_stock.infraestructure.util.ConstantsTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.Collections;

class ArticleEntityMapperTest {

    private final  ArticleEntityMapper articleEntityMapper = Mappers.getMapper( ArticleEntityMapper.class);

    @Test
    void testArticleToArticleEntity() {
        Article article = new Article(1, ConstantsTest.FIELD_NAME.getMessage()
                ,ConstantsTest.FIELD_BRAND_DESCRIPTION.getMessage(), 1, 1,
                1, Collections.singletonList(1));

        ArticleEntity articleEntity = articleEntityMapper.articleToArticleEntity(article);

        Assertions.assertNotNull(articleEntity);
        Assertions.assertEquals(ConstantsTest.FIELD_NAME.getMessage(), articleEntity.getName());
        Assertions.assertEquals(ConstantsTest.FIELD_BRAND_DESCRIPTION.getMessage(), articleEntity.getDescription());
        Assertions.assertEquals(1,articleEntity.getQuantity());
        Assertions.assertEquals(1,articleEntity.getPrice());
        Assertions.assertEquals(1,articleEntity.getIdbrand());
        Assertions.assertEquals(Collections.singletonList(1), article.getCategorys());
    }

    @Test
    void testBrandToBrandEntity_NullInput() {
        ArticleEntity articleEntity = articleEntityMapper.articleToArticleEntity(null);

        Assertions.assertNull(articleEntity);
    }
}
