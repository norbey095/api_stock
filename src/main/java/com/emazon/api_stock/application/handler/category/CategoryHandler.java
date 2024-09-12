package com.emazon.api_stock.application.handler.category;

import com.emazon.api_stock.application.dto.ResponseSuccess;
import com.emazon.api_stock.application.dto.category.CategoryRequestDto;
import com.emazon.api_stock.application.dto.category.CategoryResponseDto;
import com.emazon.api_stock.application.mapper.CategoryMapper;
import com.emazon.api_stock.application.util.Constants;
import com.emazon.api_stock.domain.api.ICategoryServicePort;
import com.emazon.api_stock.domain.model.Category;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryHandler implements ICategoryHandler {

    private final ICategoryServicePort categoryServicePort;
    private final CategoryMapper categoryMapper;


    @Override
    public ResponseSuccess saveCategory(CategoryRequestDto categoryDto) {
        Category category = categoryMapper.categoryDtoToCategory(categoryDto);
        categoryServicePort.saveCategory(category);
        return new ResponseSuccess(Constants.CATEGORY_MESSAGES_SUCCESS);
    }

    @Override
    public List<CategoryResponseDto> getAllCategories(Integer page, Integer size, boolean descending) {
        return categoryMapper.toCategoryDtoList(categoryServicePort.getAllCategories(page,size,descending));
    }
}
