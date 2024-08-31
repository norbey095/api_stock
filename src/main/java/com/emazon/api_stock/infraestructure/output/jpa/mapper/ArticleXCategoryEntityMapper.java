package com.emazon.api_stock.infraestructure.output.jpa.mapper;

import com.emazon.api_stock.domain.model.ArticleXCategory;
import com.emazon.api_stock.infraestructure.output.jpa.entity.ArticleXCategoryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ArticleXCategoryEntityMapper {

    ArticleXCategoryEntity articleXCategoryToArticleXCategoryEntity(ArticleXCategory articleXCategory);
}
