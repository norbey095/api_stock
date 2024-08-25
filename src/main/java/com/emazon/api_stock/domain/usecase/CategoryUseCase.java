package com.emazon.api_stock.domain.usecase;

import com.emazon.api_stock.domain.api.ICategoryServicePort;
import com.emazon.api_stock.domain.exception.InvalidCategoryDescriptionException;
import com.emazon.api_stock.domain.exception.InvalidCategoryNameException;
import com.emazon.api_stock.domain.model.Category;
import com.emazon.api_stock.domain.spi.ICategoryPersistencePort;
import com.emazon.api_stock.domain.util.CategoryConstants;
import com.emazon.api_stock.domain.exception.CategoryAlreadyExistsException;

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
    public List<Category> getAllCategorys(Integer page, Integer size, boolean descending) {
        return this.categoryPersistencePort.getAllCategorys(page, size,descending);
    }

    protected void validatedNamePresent(String name){
        if(this.categoryPersistencePort.getCategoryByName(name)) {
            throw new CategoryAlreadyExistsException();
        }
    }

    private void validatedName(String name){
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidCategoryNameException(CategoryConstants.FIELD_NAME_NULL.getMessage());
        }
        if (name.length() > 50) {
            throw new InvalidCategoryNameException(CategoryConstants.FIELD_NAME_MAX.getMessage());
        }
    }

    private void validatedDescription(String description){
        if (description == null || description.trim().isEmpty()) {
            throw new InvalidCategoryDescriptionException(CategoryConstants.FIELD_DESCRIPTION_NULL.getMessage());
        }
        if (description.length() > 90) {
            throw new InvalidCategoryDescriptionException(CategoryConstants.FIELD_DESCRIPTION_MAX.getMessage());
        }
    }
}