package com.emazon.api_stock.infraestructure.output.jpa.adapter;

import com.emazon.api_stock.domain.model.Article;
import com.emazon.api_stock.domain.spi.IArticlePersistencePort;
import com.emazon.api_stock.infraestructure.output.jpa.entity.ArticleEntity;
import com.emazon.api_stock.infraestructure.output.jpa.mapper.ArticleEntityMapper;
import com.emazon.api_stock.infraestructure.output.jpa.repository.IArticleRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ArticleJpaAdapter implements IArticlePersistencePort {

    private final IArticleRepository articleRepository;
    private final ArticleEntityMapper articleEntityMapper;

    @Override
    public Integer saveArticle(Article article) {
        ArticleEntity articleEntity = articleRepository.save(articleEntityMapper.articleToArticleEntity(article));
        return articleEntity.getId();
    }
}
