package com.emazon.api_stock.application.mapper;

import com.emazon.api_stock.application.dto.article.ArticleRequestDto;
import com.emazon.api_stock.application.dto.article.ArticleResponseDto;
import com.emazon.api_stock.application.dto.category.CategoryResponseListDto;
import com.emazon.api_stock.domain.model.ArticleResponse;
import com.emazon.api_stock.domain.model.ArticleSave;
import com.emazon.api_stock.domain.model.Brand;
import com.emazon.api_stock.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ArticleMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "articleRequestDto.name", target = "name")
    @Mapping(source = "articleRequestDto.description", target = "description")
    @Mapping(source = "articleRequestDto.quantity", target = "quantity")
    @Mapping(source = "articleRequestDto.price", target = "price")
    @Mapping(source = "articleRequestDto.idbrand", target = "idbrand")
    @Mapping(source = "articleRequestDto.categorys", target = "categorys")
    ArticleSave articleDtoToArticle(ArticleRequestDto articleRequestDto);

    @Mapping(source = "brand.id", target = "brand")
    @Mapping(source = "categories", target = "categorys")
    ArticleResponseDto toArticleDto(ArticleResponse article);

    @Mapping(source = "brand.id", target = "brand")
    @Mapping(source = "categories", target = "categorys")
    List<ArticleResponseDto> toArticleDtoList(List<ArticleResponse> articles);

    CategoryResponseListDto toCategoryResponseListDto(Category category);

    List<CategoryResponseListDto> toCategoryResponseListDtoList(List<Category> categories);

    default Integer map(Brand brand) {
        return brand != null ? brand.getId() : null;
    }
}
