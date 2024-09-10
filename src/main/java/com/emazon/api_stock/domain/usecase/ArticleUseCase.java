package com.emazon.api_stock.domain.usecase;

import com.emazon.api_stock.domain.api.IArticleServicePort;
import com.emazon.api_stock.domain.exception.article.ArticleAlreadyExistsException;
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
        validatedNamePresent(articleSave.getName());
        validatedNumberCategory(articleSave.getCategories());
        validatedUniqueCategory(articleSave.getCategories());
        saveArticleXCategory(this.articlePersistencePort.saveArticle(articleSave),articleSave.getCategories());
    }

    @Override
    public List<ArticleResponse> getAllArticles(Integer page, Integer size, boolean descending, String filterBy) {
        return this.articlePersistencePort.getAllArticles(page, size, descending, filterBy);
    }

    protected void validatedNamePresent(String name){
        if(this.articlePersistencePort.getArticleByName(name)) {
            throw new ArticleAlreadyExistsException();
        }
    }

    private void validatedNumberCategory(List<Integer> categories){
        if (categories == null || categories.isEmpty()) {
            throw new InvalidArticleCategoryException(Constants.FIELD_CATEGORIES_NULL.getMessage());
        }
        if (categories.size() > 3) {
            throw new InvalidArticleCategoryNumberException(Constants.FIELD_CATEGORIES_INVALID_NUMBER.getMessage());
        }
    }

    private void validatedUniqueCategory(List<Integer> categories){
        Map<Integer, Long> idCounts = categories.stream()
                .collect(Collectors.groupingBy(id -> id, Collectors.counting()));

        boolean result = idCounts.values().stream().anyMatch(count -> count > 1);
        if (result) {
            throw new RepeatedCategoryException(Constants.REPEATED_CATEGORIES.getMessage());
        }
    }

    private void saveArticleXCategory(Integer idArticle,List<Integer> categories) {
        List<ArticleXCategory> list = categories.stream().map(category ->
                    new ArticleXCategory(null, category,idArticle)).toList();
            list.forEach(articleXCategoryPersistencePort::saveArticleXCategory);
    }
}
