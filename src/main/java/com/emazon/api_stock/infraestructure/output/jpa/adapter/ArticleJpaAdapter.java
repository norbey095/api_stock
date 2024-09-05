package com.emazon.api_stock.infraestructure.output.jpa.adapter;

import com.emazon.api_stock.domain.model.ArticleResponse;
import com.emazon.api_stock.domain.model.ArticleSave;
import com.emazon.api_stock.domain.spi.IArticlePersistencePort;
import com.emazon.api_stock.infraestructure.exception.NoDataFoundException;
import com.emazon.api_stock.infraestructure.exception.PaginationNotAllowedException;
import com.emazon.api_stock.infraestructure.output.jpa.entity.ArticleEntity;
import com.emazon.api_stock.infraestructure.output.jpa.mapper.ArticleEntityMapper;
import com.emazon.api_stock.infraestructure.output.jpa.repository.IArticleRepository;
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
        validatePaginationData(page,size);
        Pageable pagination = createPageable(page, size, descending,filterBy);
        List<ArticleEntity> articleEntities = fetchArticle(pagination);
        return articleEntityMapper.articleEntityToArticleResponse(articleEntities);
    }

    private void validatePaginationData(Integer page, Integer size){
        if (page == null || size == null){
            throw new PaginationNotAllowedException();
        }
        if (page < 0 || size < 0) {
            throw new PaginationNotAllowedException();
        }
    }

    protected Pageable createPageable(Integer page, Integer size, boolean descending,String filterBy) {
        Sort.Direction direction = descending ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sort = Sort.by(direction, getSortField(filterBy));
        return PageRequest.of(page, size, sort);
    }

    private String getSortField(String filterBy) {
        switch (filterBy.toLowerCase()) {
            case "brand":
                return "b.name";
            case "category":
                return "c.name";
            case "article":
            default:
                return "a.name";
        }
    }


    private List<ArticleEntity> fetchArticle(Pageable pageable) {
        List<ArticleEntity> articles = articleRepository.findAllItemsByBrandName(pageable).getContent();
        if (articles.isEmpty()) {
            throw new NoDataFoundException();
        }
        return articles;
    }
}
