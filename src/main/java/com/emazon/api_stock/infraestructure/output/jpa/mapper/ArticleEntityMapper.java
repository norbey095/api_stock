package com.emazon.api_stock.infraestructure.output.jpa.mapper;

import com.emazon.api_stock.domain.model.Article;
import com.emazon.api_stock.infraestructure.output.jpa.entity.ArticleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ArticleEntityMapper {

    @Mapping(target = "article.categorys", ignore = true)
    ArticleEntity articleToArticleEntity(Article article);
}
