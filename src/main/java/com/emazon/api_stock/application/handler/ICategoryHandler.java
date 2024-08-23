package com.emazon.api_stock.application.handler;

import com.emazon.api_stock.application.dto.CategoryDto;

import java.util.List;

public interface ICategoryHandler {

    void saveCategory(CategoryDto categoryDto);

    List<CategoryDto> getAllCategorys(Integer page, Integer size, boolean descending);
}
