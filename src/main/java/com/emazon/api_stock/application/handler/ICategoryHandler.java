package com.emazon.api_stock.application.handler;

import com.emazon.api_stock.application.dto.CategoryDto;

public interface ICategoryHandler {

    void saveCategory(CategoryDto categoryDto);

    CategoryDto getCategoryByName(String name);

}
