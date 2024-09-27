package com.emazon.api_stock.domain.api;

import com.emazon.api_stock.domain.model.ArticleCartRequest;
import com.emazon.api_stock.domain.model.ArticleResponse;
import com.emazon.api_stock.domain.model.ArticleSave;

import java.util.List;

public interface IArticleServicePort {

      void saveArticle(ArticleSave articleSave);

      List<ArticleResponse> getAllArticles(Integer page, Integer size, boolean descending, String filterBy);

      void updateQuantity(ArticleSave articleSave);

      ArticleResponse getArticlesById(Integer articleId);

      List<ArticleResponse> getArticleByIds( ArticleCartRequest articleCartRequest);
}
