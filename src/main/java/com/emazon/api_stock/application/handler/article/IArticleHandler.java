package com.emazon.api_stock.application.handler.article;

import com.emazon.api_stock.application.dto.ResponseSuccess;
import com.emazon.api_stock.application.dto.article.ArticleRequestDto;
import com.emazon.api_stock.application.dto.article.ArticleResponseDto;
import com.emazon.api_stock.application.dto.article.ArticleUpdateRequestDto;

import java.util.List;

public interface IArticleHandler {

    ResponseSuccess saveArticle(ArticleRequestDto articleRequestDto);

    List<ArticleResponseDto> getAllArticles(Integer page, Integer size, boolean descending, String filterBy);

    ResponseSuccess updateArticle(ArticleUpdateRequestDto articleUpdateRequestDto);

}
