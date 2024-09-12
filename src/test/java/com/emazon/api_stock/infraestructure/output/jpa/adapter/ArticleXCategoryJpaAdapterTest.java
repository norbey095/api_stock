package com.emazon.api_stock.infraestructure.output.jpa.adapter;

import com.emazon.api_stock.domain.model.ArticleXCategory;
import com.emazon.api_stock.infraestructure.output.jpa.entity.ArticleXCategoryEntity;
import com.emazon.api_stock.infraestructure.output.jpa.mapper.ArticleXCategoryEntityMapper;
import com.emazon.api_stock.infraestructure.output.jpa.repository.IArticleXCategoryRepository;
import com.emazon.api_stock.infraestructure.util.ConstantsInfraestructure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class ArticleXCategoryJpaAdapterTest {

    @Mock
    private IArticleXCategoryRepository articleXCategoryRepository;

    @Mock
    private ArticleXCategoryEntityMapper articleXCategoryEntityMapper;

    @Spy
    @InjectMocks
    private ArticleXCategoryJpaAdapter articleXCategoryJpaAdapter;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveArticleXCategorySavesSuccessfully() {

        ArticleXCategory articleXCategory = new ArticleXCategory(ConstantsInfraestructure.VALUE_1
                , ConstantsInfraestructure.VALUE_1, ConstantsInfraestructure.VALUE_1);
        ArticleXCategoryEntity articleXCategoryEntity = new ArticleXCategoryEntity();
        ArticleXCategoryEntity articleXCategoryEntityResponse = new ArticleXCategoryEntity();
        articleXCategoryEntityResponse.setId(ConstantsInfraestructure.VALUE_1);

        Mockito.when(articleXCategoryEntityMapper.articleXCategoryToArticleXCategoryEntity(articleXCategory))
                .thenReturn(articleXCategoryEntity);
        Mockito.when(articleXCategoryRepository.save(articleXCategoryEntity)).thenReturn(articleXCategoryEntityResponse);

        articleXCategoryJpaAdapter.saveArticleXCategory(articleXCategory);

        Mockito.verify(articleXCategoryJpaAdapter, Mockito.times(ConstantsInfraestructure.VALUE_1))
                .saveArticleXCategory(articleXCategory);
    }
}
