package com.emazon.api_stock.infraestructure.output.jpa.adapter;

import com.emazon.api_stock.domain.model.ArticleXCategory;
import com.emazon.api_stock.domain.spi.IArticleXCategoryPersistencePort;
import com.emazon.api_stock.infraestructure.output.jpa.mapper.ArticleXCategoryEntityMapper;
import com.emazon.api_stock.infraestructure.output.jpa.repository.IArticleXCategoryRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ArticleXCategoryJpaAdapter implements IArticleXCategoryPersistencePort {

    private final IArticleXCategoryRepository articleXCategoryRepository;
    private final ArticleXCategoryEntityMapper articleXCategoryEntityMapper;

    @Override
    public void saveArticleXCategory(ArticleXCategory articleXCategory) {
        articleXCategoryRepository.save(articleXCategoryEntityMapper
                .articleXCategoryToArticleXCategoryEntity(articleXCategory));
    }
}
