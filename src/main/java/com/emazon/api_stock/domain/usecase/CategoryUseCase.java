package com.emazon.api_stock.domain.usecase;

import com.emazon.api_stock.domain.api.ICategoryServiceRepository;
import com.emazon.api_stock.domain.model.Category;
import com.emazon.api_stock.domain.spi.ICategoryPersistencePort;

public class CategoryUseCase implements ICategoryServiceRepository {


    private final ICategoryPersistencePort iCategoryPersistencePort;

    public CategoryUseCase(ICategoryPersistencePort iCategoryPersistencePort) {
        this.iCategoryPersistencePort = iCategoryPersistencePort;
    }

    @Override
    public void saveCategory(Category category) {
        this.iCategoryPersistencePort.saveCategory(category);
    }

    public Category getCategoryByName(String name) {
        return this.iCategoryPersistencePort.getCategoryByName(name);
    }
}
