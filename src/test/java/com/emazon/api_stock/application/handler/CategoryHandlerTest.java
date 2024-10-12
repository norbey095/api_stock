package com.emazon.api_stock.application.handler;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.emazon.api_stock.application.dto.PaginationDto;
import com.emazon.api_stock.application.dto.category.CategoryRequestDto;
import com.emazon.api_stock.application.dto.category.CategoryResponseDto;
import com.emazon.api_stock.application.handler.category.CategoryHandler;
import com.emazon.api_stock.application.mapper.CategoryMapper;
import com.emazon.api_stock.application.util.ConstantsApplication;
import com.emazon.api_stock.domain.api.ICategoryServicePort;
import com.emazon.api_stock.domain.model.Category;
import com.emazon.api_stock.domain.model.Pagination;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

class CategoryHandlerTest {

    @InjectMocks
    private CategoryHandler categoryHandler;

    @Mock
    private ICategoryServicePort categoryServicePort;

    @Mock
    private CategoryMapper categoryMapper;

    private CategoryRequestDto categoryDto;

    private CategoryResponseDto categoryResponseDto;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        categoryDto = new CategoryRequestDto();
        categoryDto.setName(ConstantsApplication.FIELD_NAME);
        categoryDto.setDescription(ConstantsApplication.FIELD_DESCRIPTION);

        categoryResponseDto = new CategoryResponseDto();
        categoryResponseDto.setName(ConstantsApplication.FIELD_NAME);
        categoryResponseDto.setDescription(ConstantsApplication.FIELD_DESCRIPTION);
    }

    @Test
    void shouldSaveCategory() {
        Category category = new Category(null, ConstantsApplication.FIELD_NAME
                , ConstantsApplication.FIELD_DESCRIPTION);
        when(categoryMapper.categoryDtoToCategory(categoryDto)).thenReturn(category);

        categoryHandler.saveCategory(categoryDto);


        verify(categoryMapper).categoryDtoToCategory(categoryDto);
        verify(categoryServicePort).saveCategory(category);
    }

    @Test
    void shouldGetAllCategory() {
        List<CategoryResponseDto> categoryDtoList = new ArrayList<>();
        categoryDtoList.add(categoryResponseDto);

        PaginationDto<CategoryResponseDto> paginationDto = new PaginationDto<>();
        paginationDto.setContentList(categoryDtoList);

        Category category = new Category(null, ConstantsApplication.FIELD_NAME
                , ConstantsApplication.FIELD_DESCRIPTION);
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(category);

        Pagination<Category> pagination = new Pagination<>();
        pagination.setContentList(categoryList);

        when(categoryMapper.toCategoryDtoList(pagination)).thenReturn(paginationDto);
        when(categoryServicePort.getAllCategories(ConstantsApplication.NUMBER_1, ConstantsApplication.NUMBER_1,
                ConstantsApplication.VALUE_FALSE)).thenReturn(pagination);

        categoryHandler.getAllCategories(ConstantsApplication.NUMBER_1, ConstantsApplication.NUMBER_1
                , ConstantsApplication.VALUE_FALSE);


        verify(categoryMapper).toCategoryDtoList(pagination);
        verify(categoryServicePort).getAllCategories(ConstantsApplication.NUMBER_1, ConstantsApplication.NUMBER_1,
                ConstantsApplication.VALUE_FALSE);
    }
}
