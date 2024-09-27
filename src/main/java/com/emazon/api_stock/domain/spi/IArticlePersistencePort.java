package com.emazon.api_stock.domain.spi;

import com.emazon.api_stock.domain.model.ArticleResponse;
import com.emazon.api_stock.domain.model.ArticleSave;

import java.util.List;


public interface IArticlePersistencePort {

    Integer saveArticle(ArticleSave articleSave);

    List<ArticleResponse> getAllArticles(Integer page, Integer size, boolean descending, String filterBy);

    boolean getArticleByName(String name);

    void updateArticle(ArticleResponse articleSave);

    ArticleResponse getArticleById(Integer id);

    List<ArticleResponse> getArticleByIds(Integer page, Integer size, boolean descending, List<Integer> articleIds,
                                          String categoryName, String brandName);

}

