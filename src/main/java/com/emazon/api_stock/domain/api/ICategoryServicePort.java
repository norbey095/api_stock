package com.emazon.api_stock.domain.api;

import com.emazon.api_stock.domain.model.Category;

import java.util.List;

public interface ICategoryServicePort {

        void saveCategory(Category category);

        List<Category> getAllCategorys(Integer page, Integer size, boolean descending);

}
