package com.emazon.api_stock.application.mapper;

import com.emazon.api_stock.application.dto.article.ArticleRequestDto;
import com.emazon.api_stock.application.dto.article.ArticleResponseDto;
import com.emazon.api_stock.application.dto.article.ArticleUpdateRequestDto;
import com.emazon.api_stock.application.util.ConstantsApplication;
import com.emazon.api_stock.domain.model.ArticleResponse;
import com.emazon.api_stock.domain.model.ArticleSave;
import com.emazon.api_stock.domain.model.Brand;
import com.emazon.api_stock.domain.model.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

class ArticleMapperTest {

    private final ArticleMapper articleMapper = Mappers.getMapper(ArticleMapper.class);

    @Test
    void testArticleDtoToArticle() {
        ArticleRequestDto articleDto = new ArticleRequestDto();
        articleDto.setName(ConstantsApplication.FIELD_NAME);
        articleDto.setDescription(ConstantsApplication.FIELD_ARTICLE_DESCRIPTION);
        articleDto.setQuantity(ConstantsApplication.VALOR_1);
        articleDto.setPrice(ConstantsApplication.PRICE);
        articleDto.setIdbrand(ConstantsApplication.VALOR_1);
        articleDto.setCategories(new ArrayList<>());


        ArticleSave article = articleMapper.articleDtoToArticle(articleDto);

        Assertions.assertNotNull(article);
        Assertions.assertEquals(ConstantsApplication.FIELD_NAME, article.getName());
        Assertions.assertEquals(ConstantsApplication.FIELD_ARTICLE_DESCRIPTION, article.getDescription());
        Assertions.assertEquals(ConstantsApplication.VALOR_1, article.getQuantity());
        Assertions.assertEquals(ConstantsApplication.PRICE, article.getPrice());
        Assertions.assertEquals(ConstantsApplication.VALOR_1, article.getIdbrand());
        Assertions.assertEquals(new ArrayList<>(), article.getCategories());
    }

    @Test
    void testArticleDtoToArticle_NullInput() {
        ArticleSave article = articleMapper.articleDtoToArticle(null);

        Assertions.assertNull(article);
    }

    @Test
    void testToArticleDtoList() {

        Brand brand = new Brand(ConstantsApplication.VALOR_1, ConstantsApplication.FIELD_NAME
                , ConstantsApplication.FIELD_ARTICLE_DESCRIPTION);
        List<Category> categories = new ArrayList<>();
        categories.add(new Category());

        ArticleResponse articleResponse = new ArticleResponse(ConstantsApplication.VALOR_1, ConstantsApplication.FIELD_NAME
                , ConstantsApplication.FIELD_BRAND_DESCRIPTION, ConstantsApplication.VALOR_1, ConstantsApplication.PRICE, brand,categories);

        List<ArticleResponse> articleList = new ArrayList<>();
        articleList.add(articleResponse);

        List<ArticleResponseDto> articleDtoList = articleMapper.toArticleDtoList(articleList);

        Assertions.assertNotNull(articleDtoList);
        Assertions.assertEquals(ConstantsApplication.FIELD_NAME, articleDtoList.get(ConstantsApplication.VALOR_0).getName());
        Assertions.assertEquals(ConstantsApplication.FIELD_BRAND_DESCRIPTION, articleDtoList.get(ConstantsApplication.VALOR_0)
                .getDescription());
    }

    @Test
    void testToBrandDtoList_NullInput() {
        List<ArticleResponseDto> articleResponseDtos = articleMapper.toArticleDtoList(null);

        Assertions.assertNull(articleResponseDtos);
    }

    @Test
    void testArticleUpdateDtoToArticleSave() {
        ArticleUpdateRequestDto articleUpdateRequestDto = new ArticleUpdateRequestDto();
        articleUpdateRequestDto.setArticleId(ConstantsApplication.VALOR_1);
        articleUpdateRequestDto.setQuantity(ConstantsApplication.VALOR_1);

        ArticleSave article = articleMapper.articleUpdateDtoToArticlesave(articleUpdateRequestDto);

        Assertions.assertNotNull(article);
        Assertions.assertEquals(ConstantsApplication.VALOR_1, article.getId());
        Assertions.assertEquals(ConstantsApplication.VALOR_1, article.getQuantity());
    }
}
