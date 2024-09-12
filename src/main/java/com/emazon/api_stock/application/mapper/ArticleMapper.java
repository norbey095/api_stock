package com.emazon.api_stock.application.mapper;

import com.emazon.api_stock.application.dto.article.ArticleRequestDto;
import com.emazon.api_stock.application.dto.article.ArticleResponseDto;
import com.emazon.api_stock.application.dto.category.CategoryResponseListDto;
import com.emazon.api_stock.application.util.Constants;
import com.emazon.api_stock.domain.model.ArticleResponse;
import com.emazon.api_stock.domain.model.ArticleSave;
import com.emazon.api_stock.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ArticleMapper {

    @Mapping(target = Constants.ID, ignore = true)
    ArticleSave articleDtoToArticle(ArticleRequestDto articleRequestDto);

    @Mapping(source = Constants.BRAND, target = Constants.BRAND)
    @Mapping(source = Constants.CATEGORIES, target = Constants.CATEGORIES)
    ArticleResponseDto toArticleDto(ArticleResponse article);

    @Mapping(source = Constants.BRAND, target = Constants.BRAND)
    @Mapping(source = Constants.CATEGORIES, target = Constants.CATEGORIES)
    List<ArticleResponseDto> toArticleDtoList(List<ArticleResponse> articles);

    CategoryResponseListDto toCategoryResponseListDto(Category category);

    List<CategoryResponseListDto> toCategoryResponseListDtoList(List<Category> categories);
}
