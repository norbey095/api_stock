package com.emazon.api_stock.infraestructure.output.mapper;

import com.emazon.api_stock.domain.model.ArticleResponse;
import com.emazon.api_stock.domain.model.ArticleSave;
import com.emazon.api_stock.infraestructure.output.entity.ArticleEntity;
import com.emazon.api_stock.infraestructure.output.entity.BrandEntity;
import com.emazon.api_stock.infraestructure.output.entity.CategoryEntity;
import com.emazon.api_stock.infraestructure.util.ConstantsInfraestructure;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

class ArticleEntityMapperTest {

    private final  ArticleEntityMapper articleEntityMapper = Mappers.getMapper( ArticleEntityMapper.class);

    @Test
    void testArticleToArticleEntity() {
        ArticleResponse article =  new ArticleResponse(ConstantsInfraestructure.VALUE_1, ConstantsInfraestructure.FIELD_NAME
                , ConstantsInfraestructure.FIELD_ARTICLES_DESCRIPTION,ConstantsInfraestructure.VALUE_2
                , ConstantsInfraestructure.PRICE, null,null);

        ArticleEntity articleEntity = articleEntityMapper.articleToArticleEntity(article);

        Assertions.assertNotNull(articleEntity);
        Assertions.assertEquals(ConstantsInfraestructure.FIELD_NAME, articleEntity.getName());
        Assertions.assertEquals(ConstantsInfraestructure.FIELD_ARTICLES_DESCRIPTION, articleEntity.getDescription());
        Assertions.assertEquals(ConstantsInfraestructure.VALUE_2,articleEntity.getQuantity());
        Assertions.assertEquals(ConstantsInfraestructure.PRICE,articleEntity.getPrice());
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


        List<ArticleResponse> articleResponses = articleEntityMapper.articleEntityToArticleResponseList(articleList);


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
        List<ArticleResponse> articleResponses = articleEntityMapper.articleEntityToArticleResponseList(null);

        Assertions.assertNull(articleResponses);
    }

    @Test
    void testArticleEntityToArticleSave() {
        ArticleEntity articleEntity;
        articleEntity = new ArticleEntity(ConstantsInfraestructure.VALUE_1, ConstantsInfraestructure.FIELD_NAME
                , ConstantsInfraestructure.FIELD_BRAND_DESCRIPTION, ConstantsInfraestructure.VALUE_1
                , ConstantsInfraestructure.PRICE,ConstantsInfraestructure.VALUE_1
                , null
                , null);

        ArticleSave articleSave = articleEntityMapper.articleEntityToArticleSave(articleEntity);

        Assertions.assertNotNull(articleSave);
        Assertions.assertEquals(ConstantsInfraestructure.FIELD_NAME, articleSave.getName());
        Assertions.assertEquals(ConstantsInfraestructure.FIELD_BRAND_DESCRIPTION, articleSave.getDescription());
        Assertions.assertEquals(ConstantsInfraestructure.VALUE_1,articleSave.getQuantity());
        Assertions.assertEquals(ConstantsInfraestructure.PRICE,articleSave.getPrice());
    }

    @Test
    void testArticleEntityToArticleSaveNull() {

        ArticleSave articleSave = articleEntityMapper.articleEntityToArticleSave(null);

        Assertions.assertNull(articleSave);
    }

    @Test
    void testArticleSaveToArticleEntity() {
        ArticleSave articleSave = new ArticleSave(ConstantsInfraestructure.VALUE_1, ConstantsInfraestructure.FIELD_NAME
                , ConstantsInfraestructure.FIELD_BRAND_DESCRIPTION, ConstantsInfraestructure.VALUE_1
                , ConstantsInfraestructure.PRICE,ConstantsInfraestructure.VALUE_1
                , null);

        ArticleEntity articleEntity = articleEntityMapper.articleSaveToArticleEntity(articleSave);

        Assertions.assertNotNull(articleEntity);
        Assertions.assertEquals(ConstantsInfraestructure.FIELD_NAME, articleEntity.getName());
        Assertions.assertEquals(ConstantsInfraestructure.FIELD_BRAND_DESCRIPTION, articleEntity.getDescription());
        Assertions.assertEquals(ConstantsInfraestructure.VALUE_1,articleEntity.getQuantity());
        Assertions.assertEquals(ConstantsInfraestructure.PRICE,articleEntity.getPrice());
    }

    @Test
    void testArticleSaveToArticleEntityNull() {

        ArticleEntity articleEntity = articleEntityMapper.articleSaveToArticleEntity(null);

        Assertions.assertNull(articleEntity);
    }
}
