package com.emazon.api_stock.domain.usecase;

import com.emazon.api_stock.domain.api.IArticleServicePort;
import com.emazon.api_stock.domain.exception.article.InvalidArticleCategoryException;
import com.emazon.api_stock.domain.exception.article.InvalidArticleCategoryNumberException;
import com.emazon.api_stock.domain.exception.article.RepeatedCategoryException;
import com.emazon.api_stock.domain.model.ArticleResponse;
import com.emazon.api_stock.domain.model.ArticleSave;
import com.emazon.api_stock.domain.model.ArticleXCategory;
import com.emazon.api_stock.domain.spi.IArticlePersistencePort;
import com.emazon.api_stock.domain.spi.IArticleXCategoryPersistencePort;
import com.emazon.api_stock.domain.util.Constants;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ArticleUseCase implements IArticleServicePort {

    private final IArticlePersistencePort articlePersistencePort;
    private final IArticleXCategoryPersistencePort articleXCategoryPersistencePort;

    public ArticleUseCase(IArticlePersistencePort articlePersistencePort,
                          IArticleXCategoryPersistencePort articleXCategoryPersistencePort) {
        this.articlePersistencePort = articlePersistencePort;
        this.articleXCategoryPersistencePort = articleXCategoryPersistencePort;
    }

    @Override
    public void saveArticle(ArticleSave articleSave) {
        validatedNumberCategory(articleSave.getCategorys());
        validatedUniqueCategory(articleSave.getCategorys());
        saveArticleXCategory(this.articlePersistencePort.saveArticle(articleSave),articleSave.getCategorys());
    }

    @Override
    public List<ArticleResponse> getAllArticles(Integer page, Integer size, boolean descending, String filterBy) {
        return this.articlePersistencePort.getAllArticles(page, size, descending, filterBy);
    }

    private void validatedNumberCategory(List<Integer> categorys){
        if (categorys == null || categorys.isEmpty()) {
            throw new InvalidArticleCategoryException(Constants.FIELD_CATEGORYS_NULL.getMessage());
        }
        if (categorys.size() > 3) {
            throw new InvalidArticleCategoryNumberException(Constants.FIELD_CATEGORYS_INVALID_NUMBER.getMessage());
        }
    }

    private void validatedUniqueCategory(List<Integer> categorys){
        Map<Integer, Long> idCounts = categorys.stream()
                .collect(Collectors.groupingBy(id -> id, Collectors.counting()));

        boolean result = idCounts.values().stream().anyMatch(count -> count > 1);
        if (result) {
            throw new RepeatedCategoryException(Constants.REPEATED_CATEGORIES.getMessage());
        }
    }

    private void saveArticleXCategory(Integer idArticle,List<Integer> categorys) {
        List<ArticleXCategory> list = categorys.stream().map(category ->
                    new ArticleXCategory(null, category,idArticle)).toList();
            list.forEach(articleXCategoryPersistencePort::saveArticleXCategory);
    }
}
