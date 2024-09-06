package com.emazon.api_stock.application.mapper;

import com.emazon.api_stock.application.dto.article.ArticleRequestDto;
import com.emazon.api_stock.application.dto.article.ArticleResponseDto;
import com.emazon.api_stock.application.util.ConstantsTest;
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
        articleDto.setName(ConstantsTest.FIELD_NAME.getMessage());
        articleDto.setDescription(ConstantsTest.FIELD_ARTICLE_DESCRIPTION.getMessage());
        articleDto.setQuantity(1);
        articleDto.setPrice(1);
        articleDto.setIdbrand(1);
        articleDto.setCategorys(new ArrayList<>());


        ArticleSave article = articleMapper.articleDtoToArticle(articleDto);

        Assertions.assertNotNull(article);
        Assertions.assertEquals(ConstantsTest.FIELD_NAME.getMessage(), article.getName());
        Assertions.assertEquals(ConstantsTest.FIELD_ARTICLE_DESCRIPTION.getMessage(), article.getDescription());
        Assertions.assertEquals(1, article.getQuantity());
        Assertions.assertEquals(1, article.getPrice());
        Assertions.assertEquals(1, article.getIdbrand());
        Assertions.assertEquals(new ArrayList<>(), article.getCategorys());
    }

    @Test
    void testArticleDtoToArticle_NullInput() {
        ArticleSave article = articleMapper.articleDtoToArticle(null);

        Assertions.assertNull(article);
    }

    @Test
    void testToArticleDtoList() {

        Brand brand = new Brand(1, com.emazon.api_stock.infraestructure.util.ConstantsTest.FIELD_NAME.getMessage()
                , com.emazon.api_stock.infraestructure.util.ConstantsTest.FIELD_ARTICLES_DESCRIPTION.getMessage());
        List<Category> categories = new ArrayList<>();
        categories.add(new Category());

        ArticleResponse articleResponse = new ArticleResponse(1, ConstantsTest.FIELD_NAME.getMessage()
                , ConstantsTest.FIELD_BRAND_DESCRIPTION.getMessage(), 1, 1000, brand,categories);

        List<ArticleResponse> articleList = new ArrayList<>();
        articleList.add(articleResponse);

        List<ArticleResponseDto> articleDtoList = articleMapper.toArticleDtoList(articleList);

        Assertions.assertNotNull(articleDtoList);
        Assertions.assertEquals(ConstantsTest.FIELD_NAME.getMessage(), articleDtoList.get(0).getName());
        Assertions.assertEquals(ConstantsTest.FIELD_BRAND_DESCRIPTION.getMessage(), articleDtoList.get(0).getDescription());
    }

    @Test
    void testToBrandDtoList_NullInput() {
        List<ArticleResponseDto> articleResponseDtos = articleMapper.toArticleDtoList(null);

        Assertions.assertNull(articleResponseDtos);
    }
}
