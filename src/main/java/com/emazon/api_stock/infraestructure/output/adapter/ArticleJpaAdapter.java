package com.emazon.api_stock.infraestructure.output.adapter;

import com.emazon.api_stock.domain.exception.article.TheArticleDoesNotExistException;
import com.emazon.api_stock.domain.model.ArticleResponse;
import com.emazon.api_stock.domain.model.ArticleSave;
import com.emazon.api_stock.domain.spi.IArticlePersistencePort;
import com.emazon.api_stock.infraestructure.output.entity.ArticleEntity;
import com.emazon.api_stock.infraestructure.output.mapper.ArticleEntityMapper;
import com.emazon.api_stock.infraestructure.output.repository.IArticleRepository;
import com.emazon.api_stock.infraestructure.utils.InfraestructureConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
public class ArticleJpaAdapter implements IArticlePersistencePort {

    private final IArticleRepository articleRepository;
    private final ArticleEntityMapper articleEntityMapper;

    @Override
    public Integer saveArticle(ArticleSave article) {
        ArticleEntity articleEntity = articleRepository.save(articleEntityMapper.articleSaveToArticleEntity(article));
        return articleEntity.getId();
    }

    @Override
    public List<ArticleResponse> getAllArticles(Integer page, Integer size, boolean descending, String filterBy) {
        Pageable pagination = createPageable(page, size, descending,filterBy);
        List<ArticleEntity> articleEntities = articleRepository.findAllItemsByBrandName(
                null,null,null,pagination).getContent();
        return articleEntityMapper.articleEntityToArticleResponseList(articleEntities);
    }

    @Override
    public boolean getArticleByName(String name) {
        return articleRepository.findByName(name).isPresent();
    }

    @Transactional
    @Override
    public void updateArticle(ArticleResponse articleResponse) {
        articleRepository.save(articleEntityMapper.articleToArticleEntity(articleResponse));
    }

    @Override
    public ArticleResponse getArticleById(Integer id) {
        ArticleEntity articleEntity = articleRepository.findById(id).orElseThrow(TheArticleDoesNotExistException::new);
        return articleEntityMapper.articleEntityToArticleResponse(articleEntity);
    }

    @Override
    public List<ArticleResponse> getArticleByIds(Integer page, Integer size, boolean descending,
                                                 List<Integer> articleIds, String categoryName, String brandName) {
        Pageable pagination = createPageable(page, size, descending,InfraestructureConstants.ARTICLE);
        List<ArticleEntity> articleEntities = articleRepository.findAllItemsByBrandName(
                brandName,categoryName,articleIds,pagination).getContent();
        return articleEntityMapper.articleEntityToArticleResponseList(articleEntities);
    }

    private Pageable createPageable(Integer page, Integer size, boolean descending,String filterBy) {
        Sort.Direction direction = descending ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sort = Sort.by(direction, getSortField(filterBy));
        return PageRequest.of(page, size, sort);
    }

    String getSortField(String filterBy) {
        return switch (filterBy.toLowerCase()) {
            case InfraestructureConstants.BRAND -> InfraestructureConstants.BRAND_NAME;
            case InfraestructureConstants.CATEGORY -> InfraestructureConstants.CATEGORY_NAME;
            default -> InfraestructureConstants.ARTICLE_NAME;
        };
    }
}
