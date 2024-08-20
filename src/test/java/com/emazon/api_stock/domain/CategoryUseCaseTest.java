package com.emazon.api_stock.domain;

import com.emazon.api_stock.domain.model.Category;
import com.emazon.api_stock.domain.spi.ICategoryPersistencePort;
import com.emazon.api_stock.domain.usecase.CategoryUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class CategoryUseCaseTest {

    private static final String NAME_VALID = "Toilet";
    private static final String DESCRIPTION_VALID = "Toilet area";

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
        Category category = new Category(1, NAME_VALID, DESCRIPTION_VALID);

        //Act - Actuar
        categoryUseCase.saveCategory(category);

        // Assert - Afirmar
        Mockito.verify(iCategoryPersistencePort, Mockito.times(1)).saveCategory(category);
    }


}
