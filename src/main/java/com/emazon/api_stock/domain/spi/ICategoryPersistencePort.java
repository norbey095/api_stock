package com.emazon.api_stock.domain.spi;

import com.emazon.api_stock.domain.model.Category;

import java.util.List;

public interface ICategoryPersistencePort {

    void saveCategory(Category category);

    List<Category> getAllCategorys(Integer page, Integer size,boolean descending);

}
