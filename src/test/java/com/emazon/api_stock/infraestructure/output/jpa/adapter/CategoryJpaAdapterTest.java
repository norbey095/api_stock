package com.emazon.api_stock.infraestructure.output.jpa.adapter;

import com.emazon.api_stock.domain.model.Category;
import com.emazon.api_stock.infraestructure.exception.CategoryAlreadyExistsException;
import com.emazon.api_stock.infraestructure.output.jpa.entity.CategoryEntity;
import com.emazon.api_stock.infraestructure.output.jpa.mapper.CategoryEntityMapper;
import com.emazon.api_stock.infraestructure.output.jpa.repository.ICategoryRepository;
import com.emazon.api_stock.infraestructure.util.ConstantsTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CategoryJpaAdapterTest {

    @Mock
    private ICategoryRepository categoryRepository;

    @Mock
    private CategoryEntityMapper categoryEntityMapper;

    @InjectMocks
    private CategoryJpaAdapter categoryJpaAdapter;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveCategoryThrowsExceptionWhenCategoryExists() {
        Category category = new Category(1, ConstantsTest.FIELD_NAME.getMessage()
                , ConstantsTest.FIELD_DESCRIPTION.getMessage());

        Mockito.when(categoryRepository.findByName(ConstantsTest.FIELD_NAME.getMessage()))
                .thenReturn(Optional.of(new CategoryEntity()));

        assertThrows(CategoryAlreadyExistsException.class, () -> {
            categoryJpaAdapter.saveCategory(category);
        });

        Mockito.verify(categoryRepository, Mockito.never()).save(Mockito.any(CategoryEntity.class));
    }

    @Test
    void testSaveCategorySavesSuccessfullyWhenCategoryDoesNotExist() {

        Category category = new Category(1,ConstantsTest.FIELD_NAME.getMessage()
                , ConstantsTest.FIELD_DESCRIPTION.getMessage());
        CategoryEntity categoryEntity = new CategoryEntity();
        Mockito.when(categoryRepository.findByName(ConstantsTest.FIELD_SEARCH_NAME.getMessage()))
                .thenReturn(Optional.empty());
        Mockito.when(categoryEntityMapper.categoyToCategoryEntity(category)).thenReturn(categoryEntity);

        categoryJpaAdapter.saveCategory(category);

        Mockito.verify(categoryRepository, Mockito.times(1)).save(categoryEntity);
    }
}
