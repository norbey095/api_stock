package com.emazon.api_stock.domain.spi;

import com.emazon.api_stock.domain.model.Category;

public interface ICategoryPersistencePort {

    void saveCategory(Category category);

    Category getCategoryByName(String name);

}
