package com.emazon.api_stock.domain.spi;

import com.emazon.api_stock.domain.model.ArticleResponse;
import com.emazon.api_stock.domain.model.ArticleSave;

import java.util.List;


public interface IArticlePersistencePort {

    Integer saveArticle(ArticleSave articleSave);

    List<ArticleResponse> getAllArticles(Integer page, Integer size, boolean descending, String filterBy);
}

