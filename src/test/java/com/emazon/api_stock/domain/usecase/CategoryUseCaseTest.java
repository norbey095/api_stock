package com.emazon.api_stock.domain.usecase;

import com.emazon.api_stock.domain.exception.category.CategoryAlreadyExistsException;
import com.emazon.api_stock.domain.exception.category.InvalidCategoryDescriptionException;
import com.emazon.api_stock.domain.exception.category.InvalidCategoryNameException;
import com.emazon.api_stock.domain.model.Category;
import com.emazon.api_stock.domain.spi.ICategoryPersistencePort;
import com.emazon.api_stock.domain.util.ConstantsDomain;
import com.emazon.api_stock.domain.exception.PaginationNotAllowedException;
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
        Category category = new Category(ConstantsDomain.VALUE_1, ConstantsDomain.FIELD_NAME
                , ConstantsDomain.FIELD_DESCRIPTION);

        categoryUseCase.saveCategory(category);

        Mockito.verify(categoryPersistencePort, Mockito.times(ConstantsDomain.VALUE_1)).saveCategory(category);
    }

    @Test
    void shouldThrowsExceptionWhenCategoryExists() {
        Category category = new Category(ConstantsDomain.VALUE_1, ConstantsDomain.FIELD_NAME
                , ConstantsDomain.FIELD_DESCRIPTION);
        Mockito.when(categoryPersistencePort.getCategoryByName(category.getName()))
                .thenReturn(ConstantsDomain.VALUE_TRUE);

        assertThrows(CategoryAlreadyExistsException.class, () -> {
            categoryUseCase.saveCategory(category);
        });

        Mockito.verify(categoryPersistencePort, Mockito.times(ConstantsDomain.VALUE_1))
                .getCategoryByName(category.getName());
    }

    @Test
    void shouldThrowExceptionWhenNameExceeds50Characters() {
        Category category = new Category(ConstantsDomain.VALUE_1, ConstantsDomain.NAME_INVALID
                , ConstantsDomain.FIELD_DESCRIPTION);

        InvalidCategoryNameException exception = assertThrows(InvalidCategoryNameException.class, () -> {
            categoryUseCase.saveCategory(category);
        });

        assertEquals(ConstantsDomain.MSN_NAME_CARACTER, exception.getMessage());
    }


    @Test
    void shouldThrowExceptionWhenDescriptionExceeds90Characters() {
        Category category = new Category(ConstantsDomain.VALUE_1, ConstantsDomain.FIELD_NAME
                , ConstantsDomain.DESCRIPTION_INVALID);

        InvalidCategoryDescriptionException exception = assertThrows(InvalidCategoryDescriptionException.class, () -> {
            categoryUseCase.saveCategory(category);
        });

        assertEquals(ConstantsDomain.MSN_DESCRIPTION_CARACTER, exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenNameIsNull() {
        Category category = new Category(ConstantsDomain.VALUE_1, null, ConstantsDomain.FIELD_DESCRIPTION);

        InvalidCategoryNameException exception = assertThrows(InvalidCategoryNameException.class, () -> {
            categoryUseCase.saveCategory(category);
        });

        assertEquals(ConstantsDomain.MSN_NAME_NULL, exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenNameIsEmpty() {
        Category category = new Category(ConstantsDomain.VALUE_1, ConstantsDomain.COMILLAS,
                ConstantsDomain.FIELD_DESCRIPTION);

        InvalidCategoryNameException exception = assertThrows(InvalidCategoryNameException.class, () -> {
            categoryUseCase.saveCategory(category);
        });

        assertEquals(ConstantsDomain.MSN_NAME_NULL, exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenDescriptionIsNull() {
        Category category = new Category(ConstantsDomain.VALUE_1, ConstantsDomain.FIELD_NAME, null);

        InvalidCategoryDescriptionException exception = assertThrows(InvalidCategoryDescriptionException.class, () -> {
            categoryUseCase.saveCategory(category);
        });

        assertEquals(ConstantsDomain.MSN_DESCRIPTION_NULL, exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenDescriptionIsEmpty() {
        Category category = new Category(ConstantsDomain.VALUE_1, ConstantsDomain.FIELD_NAME,
                ConstantsDomain.COMILLAS);

        InvalidCategoryDescriptionException exception = assertThrows(InvalidCategoryDescriptionException.class, () -> {
            categoryUseCase.saveCategory(category);
        });

        assertEquals(ConstantsDomain.MSN_DESCRIPTION_NULL, exception.getMessage());
    }

    @Test
    void shouldGetAllCategories() {
        Category category = new Category(ConstantsDomain.VALUE_1, ConstantsDomain.FIELD_NAME, ConstantsDomain.COMILLAS);
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(category);

        Mockito.when(categoryPersistencePort.getAllCategories(ConstantsDomain.VALUE_1,ConstantsDomain.VALUE_1
                ,ConstantsDomain.VALUE_FALSE)).thenReturn(categoryList);

        List<Category> result = categoryUseCase.getAllCategories(ConstantsDomain.VALUE_1,ConstantsDomain.VALUE_1
                ,ConstantsDomain.VALUE_FALSE);

        Mockito.verify(categoryPersistencePort, Mockito.times(ConstantsDomain.VALUE_1))
                .getAllCategories(ConstantsDomain.VALUE_1,ConstantsDomain.VALUE_1,ConstantsDomain.VALUE_FALSE);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.get(ConstantsDomain.VALUE_0).getName(), category.getName());
        Assertions.assertEquals(result.get(ConstantsDomain.VALUE_0).getDescription(), category.getDescription());
    }

    @Test
    void testGetAllCategory_WhitPageNegative() {
        Integer page = ConstantsDomain.VALUE_N1;
        Integer size = ConstantsDomain.VALUE_1;

        assertThrows(PaginationNotAllowedException.class, () -> {
            categoryUseCase.getAllCategories(page, size, ConstantsDomain.VALUE_FALSE);
        });

        Mockito.verify(categoryPersistencePort, Mockito.times(ConstantsDomain.VALUE_0))
                .getAllCategories(page,size,ConstantsDomain.VALUE_FALSE);
    }

    @Test
    void testGetAllCategory_WhitSizeNegative() {
        Integer page = ConstantsDomain.VALUE_1;
        Integer size = ConstantsDomain.VALUE_N1;

        assertThrows(PaginationNotAllowedException.class, () -> {
            categoryUseCase.getAllCategories(page, size, ConstantsDomain.VALUE_FALSE);
        });

        Mockito.verify(categoryPersistencePort, Mockito.times(ConstantsDomain.VALUE_0))
                .getAllCategories(page,size,ConstantsDomain.VALUE_FALSE);
    }
}
