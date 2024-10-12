package com.emazon.api_stock.application.mapper;

import com.emazon.api_stock.application.dto.PaginationDto;
import com.emazon.api_stock.application.dto.article.*;
import com.emazon.api_stock.application.dto.category.CategoryResponseListDto;
import com.emazon.api_stock.application.util.ConstantsMapper;
import com.emazon.api_stock.domain.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ArticleMapper {

    @Mapping(target = ConstantsMapper.ID, ignore = true)
    ArticleSave articleDtoToArticle(ArticleRequestDto articleRequestDto);

    @Mapping(source = "articleUpdateRequestDto.articleId", target = "id")
    ArticleSave articleUpdateDtoToArticlesave(ArticleUpdateRequestDto articleUpdateRequestDto);

    @Mapping(source = ConstantsMapper.BRAND, target = ConstantsMapper.BRAND)
    @Mapping(source = ConstantsMapper.CATEGORIES, target = ConstantsMapper.CATEGORIES)
    ArticleResponseDto toArticleDto(ArticleResponse article);

    PaginationDto<ArticleResponseDto> toArticleDtoListPagination(Pagination<ArticleResponse> articles);

    List<ArticleResponseDto> toArticleDtoList(List<ArticleResponse> articles);

    CategoryResponseListDto toCategoryResponseListDto(Category category);

    List<CategoryResponseListDto> toCategoryResponseListDtoList(List<Category> categories);

    List<ArticlePriceResponseDto> articlePriceResponseToArticlePriceResponseDtoList
            (List<ArticlePriceResponse> articlePriceResponse);

    List<SubtractArticleRequest> subtractArticleRequestDtoToSubtractArticleRequest(
            List<SubtractArticleRequestDto> subtractArticleRequest);
}
