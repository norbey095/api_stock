package com.emazon.api_stock.infraestructure.output.jpa.adapter;

import com.emazon.api_stock.domain.model.Category;
import com.emazon.api_stock.infraestructure.exception.PaginationNotAllowedException;
import com.emazon.api_stock.infraestructure.output.jpa.entity.CategoryEntity;
import com.emazon.api_stock.infraestructure.output.jpa.mapper.CategoryEntityMapper;
import com.emazon.api_stock.infraestructure.output.jpa.repository.ICategoryRepository;
import com.emazon.api_stock.infraestructure.util.ConstantsTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CategoryJpaAdapterTest {

    @Mock
    private ICategoryRepository categoryRepository;

    @Mock
    private CategoryEntityMapper categoryEntityMapper;

    @Spy
    @InjectMocks
    private CategoryJpaAdapter categoryJpaAdapter;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveCategorySavesSuccessfully() {

        Category category = new Category(1,ConstantsTest.FIELD_NAME.getMessage()
                , ConstantsTest.FIELD_DESCRIPTION.getMessage());
        CategoryEntity categoryEntity = new CategoryEntity();
        Mockito.when(categoryEntityMapper.categoryToCategoryEntity(category)).thenReturn(categoryEntity);

        categoryJpaAdapter.saveCategory(category);

        Mockito.verify(categoryRepository, Mockito.times(1)).save(categoryEntity);
    }

    @Test
    void testGetAllCategoriesSuccess() {
        Integer page = 0;
        Integer size = 1;

        List<CategoryEntity> categoryEntities = new ArrayList<>();
        categoryEntities.add(createCategoryEntity());

        List<Category> categories = new ArrayList<>();
        categories.add(createCategory());

        Pageable pagination = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "name"));

        Mockito.doReturn(pagination).when(categoryJpaAdapter).createPageable(page, size, false);
        Mockito.when(categoryRepository.findAll(pagination)).thenReturn(new PageImpl<>(categoryEntities));
        Mockito.when(categoryEntityMapper.categoryEntityToCategory(categoryEntities)).thenReturn(categories);

        List<Category> result = categoryJpaAdapter.getAllCategories(page, size, false);

        assertNotNull(result);
        assertEquals(categories.size(), result.size());
        assertEquals(categories.get(0).getName(), result.get(0).getName());
        assertEquals(categories.get(0).getDescription(), result.get(0).getDescription());
    }

    @Test
    void testGetAllCategory_WhitPageNegative() {
        Integer page = -1;
        Integer size = 1;

        assertThrows(PaginationNotAllowedException.class, () -> {
            categoryJpaAdapter.getAllCategories(page, size, false);
        });

        Mockito.verify(categoryJpaAdapter, Mockito.times(0))
                .createPageable(1,1,false);
    }

    @Test
    void testGetAllCategory_WhitSizeNegative() {
        Integer page = 1;
        Integer size = -1;

        assertThrows(PaginationNotAllowedException.class, () -> {
            categoryJpaAdapter.getAllCategories(page, size, false);
        });

        Mockito.verify(categoryJpaAdapter, Mockito.times(0))
                .createPageable(1,1,false);
    }

    @Test
    void testGetCategoryByNameSuccess() {
        CategoryEntity categoryEntity = new CategoryEntity();
        Mockito.when(categoryRepository.findByName(ConstantsTest.FIELD_NAME.getMessage()))
                .thenReturn(Optional.of(categoryEntity));

        boolean result = categoryJpaAdapter.getCategoryByName(ConstantsTest.FIELD_NAME.getMessage());

        assertTrue(result);
    }

    private CategoryEntity createCategoryEntity(){
        CategoryEntity categoryEntity = new CategoryEntity();

        categoryEntity.setId(1);
        categoryEntity.setName("");
        categoryEntity.setDescription("");

        return categoryEntity;
    }

    private Category createCategory(){
        return new Category(1,"","");
    }
}
