package com.emazon.api_stock.application.handler.category;

import com.emazon.api_stock.application.dto.ResponseSuccess;
import com.emazon.api_stock.application.dto.category.CategoryRequestDto;
import com.emazon.api_stock.application.dto.category.CategoryResponseDto;

import java.util.List;

public interface ICategoryHandler {

    ResponseSuccess saveCategory(CategoryRequestDto categoryDto);

    List<CategoryResponseDto> getAllCategories(Integer page, Integer size, boolean descending);
}
