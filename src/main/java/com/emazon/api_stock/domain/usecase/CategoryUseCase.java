package com.emazon.api_stock.domain.usecase;

import com.emazon.api_stock.domain.api.ICategoryServicePort;
import com.emazon.api_stock.domain.model.Category;
import com.emazon.api_stock.domain.spi.ICategoryPersistencePort;
import com.emazon.api_stock.domain.util.CategoryConstants;

public class CategoryUseCase implements ICategoryServicePort {

    private final ICategoryPersistencePort iCategoryPersistencePort;

    public CategoryUseCase(ICategoryPersistencePort iCategoryPersistencePort) {
        this.iCategoryPersistencePort = iCategoryPersistencePort;
    }

    @Override
    public void saveCategory(Category category) {
        if (category.getName() == null || category.getName().length() > 50) {
            throw new IllegalArgumentException(CategoryConstants.FIELD_NAME_MESSAGE);
        }
        if (category.getDescription() == null || category.getDescription() .length() > 90) {
            throw new IllegalArgumentException(CategoryConstants.FIELD_DESCRIPTION_MESSAGE);
        }
        this.iCategoryPersistencePort.saveCategory(category);
    }

}