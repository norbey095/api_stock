package com.emazon.api_stock.domain.spi;

import com.emazon.api_stock.domain.model.*;

import java.util.List;


public interface IArticlePersistencePort {

    Integer saveArticle(ArticleSave articleSave);

    Pagination<ArticleResponse> getAllArticles(Integer page, Integer size, boolean descending, String filterBy);

    boolean getArticleByName(String name);

    void updateArticle(Integer id,Integer quantity);

    ArticleResponse getArticleById(Integer id);

    List<ArticleResponse> getArticleByIds(Integer page, Integer size, boolean descending, List<Integer> articleIds,
                                          String categoryName, String brandName);

    List<ArticlePriceResponse> getPriceByIds(List<Integer> articlesIds);
}

