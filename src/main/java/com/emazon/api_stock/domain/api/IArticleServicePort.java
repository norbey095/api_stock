package com.emazon.api_stock.domain.api;

import com.emazon.api_stock.domain.model.ArticlePriceResponse;
import com.emazon.api_stock.domain.model.ArticleResponse;
import com.emazon.api_stock.domain.model.ArticleSave;
import com.emazon.api_stock.domain.model.SubtractArticleRequest;

import java.util.List;

public interface IArticleServicePort {

      void saveArticle(ArticleSave articleSave);

      List<ArticleResponse> getAllArticles(Integer page, Integer size, boolean descending, String filterBy);

      void updateQuantity(ArticleSave articleSave);

      ArticleResponse getArticlesById(Integer articleId);

      List<ArticleResponse> getArticleByIds(Integer page, Integer size,boolean descending,List<Integer> ids,
                                            String categoryName,String brandName);

      List<ArticlePriceResponse> getPriceByIds(List<Integer> articlesIds);

      void subtractQuantityArticle(List<SubtractArticleRequest> subtractArticleRequests);
}
