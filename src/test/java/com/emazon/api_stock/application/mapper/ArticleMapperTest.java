package com.emazon.api_stock.application.mapper;

import com.emazon.api_stock.application.dto.article.ArticleRequestDto;
import com.emazon.api_stock.application.util.ConstantsTest;
import com.emazon.api_stock.domain.model.Article;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;

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


        Article article = articleMapper.articleDtoToArticle(articleDto);

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
        Article article = articleMapper.articleDtoToArticle(null);

        Assertions.assertNull(article);
    }
}
