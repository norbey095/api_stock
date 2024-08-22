package com.emazon.api_stock.domain.usecase;

import com.emazon.api_stock.domain.exception.InvalidCategoryDescriptionException;
import com.emazon.api_stock.domain.exception.InvalidCategoryNameException;
import com.emazon.api_stock.domain.model.Category;
import com.emazon.api_stock.domain.spi.ICategoryPersistencePort;
import com.emazon.api_stock.domain.util.ConstantsTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CategoryUseCaseTest {

    @Mock
    private ICategoryPersistencePort iCategoryPersistencePort;

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
        Mockito.verify(iCategoryPersistencePort, Mockito.times(1)).saveCategory(category);
    }

    @Test
    void shouldThrowExceptionWhenNameExceeds50Characters() {
        Category category = new Category(1,ConstantsTest.NAME_INVALID.getMessage() , ConstantsTest.FIELD_DESCRIPTION.getMessage());

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
    void shouldThrowExceptionWhenDescriptionIsNull() {
        Category category = new Category(1,ConstantsTest.FIELD_NAME.getMessage(), null);

        InvalidCategoryDescriptionException exception = assertThrows(InvalidCategoryDescriptionException.class, () -> {
            categoryUseCase.saveCategory(category);
        });

        assertEquals(ConstantsTest.MSN_DESCRIPTION_NULL.getMessage(), exception.getMessage());
    }

}
