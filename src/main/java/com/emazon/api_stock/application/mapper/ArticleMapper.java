package com.emazon.api_stock.application.mapper;

import com.emazon.api_stock.application.dto.article.ArticleRequestDto;
import com.emazon.api_stock.domain.model.Article;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ArticleMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "articleRequestDto.name", target = "name")
    @Mapping(source = "articleRequestDto.description", target = "description")
    @Mapping(source = "articleRequestDto.quantity", target = "quantity")
    @Mapping(source = "articleRequestDto.price", target = "price")
    @Mapping(source = "articleRequestDto.idbrand", target = "idbrand")
    @Mapping(source = "articleRequestDto.categorys", target = "categorys")
    Article articleDtoToArticle(ArticleRequestDto articleRequestDto);
}
