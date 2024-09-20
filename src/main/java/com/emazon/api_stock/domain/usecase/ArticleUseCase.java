package com.emazon.api_stock.domain.usecase;

import com.emazon.api_stock.domain.api.IArticleServicePort;
import com.emazon.api_stock.domain.exception.NoDataFoundException;
import com.emazon.api_stock.domain.exception.PaginationNotAllowedException;
import com.emazon.api_stock.domain.exception.article.*;
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
        validatePaginationData(page,size);
        List<ArticleResponse> articleResponseList = this.articlePersistencePort.getAllArticles(page, size
                , descending, filterBy);
        validateData(articleResponseList);
        return articleResponseList;
    }

    @Override
    public void updateArticle(ArticleSave articleRequest){
        ArticleSave articleDataBase = getArticleDatabase(articleRequest.getId());
        updatedQuantity(articleRequest,articleDataBase);
        this.articlePersistencePort.updateArticle(articleDataBase);
    }

    @Override
    public boolean getArticlesById(Integer id) {
        ArticleSave articleSave = this.articlePersistencePort.getArticleById(id);
        return articleSave != null;
    }

    private void validatedNamePresent(String name){
        if(this.articlePersistencePort.getArticleByName(name)) {
            throw new ArticleAlreadyExistsException();
        }
    }

    private void validatedNumberCategory(List<Integer> categories){
        if (categories == null || categories.isEmpty()) {
            throw new InvalidArticleCategoryException(Constants.FIELD_CATEGORIES_NULL);
        }
        if (categories.size() > Constants.VALUE_3) {
            throw new InvalidArticleCategoryNumberException(Constants.FIELD_CATEGORIES_INVALID_NUMBER);
        }
    }

    private void validatedUniqueCategory(List<Integer> categories){
        Map<Integer, Long> idCounts = categories.stream()
                .collect(Collectors.groupingBy(id -> id, Collectors.counting()));

        boolean result = idCounts.values().stream().anyMatch(count -> count > Constants.VALUE_1);
        if (result) {
            throw new RepeatedCategoryException(Constants.REPEATED_CATEGORIES);
        }
    }

    private void saveArticleXCategory(Integer idArticle,List<Integer> categories) {
        List<ArticleXCategory> list = categories.stream().map(category ->
                    new ArticleXCategory(null, category,idArticle)).toList();
            list.forEach(articleXCategoryPersistencePort::saveArticleXCategory);
    }

    private void validatePaginationData(Integer page, Integer size){
        if (page == null || size == null){
            throw new PaginationNotAllowedException();
        }
        if (page < Constants.VALUE_0 || size <  Constants.VALUE_0) {
            throw new PaginationNotAllowedException();
        }
    }

    private void validateData(List<ArticleResponse> articleResponseList){
        if (articleResponseList.isEmpty()) {
            throw new NoDataFoundException();
        }
    }

    private ArticleSave getArticleDatabase(Integer id){
        ArticleSave articleSave = this.articlePersistencePort.getArticleById(id);
        if(articleSave == null) {
            throw new TheArticleDoesNotExistException();
        }
        return articleSave;
    }

    private void updatedQuantity(ArticleSave articleRequest,ArticleSave articleDataBase){
        Integer updatedQuantity = articleRequest.getQuantity() + articleDataBase.getQuantity();
        articleDataBase.setQuantity(updatedQuantity);
    }
}
