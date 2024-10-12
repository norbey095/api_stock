package com.emazon.api_stock.domain.api;

import com.emazon.api_stock.domain.model.Category;
import com.emazon.api_stock.domain.model.Pagination;

public interface ICategoryServicePort {

        void saveCategory(Category category);

        Pagination<Category> getAllCategories(Integer page, Integer size, boolean descending);

}
