package com.emazon.api_stock.application.handler;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.emazon.api_stock.application.dto.category.CategoryRequestDto;
import com.emazon.api_stock.application.dto.category.CategoryResponseDto;
import com.emazon.api_stock.application.handler.category.CategoryHandler;
import com.emazon.api_stock.application.mapper.CategoryMapper;
import com.emazon.api_stock.application.util.ConstantsTest;
import com.emazon.api_stock.domain.api.ICategoryServicePort;
import com.emazon.api_stock.domain.model.Category;
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
        categoryDto.setName(ConstantsTest.FIELD_NAME.getMessage());
        categoryDto.setDescription(ConstantsTest.FIELD_DESCRIPTION.getMessage());

        categoryResponseDto = new CategoryResponseDto();
        categoryResponseDto.setName(ConstantsTest.FIELD_NAME.getMessage());
        categoryResponseDto.setDescription(ConstantsTest.FIELD_DESCRIPTION.getMessage());
    }

    @Test
    void shouldSaveCategory() {
        Category category = new Category(null,ConstantsTest.FIELD_NAME.getMessage()
                ,ConstantsTest.FIELD_DESCRIPTION.getMessage());
        when(categoryMapper.categoryDtoToCategory(categoryDto)).thenReturn(category);

        categoryHandler.saveCategory(categoryDto);


        verify(categoryMapper).categoryDtoToCategory(categoryDto);
        verify(categoryServicePort).saveCategory(category);
    }

    @Test
    void shouldGetAllCategory() {
        List<CategoryResponseDto> categoryDtoList = new ArrayList<>();
        categoryDtoList.add(categoryResponseDto);

        Category category = new Category(null,ConstantsTest.FIELD_NAME.getMessage()
                ,ConstantsTest.FIELD_DESCRIPTION.getMessage());
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(category);

        when(categoryMapper.toCategoryDtoList(categoryList)).thenReturn(categoryDtoList);
        when(categoryServicePort.getAllCategories(1,1,false)).thenReturn(categoryList);

        categoryHandler.getAllCategories(1,1,false);


        verify(categoryMapper).toCategoryDtoList(categoryList);
        verify(categoryServicePort).getAllCategories(1,1,false);
    }
}
