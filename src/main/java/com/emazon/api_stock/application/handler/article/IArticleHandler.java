package com.emazon.api_stock.application.handler.article;

import com.emazon.api_stock.application.dto.ResponseSuccess;
import com.emazon.api_stock.application.dto.article.ArticleRequestDto;

public interface IArticleHandler {

    ResponseSuccess saveArticle(ArticleRequestDto articleRequestDto);
}
