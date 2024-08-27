package com.emazon.api_stock.application.handler;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.emazon.api_stock.application.dto.CategoryDto;
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

    private CategoryDto categoryDto;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        categoryDto = new CategoryDto();
        categoryDto.setName(ConstantsTest.FIELD_NAME.getMessage());
        categoryDto.setDescription(ConstantsTest.FIELD_DESCRIPTION.getMessage());
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
        List<CategoryDto> categoryDtoList = new ArrayList<>();
        categoryDtoList.add(categoryDto);

        Category category = new Category(null,ConstantsTest.FIELD_NAME.getMessage()
                ,ConstantsTest.FIELD_DESCRIPTION.getMessage());
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(category);

        when(categoryMapper.toCategoryDtoList(categoryList)).thenReturn(categoryDtoList);
        when(categoryServicePort.getAllCategorys(1,1,false)).thenReturn(categoryList);

        categoryHandler.getAllCategorys(1,1,false);


        verify(categoryMapper).toCategoryDtoList(categoryList);
        verify(categoryServicePort).getAllCategorys(1,1,false);
    }
}
