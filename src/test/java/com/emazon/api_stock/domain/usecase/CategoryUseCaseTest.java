package com.emazon.api_stock.domain.usecase;

import com.emazon.api_stock.domain.exception.category.CategoryAlreadyExistsException;
import com.emazon.api_stock.domain.exception.category.InvalidCategoryDescriptionException;
import com.emazon.api_stock.domain.exception.category.InvalidCategoryNameException;
import com.emazon.api_stock.domain.model.Category;
import com.emazon.api_stock.domain.spi.ICategoryPersistencePort;
import com.emazon.api_stock.domain.util.ConstantsTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CategoryUseCaseTest {

    @Mock
    private ICategoryPersistencePort categoryPersistencePort;

    @InjectMocks
    private CategoryUseCase categoryUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCallSaveCategoryInPersistencePort() {
        // Arrange - Preparar
        Category category = new Category(1, ConstantsTest.FIELD_NAME.getMessage()
                , ConstantsTest.FIELD_DESCRIPTION.getMessage());

        //Act - Actuar
        categoryUseCase.saveCategory(category);

        // Assert - Afirmar
        Mockito.verify(categoryPersistencePort, Mockito.times(1)).saveCategory(category);
    }

    @Test
    void shouldThrowsExceptionWhenCategoryExists() {
        String name = ConstantsTest.FIELD_NAME.getMessage();
        Mockito.when(categoryPersistencePort.getCategoryByName(name))
                .thenReturn(true);

        assertThrows(CategoryAlreadyExistsException.class, () -> {
            categoryUseCase.validatedNamePresent(name);
        });

        Mockito.verify(categoryPersistencePort, Mockito.times(1))
                .getCategoryByName(name);
    }

    @Test
    void shouldThrowExceptionWhenNameExceeds50Characters() {
        Category category = new Category(1,ConstantsTest.NAME_INVALID.getMessage()
                , ConstantsTest.FIELD_DESCRIPTION.getMessage());

        InvalidCategoryNameException exception = assertThrows(InvalidCategoryNameException.class, () -> {
            categoryUseCase.saveCategory(category);
        });

        assertEquals(ConstantsTest.MSN_NAME_CARACTER.getMessage(), exception.getMessage());
    }


    @Test
    void shouldThrowExceptionWhenDescriptionExceeds90Characters() {
        Category category = new Category(1, ConstantsTest.FIELD_NAME.getMessage()
                , ConstantsTest.DESCRIPTION_INVALID.getMessage());

        InvalidCategoryDescriptionException exception = assertThrows(InvalidCategoryDescriptionException.class, () -> {
            categoryUseCase.saveCategory(category);
        });

        assertEquals(ConstantsTest.MSN_DESCRIPTION_CARACTER.getMessage(), exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenNameIsNull() {
        Category category = new Category(1, null, ConstantsTest.FIELD_DESCRIPTION.getMessage());

        InvalidCategoryNameException exception = assertThrows(InvalidCategoryNameException.class, () -> {
            categoryUseCase.saveCategory(category);
        });

        assertEquals(ConstantsTest.MSN_NAME_NULL.getMessage(), exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenNameIsEmpty() {
        Category category = new Category(1, "", ConstantsTest.FIELD_DESCRIPTION.getMessage());

        InvalidCategoryNameException exception = assertThrows(InvalidCategoryNameException.class, () -> {
            categoryUseCase.saveCategory(category);
        });

        assertEquals(ConstantsTest.MSN_NAME_NULL.getMessage(), exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenDescriptionIsNull() {
        Category category = new Category(1,ConstantsTest.FIELD_NAME.getMessage(), null);

        InvalidCategoryDescriptionException exception = assertThrows(InvalidCategoryDescriptionException.class, () -> {
            categoryUseCase.saveCategory(category);
        });

        assertEquals(ConstantsTest.MSN_DESCRIPTION_NULL.getMessage(), exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenDescriptionIsEmpty() {
        Category category = new Category(1,ConstantsTest.FIELD_NAME.getMessage(), "");

        InvalidCategoryDescriptionException exception = assertThrows(InvalidCategoryDescriptionException.class, () -> {
            categoryUseCase.saveCategory(category);
        });

        assertEquals(ConstantsTest.MSN_DESCRIPTION_NULL.getMessage(), exception.getMessage());
    }

    @Test
    void shouldGetAllCategorys() {
        Category category = new Category(1,ConstantsTest.FIELD_NAME.getMessage(), "");
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(category);

        Mockito.when(categoryPersistencePort.getAllCategorys(1,1,false)).thenReturn(categoryList);

        List<Category> result = categoryUseCase.getAllCategorys(1,1,false);

        Mockito.verify(categoryPersistencePort, Mockito.times(1))
                .getAllCategorys(1,1,false);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.get(0).getName(), category.getName());
        Assertions.assertEquals(result.get(0).getDescription(), category.getDescription());
    }
}
