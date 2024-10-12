package com.emazon.api_stock.domain.api;

import com.emazon.api_stock.domain.model.*;

import java.util.List;

public interface IArticleServicePort {

      void saveArticle(ArticleSave articleSave);

      Pagination<ArticleResponse> getAllArticles(Integer page, Integer size, boolean descending, String filterBy);

      void updateQuantity(ArticleSave articleSave);

      ArticleResponse getArticlesById(Integer articleId);

      List<ArticleResponse> getArticleByIds(Integer page, Integer size,boolean descending,List<Integer> ids,
                                            String categoryName,String brandName);

      List<ArticlePriceResponse> getPriceByIds(List<Integer> articlesIds);

      void subtractQuantityArticle(List<SubtractArticleRequest> subtractArticleRequests);
}
