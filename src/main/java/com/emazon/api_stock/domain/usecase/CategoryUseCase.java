package com.emazon.api_stock.domain.usecase;

import com.emazon.api_stock.domain.api.ICategoryServicePort;
import com.emazon.api_stock.domain.exception.category.InvalidCategoryDescriptionException;
import com.emazon.api_stock.domain.exception.category.InvalidCategoryNameException;
import com.emazon.api_stock.domain.model.Category;
import com.emazon.api_stock.domain.spi.ICategoryPersistencePort;
import com.emazon.api_stock.domain.util.Constants;
import com.emazon.api_stock.domain.exception.category.CategoryAlreadyExistsException;

import java.util.List;

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
    public List<Category> getAllCategories(Integer page, Integer size, boolean descending) {
        return this.categoryPersistencePort.getAllCategories(page, size,descending);
    }

    protected void validatedNamePresent(String name){
        if(this.categoryPersistencePort.getCategoryByName(name)) {
            throw new CategoryAlreadyExistsException();
        }
    }

    private void validatedName(String name){
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidCategoryNameException(Constants.FIELD_NAME_NULL.getMessage());
        }
        if (name.length() > 50) {
            throw new InvalidCategoryNameException(Constants.FIELD_NAME_MAX.getMessage());
        }
    }

    private void validatedDescription(String description){
        if (description == null || description.trim().isEmpty()) {
            throw new InvalidCategoryDescriptionException(Constants.FIELD_DESCRIPTION_NULL.getMessage());
        }
        if (description.length() > 90) {
            throw new InvalidCategoryDescriptionException(Constants.FIELD_DESCRIPTION_CATEGORY_MAX.getMessage());
        }
    }
}