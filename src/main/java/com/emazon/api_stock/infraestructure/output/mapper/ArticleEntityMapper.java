package com.emazon.api_stock.infraestructure.output.mapper;

import com.emazon.api_stock.domain.model.ArticlePriceResponse;
import com.emazon.api_stock.domain.model.ArticleResponse;
import com.emazon.api_stock.domain.model.ArticleSave;
import com.emazon.api_stock.infraestructure.output.entity.ArticleEntity;
import com.emazon.api_stock.infraestructure.utils.InfraestructureConstants;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ArticleEntityMapper {

    @Mapping(target = InfraestructureConstants.CATEGORIES, ignore = true)
    @Mapping(source = "articleResponse.brand.id", target = "idbrand")
    ArticleEntity articleToArticleEntity(ArticleResponse articleResponse);

    @Mapping(target = InfraestructureConstants.CATEGORIES, ignore = true)
    @Mapping(target = InfraestructureConstants.BRAND, ignore = true)
    ArticleEntity articleSaveToArticleEntity(ArticleSave articleSave);

    @Mapping(target = InfraestructureConstants.ID_BRAND, ignore = true)
    List<ArticleResponse> articleEntityToArticleResponseList(List<ArticleEntity> articleEntity);

    @Mapping(target = InfraestructureConstants.CATEGORIES, ignore = true)
    ArticleSave articleEntityToArticleSave(ArticleEntity articleEntity);

    ArticleResponse articleEntityToArticleResponse(ArticleEntity articleEntity);

    @Mapping(source = "articleEntity.id", target = "id")
    @Mapping(source = "articleEntity.price", target = "price")
    @Mapping(source = "articleEntity.quantity", target = "quantity")
    List<ArticlePriceResponse> articleEntityToArticlePriceResponse
            (List<ArticleEntity> articleEntity);

}
