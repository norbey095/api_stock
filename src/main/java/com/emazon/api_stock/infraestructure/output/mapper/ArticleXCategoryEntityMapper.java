package com.emazon.api_stock.infraestructure.output.mapper;

import com.emazon.api_stock.domain.model.ArticleXCategory;
import com.emazon.api_stock.infraestructure.output.entity.ArticleXCategoryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ArticleXCategoryEntityMapper {

    ArticleXCategoryEntity articleXCategoryToArticleXCategoryEntity(ArticleXCategory articleXCategory);
}
