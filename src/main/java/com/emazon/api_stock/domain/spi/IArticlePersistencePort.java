package com.emazon.api_stock.domain.spi;

import com.emazon.api_stock.domain.model.Article;


public interface IArticlePersistencePort {

    Integer saveArticle(Article article);
}

