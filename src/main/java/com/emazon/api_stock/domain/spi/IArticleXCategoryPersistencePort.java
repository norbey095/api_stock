package com.emazon.api_stock.domain.spi;

import com.emazon.api_stock.domain.model.ArticleXCategory;


public interface IArticleXCategoryPersistencePort {

    void saveArticleXCategory(ArticleXCategory articleXCategory);
}

