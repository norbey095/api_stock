package com.emazon.api_stock.infraestructure.output.jpa.mapper;

import com.emazon.api_stock.domain.model.ArticleResponse;
import com.emazon.api_stock.domain.model.ArticleSave;
import com.emazon.api_stock.infraestructure.output.jpa.entity.ArticleEntity;
import com.emazon.api_stock.infraestructure.utils.InfraestructureConstants;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ArticleEntityMapper {

    @Mapping(target = InfraestructureConstants.CATEGORIES, ignore = true)
    ArticleEntity articleToArticleEntity(ArticleSave article);

    @Mapping(target = InfraestructureConstants.ID_BRAND, ignore = true)
    List<ArticleResponse> articleEntityToArticleResponse(List<ArticleEntity> articleEntity);

    @Mapping(target = InfraestructureConstants.CATEGORIES, ignore = true)
    ArticleSave articleEntityToArticleSave(ArticleEntity articleEntity);

}
