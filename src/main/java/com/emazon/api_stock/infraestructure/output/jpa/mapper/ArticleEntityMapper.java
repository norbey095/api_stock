package com.emazon.api_stock.infraestructure.output.jpa.mapper;

import com.emazon.api_stock.domain.model.ArticleResponse;
import com.emazon.api_stock.domain.model.ArticleSave;
import com.emazon.api_stock.infraestructure.output.jpa.entity.ArticleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ArticleEntityMapper {

    ArticleEntity articleToArticleEntity(ArticleSave article);

    @Mapping(target = "idbrand", ignore = true)
    List<ArticleResponse> articleEntityToArticleResponse(List<ArticleEntity> articleEntity);

}
