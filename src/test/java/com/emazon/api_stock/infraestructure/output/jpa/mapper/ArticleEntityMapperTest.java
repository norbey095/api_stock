package com.emazon.api_stock.infraestructure.output.jpa.mapper;

import com.emazon.api_stock.domain.model.ArticleResponse;
import com.emazon.api_stock.domain.model.ArticleSave;
import com.emazon.api_stock.infraestructure.output.jpa.entity.ArticleEntity;
import com.emazon.api_stock.infraestructure.output.jpa.entity.BrandEntity;
import com.emazon.api_stock.infraestructure.output.jpa.entity.CategoryEntity;
import com.emazon.api_stock.infraestructure.util.ConstantsTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class ArticleEntityMapperTest {

    private final  ArticleEntityMapper articleEntityMapper = Mappers.getMapper( ArticleEntityMapper.class);

    @Test
    void testArticleToArticleEntity() {
        ArticleSave article = new ArticleSave(1, ConstantsTest.FIELD_NAME.getMessage()
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
    void testArticleToArticleEntity_NullInput() {
        ArticleEntity articleEntity = articleEntityMapper.articleToArticleEntity(null);

        Assertions.assertNull(articleEntity);
    }

    @Test
    void testArticleEntityToArticleResponse() {
        BrandEntity brandEntity = new BrandEntity(1, ConstantsTest.FIELD_NAME.getMessage()
                ,ConstantsTest.FIELD_ARTICLES_DESCRIPTION.getMessage(),new ArrayList<>());

        CategoryEntity categoryEntity = new CategoryEntity(1, ConstantsTest.FIELD_NAME.getMessage()
                ,ConstantsTest.FIELD_ARTICLES_DESCRIPTION.getMessage(),new ArrayList<>());
        List<CategoryEntity> categoryEntities = new ArrayList<>();
        categoryEntities.add(categoryEntity);

        ArticleEntity articleEntity = new ArticleEntity(1, ConstantsTest.FIELD_NAME.getMessage()
                ,ConstantsTest.FIELD_ARTICLES_DESCRIPTION.getMessage(),2, 25000, 1, brandEntity
        , categoryEntities);

        List<ArticleEntity> articleList = new ArrayList<>();
        articleList.add(articleEntity);


        List<ArticleResponse> articleResponses = articleEntityMapper.articleEntityToArticleResponse(articleList);


        Assertions.assertNotNull(articleResponses);
        Assertions.assertEquals(ConstantsTest.FIELD_NAME.getMessage(), articleResponses.get(0).getName());
        Assertions.assertEquals(ConstantsTest.FIELD_ARTICLES_DESCRIPTION.getMessage(),
                articleResponses.get(0).getDescription());
        Assertions.assertEquals(brandEntity.getId(), articleResponses.get(0).getId());
        Assertions.assertEquals(categoryEntities.get(0).getId(), articleResponses.get(0).getCategories().get(0).getId());
    }

    @Test
    void testBrandEntityToBrand_NullInput() {
        List<ArticleResponse> articleResponses = articleEntityMapper.articleEntityToArticleResponse(null);

        Assertions.assertNull(articleResponses);
    }
}
