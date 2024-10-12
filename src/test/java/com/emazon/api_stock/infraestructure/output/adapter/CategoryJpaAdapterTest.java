package com.emazon.api_stock.infraestructure.output.adapter;

import com.emazon.api_stock.domain.model.Category;
import com.emazon.api_stock.domain.model.Pagination;
import com.emazon.api_stock.infraestructure.output.entity.CategoryEntity;
import com.emazon.api_stock.infraestructure.output.mapper.CategoryEntityMapper;
import com.emazon.api_stock.infraestructure.output.repository.ICategoryRepository;
import com.emazon.api_stock.infraestructure.util.ConstantsInfraestructure;
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

        Category category = new Category(ConstantsInfraestructure.VALUE_1, ConstantsInfraestructure.FIELD_NAME
                , ConstantsInfraestructure.FIELD_DESCRIPTION);
        CategoryEntity categoryEntity = new CategoryEntity();
        Mockito.when(categoryEntityMapper.categoryToCategoryEntity(category)).thenReturn(categoryEntity);

        categoryJpaAdapter.saveCategory(category);

        Mockito.verify(categoryRepository, Mockito.times(ConstantsInfraestructure.VALUE_1)).save(categoryEntity);
    }

    @Test
    void testGetAllCategoriesSuccess() {
        Integer page = ConstantsInfraestructure.VALUE_0;
        Integer size = ConstantsInfraestructure.VALUE_1;

        List<CategoryEntity> categoryEntities = new ArrayList<>();
        categoryEntities.add(createCategoryEntity());

        List<Category> categories = new ArrayList<>();
        categories.add(createCategory());

        Pageable pagination = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, ConstantsInfraestructure.NAME));

        Mockito.when(categoryRepository.findAll(pagination)).thenReturn(new PageImpl<>(categoryEntities));
        Mockito.when(categoryEntityMapper.categoryEntityToCategory(categoryEntities)).thenReturn(categories);

        Pagination<Category> result = categoryJpaAdapter.getAllCategories(page, size, ConstantsInfraestructure.VALUE_FALSE);

        assertNotNull(result);
        assertEquals(categories.size(), result.getContentList().size());
        assertEquals(categories.get(ConstantsInfraestructure.VALUE_0).getName()
                , result.getContentList().get(ConstantsInfraestructure.VALUE_0).getName());
        assertEquals(categories.get(ConstantsInfraestructure.VALUE_0).getDescription()
                , result.getContentList().get(ConstantsInfraestructure.VALUE_0).getDescription());
    }

    @Test
    void testGetCategoryByNameSuccess() {
        CategoryEntity categoryEntity = new CategoryEntity();
        Mockito.when(categoryRepository.findByName(ConstantsInfraestructure.FIELD_NAME))
                .thenReturn(Optional.of(categoryEntity));

        boolean result = categoryJpaAdapter.getCategoryByName(ConstantsInfraestructure.FIELD_NAME);

        assertTrue(result);
    }

    private CategoryEntity createCategoryEntity(){
        CategoryEntity categoryEntity = new CategoryEntity();

        categoryEntity.setId(ConstantsInfraestructure.VALUE_1);
        categoryEntity.setName(ConstantsInfraestructure.COMILLAS);
        categoryEntity.setDescription(ConstantsInfraestructure.COMILLAS);

        return categoryEntity;
    }

    private Category createCategory(){
        return new Category(ConstantsInfraestructure.VALUE_1,ConstantsInfraestructure.COMILLAS
                ,ConstantsInfraestructure.COMILLAS);
    }
}
