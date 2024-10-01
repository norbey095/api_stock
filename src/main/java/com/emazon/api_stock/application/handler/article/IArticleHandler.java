package com.emazon.api_stock.application.handler.article;

import com.emazon.api_stock.application.dto.ResponseSuccess;
import com.emazon.api_stock.application.dto.article.*;

import java.util.List;

public interface IArticleHandler {

    ResponseSuccess saveArticle(ArticleRequestDto articleRequestDto);

    List<ArticleResponseDto> getAllArticles(Integer page, Integer size, boolean descending, String filterBy);

    ResponseSuccess updateQuantity(ArticleUpdateRequestDto articleUpdateRequestDto);

    ArticleResponseDto getArticlesById(Integer articleId);

    List<ArticleResponseDto> getArticleByIds(Integer page, Integer size,boolean descending,List<Integer> ids,
                                             String categoryName,String brandName);

    List<ArticlePriceResponseDto> getPriceByIds(List<Integer> articlesIds);

    ResponseSuccess subtractQuantityArticle(List<SubtractArticleRequestDto> subtractArticleRequest);
}
