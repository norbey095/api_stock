package com.emazon.api_stock.domain.spi;

import com.emazon.api_stock.domain.model.Category;
import com.emazon.api_stock.domain.model.Pagination;

public interface ICategoryPersistencePort {

    void saveCategory(Category category);

    Pagination<Category> getAllCategories(Integer page, Integer size, boolean descending);

    boolean getCategoryByName(String name);
}

