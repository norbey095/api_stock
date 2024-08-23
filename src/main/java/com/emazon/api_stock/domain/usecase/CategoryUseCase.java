package com.emazon.api_stock.domain.usecase;

import com.emazon.api_stock.domain.api.ICategoryServicePort;
import com.emazon.api_stock.domain.exception.InvalidCategoryDescriptionException;
import com.emazon.api_stock.domain.exception.InvalidCategoryNameException;
import com.emazon.api_stock.domain.model.Category;
import com.emazon.api_stock.domain.spi.ICategoryPersistencePort;
import com.emazon.api_stock.domain.util.CategoryConstants;

import java.util.List;

public class CategoryUseCase implements ICategoryServicePort {

    private final ICategoryPersistencePort iCategoryPersistencePort;

    public CategoryUseCase(ICategoryPersistencePort iCategoryPersistencePort) {
        this.iCategoryPersistencePort = iCategoryPersistencePort;
    }

    @Override
    public void saveCategory(Category category) {
        if (category.getName() == null || category.getName().trim().isEmpty()) {
            throw new InvalidCategoryNameException(CategoryConstants.FIELD_NAME_NULL.getMessage());
        }
        if (category.getName().length() > 50) {
            throw new InvalidCategoryNameException(CategoryConstants.FIELD_NAME_MAX.getMessage());
        }
        if (category.getDescription() == null || category.getDescription().trim().isEmpty()) {
            throw new InvalidCategoryDescriptionException(CategoryConstants.FIELD_DESCRIPTION_NULL.getMessage());
        }
        if (category.getDescription().length() > 90) {
            throw new InvalidCategoryDescriptionException(CategoryConstants.FIELD_DESCRIPTION_MAX.getMessage());
        }
        this.iCategoryPersistencePort.saveCategory(category);
    }

    @Override
    public List<Category> getAllCategorys(Integer page, Integer size, boolean descending) {
        return this.iCategoryPersistencePort.getAllCategorys(page, size,descending);
    }

}