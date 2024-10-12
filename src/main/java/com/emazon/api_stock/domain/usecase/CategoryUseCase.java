package com.emazon.api_stock.domain.usecase;

import com.emazon.api_stock.domain.api.ICategoryServicePort;
import com.emazon.api_stock.domain.exception.category.InvalidCategoryDescriptionException;
import com.emazon.api_stock.domain.exception.category.InvalidCategoryNameException;
import com.emazon.api_stock.domain.model.Category;
import com.emazon.api_stock.domain.model.Pagination;
import com.emazon.api_stock.domain.spi.ICategoryPersistencePort;
import com.emazon.api_stock.domain.util.Constants;
import com.emazon.api_stock.domain.exception.category.CategoryAlreadyExistsException;
import com.emazon.api_stock.domain.exception.NoDataFoundException;
import com.emazon.api_stock.domain.exception.PaginationNotAllowedException;

public class CategoryUseCase implements ICategoryServicePort {

    private final ICategoryPersistencePort categoryPersistencePort;

    public CategoryUseCase(ICategoryPersistencePort iCategoryPersistencePort) {
        this.categoryPersistencePort = iCategoryPersistencePort;
    }

    @Override
    public void saveCategory(Category category) {
        validatedName(category.getName());
        validatedDescription(category.getDescription());
        validatedNamePresent(category.getName());
        this.categoryPersistencePort.saveCategory(category);
    }

    @Override
    public Pagination<Category> getAllCategories(Integer page, Integer size, boolean descending) {
        validateNegativeData(page,size);
        Pagination<Category> categoryList = this.categoryPersistencePort.getAllCategories(page, size,descending);
        validateData(categoryList);
        return categoryList;
    }

    private void validatedNamePresent(String name){
        if(this.categoryPersistencePort.getCategoryByName(name)) {
            throw new CategoryAlreadyExistsException();
        }
    }

    private void validatedName(String name){
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidCategoryNameException(Constants.FIELD_NAME_NULL);
        }
        if (name.length() > Constants.VALUE_50) {
            throw new InvalidCategoryNameException(Constants.FIELD_NAME_MAX);
        }
    }

    private void validatedDescription(String description){
        if (description == null || description.trim().isEmpty()) {
            throw new InvalidCategoryDescriptionException(Constants.FIELD_DESCRIPTION_NULL);
        }
        if (description.length() > Constants.VALUE_90) {
            throw new InvalidCategoryDescriptionException(Constants.FIELD_DESCRIPTION_CATEGORY_MAX);
        }
    }

    private void validateNegativeData(Integer page, Integer size){
        if (page == null || size == null){
            throw new PaginationNotAllowedException();
        }
        if (page < Constants.VALUE_0 || size < Constants.VALUE_0) {
            throw new PaginationNotAllowedException();
        }
    }

    private void validateData(Pagination<Category> categoryList){
        if (categoryList == null || categoryList.getContentList() == null || categoryList.getContentList().isEmpty()) {
            throw new NoDataFoundException();
        }
    }
}