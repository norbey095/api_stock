package com.emazon.api_stock.application.mapper;

import com.emazon.api_stock.application.dto.article.*;
import com.emazon.api_stock.application.dto.category.CategoryResponseListDto;
import com.emazon.api_stock.application.util.ConstantsApplication;
import com.emazon.api_stock.domain.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class ArticleMapperTest {

    private final ArticleMapper articleMapper = Mappers.getMapper(ArticleMapper.class);

    @Test
    void testArticleDtoToArticle() {
        ArticleRequestDto articleDto = new ArticleRequestDto();
        articleDto.setName(ConstantsApplication.FIELD_NAME);
        articleDto.setDescription(ConstantsApplication.FIELD_ARTICLE_DESCRIPTION);
        articleDto.setQuantity(ConstantsApplication.NUMBER_1);
        articleDto.setPrice(ConstantsApplication.PRICE);
        articleDto.setIdbrand(ConstantsApplication.NUMBER_1);
        articleDto.setCategories(new ArrayList<>());


        ArticleSave article = articleMapper.articleDtoToArticle(articleDto);

        Assertions.assertNotNull(article);
        Assertions.assertEquals(ConstantsApplication.FIELD_NAME, article.getName());
        Assertions.assertEquals(ConstantsApplication.FIELD_ARTICLE_DESCRIPTION, article.getDescription());
        Assertions.assertEquals(ConstantsApplication.NUMBER_1, article.getQuantity());
        Assertions.assertEquals(ConstantsApplication.PRICE, article.getPrice());
        Assertions.assertEquals(ConstantsApplication.NUMBER_1, article.getIdbrand());
        Assertions.assertEquals(new ArrayList<>(), article.getCategories());
    }

    @Test
    void testArticleDtoToArticle_NullInput() {
        ArticleSave article = articleMapper.articleDtoToArticle(null);

        Assertions.assertNull(article);
    }

    @Test
    void testToArticleDtoList() {

        Brand brand = new Brand(ConstantsApplication.NUMBER_1, ConstantsApplication.FIELD_NAME
                , ConstantsApplication.FIELD_ARTICLE_DESCRIPTION);
        List<Category> categories = new ArrayList<>();
        categories.add(new Category());

        ArticleResponse articleResponse = new ArticleResponse(ConstantsApplication.NUMBER_1
                , ConstantsApplication.FIELD_NAME
                , ConstantsApplication.FIELD_BRAND_DESCRIPTION, ConstantsApplication.NUMBER_1
                , ConstantsApplication.PRICE, brand,categories);

        List<ArticleResponse> articleList = new ArrayList<>();
        articleList.add(articleResponse);

        List<ArticleResponseDto> articleDtoList = articleMapper.toArticleDtoList(articleList);

        Assertions.assertNotNull(articleDtoList);
        Assertions.assertEquals(ConstantsApplication.FIELD_NAME, articleDtoList.get(ConstantsApplication.NUMBER_0)
                .getName());
        Assertions.assertEquals(ConstantsApplication.FIELD_BRAND_DESCRIPTION, articleDtoList.get(ConstantsApplication
                        .NUMBER_0)
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
        articleUpdateRequestDto.setArticleId(ConstantsApplication.NUMBER_1);
        articleUpdateRequestDto.setQuantity(ConstantsApplication.NUMBER_1);

        ArticleSave article = articleMapper.articleUpdateDtoToArticlesave(articleUpdateRequestDto);

        Assertions.assertNotNull(article);
        Assertions.assertEquals(ConstantsApplication.NUMBER_1, article.getId());
        Assertions.assertEquals(ConstantsApplication.NUMBER_1, article.getQuantity());
    }

    @Test
    void testToCategoryResponseListDto() {
        Category category = new Category();
        category.setId(ConstantsApplication.NUMBER_1);
        category.setName(ConstantsApplication.FIELD_NAME);

        CategoryResponseListDto dto = articleMapper.toCategoryResponseListDto(category);

        Assertions.assertNotNull(dto);
        Assertions.assertEquals(ConstantsApplication.NUMBER_1, dto.getId());
        Assertions.assertEquals(ConstantsApplication.FIELD_NAME, dto.getName());
    }

    @Test
    void testToCategoryResponseListDtoList() {
        List<Category> categories = Arrays.asList(
                new Category(ConstantsApplication.NUMBER_1, ConstantsApplication.FIELD_NAME,
                        ConstantsApplication.FIELD_DESCRIPTION)
        );

        List<CategoryResponseListDto> dtoList = articleMapper.toCategoryResponseListDtoList(categories);

        Assertions.assertEquals(ConstantsApplication.NUMBER_1, dtoList.size());
        Assertions.assertEquals(ConstantsApplication.FIELD_NAME, dtoList.get(ConstantsApplication.NUMBER_0).getName());
    }

    @Test
    void testArticlePriceResponseToArticlePriceResponseDtoList() {
        List<ArticlePriceResponse> articlePrices = Arrays.asList(
                new ArticlePriceResponse(ConstantsApplication.NUMBER_1, ConstantsApplication.PRICE
                        ,ConstantsApplication.NUMBER_1)
        );

        List<ArticlePriceResponseDto> dtoList = articleMapper
                .articlePriceResponseToArticlePriceResponseDtoList(articlePrices);

        Assertions.assertEquals(ConstantsApplication.NUMBER_1, dtoList.size());
        Assertions.assertEquals(ConstantsApplication.PRICE, dtoList.get(ConstantsApplication.NUMBER_0).getPrice());
    }

    @Test
    void testSubtractArticleRequestDtoToSubtractArticleRequest() {
        List<SubtractArticleRequestDto> requestDtos = Arrays.asList(
                new SubtractArticleRequestDto(ConstantsApplication.NUMBER_1, ConstantsApplication.NUMBER_2)
        );

        List<SubtractArticleRequest> requests = articleMapper
                .subtractArticleRequestDtoToSubtractArticleRequest(requestDtos);

        Assertions.assertEquals(ConstantsApplication.NUMBER_1, requests.size());
        Assertions.assertEquals(ConstantsApplication.NUMBER_1, requests
                .get(ConstantsApplication.NUMBER_0).getArticleId());
        Assertions.assertEquals(ConstantsApplication.NUMBER_2, requests
                .get(ConstantsApplication.NUMBER_0).getQuantity());
    }
}
