package com.emazon.api_stock.infraestructure.output.jpa.adapter;

import com.emazon.api_stock.domain.model.ArticleResponse;
import com.emazon.api_stock.domain.model.ArticleSave;
import com.emazon.api_stock.domain.spi.IArticlePersistencePort;
import com.emazon.api_stock.infraestructure.output.jpa.entity.ArticleEntity;
import com.emazon.api_stock.infraestructure.output.jpa.mapper.ArticleEntityMapper;
import com.emazon.api_stock.infraestructure.output.jpa.repository.IArticleRepository;
import com.emazon.api_stock.infraestructure.utils.InfraestructureConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@RequiredArgsConstructor
public class ArticleJpaAdapter implements IArticlePersistencePort {

    private final IArticleRepository articleRepository;
    private final ArticleEntityMapper articleEntityMapper;

    @Override
    public Integer saveArticle(ArticleSave article) {
        ArticleEntity articleEntity = articleRepository.save(articleEntityMapper.articleToArticleEntity(article));
        return articleEntity.getId();
    }

    @Override
    public List<ArticleResponse> getAllArticles(Integer page, Integer size, boolean descending, String filterBy) {
        Pageable pagination = createPageable(page, size, descending,filterBy);
        List<ArticleEntity> articleEntities = articleRepository.findAllItemsByBrandName(pagination).getContent();
        return articleEntityMapper.articleEntityToArticleResponse(articleEntities);
    }

    @Override
    public boolean getArticleByName(String name) {
        return articleRepository.findByName(name).isPresent();
    }

    private Pageable createPageable(Integer page, Integer size, boolean descending,String filterBy) {
        Sort.Direction direction = descending ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sort = Sort.by(direction, getSortField(filterBy));
        return PageRequest.of(page, size, sort);
    }

    private String getSortField(String filterBy) {
        return switch (filterBy.toLowerCase()) {
            case InfraestructureConstants.BRAND -> InfraestructureConstants.BRAND_NAME;
            case InfraestructureConstants.CATEGORY -> InfraestructureConstants.CATEGORY_NAME;
            default -> InfraestructureConstants.ARTICLE_NAME;
        };
    }
}
