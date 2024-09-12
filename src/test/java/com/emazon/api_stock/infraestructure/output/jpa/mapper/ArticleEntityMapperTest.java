package com.emazon.api_stock.infraestructure.output.jpa.mapper;

import com.emazon.api_stock.domain.model.ArticleResponse;
import com.emazon.api_stock.domain.model.ArticleSave;
import com.emazon.api_stock.infraestructure.output.jpa.entity.ArticleEntity;
import com.emazon.api_stock.infraestructure.output.jpa.entity.BrandEntity;
import com.emazon.api_stock.infraestructure.output.jpa.entity.CategoryEntity;
import com.emazon.api_stock.infraestructure.util.ConstantsInfraestructure;
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
        ArticleSave article = new ArticleSave(ConstantsInfraestructure.VALUE_1, ConstantsInfraestructure.FIELD_NAME
                , ConstantsInfraestructure.FIELD_BRAND_DESCRIPTION, ConstantsInfraestructure.VALUE_1
                , ConstantsInfraestructure.PRICE,ConstantsInfraestructure.VALUE_1
                , Collections.singletonList(ConstantsInfraestructure.VALUE_1));

        ArticleEntity articleEntity = articleEntityMapper.articleToArticleEntity(article);

        Assertions.assertNotNull(articleEntity);
        Assertions.assertEquals(ConstantsInfraestructure.FIELD_NAME, articleEntity.getName());
        Assertions.assertEquals(ConstantsInfraestructure.FIELD_BRAND_DESCRIPTION, articleEntity.getDescription());
        Assertions.assertEquals(ConstantsInfraestructure.VALUE_1,articleEntity.getQuantity());
        Assertions.assertEquals(ConstantsInfraestructure.PRICE,articleEntity.getPrice());
        Assertions.assertEquals(ConstantsInfraestructure.VALUE_1,articleEntity.getIdbrand());
        Assertions.assertEquals(Collections.singletonList(ConstantsInfraestructure.VALUE_1), article.getCategories());
    }

    @Test
    void testArticleToArticleEntity_NullInput() {
        ArticleEntity articleEntity = articleEntityMapper.articleToArticleEntity(null);

        Assertions.assertNull(articleEntity);
    }

    @Test
    void testArticleEntityToArticleResponse() {
        BrandEntity brandEntity = new BrandEntity(ConstantsInfraestructure.VALUE_1, ConstantsInfraestructure.FIELD_NAME
                , ConstantsInfraestructure.FIELD_ARTICLES_DESCRIPTION,new ArrayList<>());

        CategoryEntity categoryEntity = new CategoryEntity(ConstantsInfraestructure.VALUE_1
                , ConstantsInfraestructure.FIELD_NAME
                , ConstantsInfraestructure.FIELD_ARTICLES_DESCRIPTION,new ArrayList<>());
        List<CategoryEntity> categoryEntities = new ArrayList<>();
        categoryEntities.add(categoryEntity);

        ArticleEntity articleEntity = new ArticleEntity(ConstantsInfraestructure.VALUE_1
                , ConstantsInfraestructure.FIELD_NAME, ConstantsInfraestructure.FIELD_ARTICLES_DESCRIPTION
                ,ConstantsInfraestructure.VALUE_2, ConstantsInfraestructure.PRICE
                , ConstantsInfraestructure.VALUE_1, brandEntity
        , categoryEntities);

        List<ArticleEntity> articleList = new ArrayList<>();
        articleList.add(articleEntity);


        List<ArticleResponse> articleResponses = articleEntityMapper.articleEntityToArticleResponse(articleList);


        Assertions.assertNotNull(articleResponses);
        Assertions.assertEquals(ConstantsInfraestructure.FIELD_NAME, articleResponses
                .get(ConstantsInfraestructure.VALUE_0).getName());
        Assertions.assertEquals(ConstantsInfraestructure.FIELD_ARTICLES_DESCRIPTION,
                articleResponses.get(ConstantsInfraestructure.VALUE_0).getDescription());
        Assertions.assertEquals(brandEntity.getId(), articleResponses.get(ConstantsInfraestructure.VALUE_0).getId());
        Assertions.assertEquals(categoryEntities.get(ConstantsInfraestructure.VALUE_0).getId()
                , articleResponses.get(ConstantsInfraestructure.VALUE_0).getCategories()
                        .get(ConstantsInfraestructure.VALUE_0).getId());
    }

    @Test
    void testBrandEntityToBrand_NullInput() {
        List<ArticleResponse> articleResponses = articleEntityMapper.articleEntityToArticleResponse(null);

        Assertions.assertNull(articleResponses);
    }
}
