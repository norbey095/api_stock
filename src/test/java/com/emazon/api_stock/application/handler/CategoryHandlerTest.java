package com.emazon.api_stock.application.handler;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.emazon.api_stock.application.dto.CategoryDto;
import com.emazon.api_stock.application.mapper.CategoryMapper;
import com.emazon.api_stock.application.util.ConstantsTest;
import com.emazon.api_stock.domain.api.ICategoryServicePort;
import com.emazon.api_stock.domain.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class CategoryHandlerTest {

    @InjectMocks
    private CategoryHandler categoryHandler;

    @Mock
    private ICategoryServicePort categoryServicePort;

    @Mock
    private CategoryMapper categoryMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldSaveCategory() {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName(ConstantsTest.FIELD_NAME.getMessage());
        categoryDto.setDescription(ConstantsTest.FIELD_DESCRIPTION.getMessage());

        Category category = new Category(null,ConstantsTest.FIELD_NAME.getMessage()
                ,ConstantsTest.FIELD_DESCRIPTION.getMessage());
        when(categoryMapper.categoryDtoToCategory(categoryDto)).thenReturn(category);

        categoryHandler.saveCategory(categoryDto);


        verify(categoryMapper).categoryDtoToCategory(categoryDto);
        verify(categoryServicePort).saveCategory(category);
    }
}
