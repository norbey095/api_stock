package com.emazon.api_stock.domain.api;

import com.emazon.api_stock.domain.model.Category;

public interface ICategoryServiceRepository {

        void saveCategory(Category category);

        Category getCategoryByName(String name);

}
