package com.emazon.api_stock.infraestructure.output.jpa.adapter;

import com.emazon.api_stock.domain.model.ArticleResponse;
import com.emazon.api_stock.domain.model.ArticleSave;
import com.emazon.api_stock.domain.model.Brand;
import com.emazon.api_stock.domain.model.Category;
import com.emazon.api_stock.infraestructure.output.jpa.entity.ArticleEntity;
import com.emazon.api_stock.infraestructure.output.jpa.mapper.ArticleEntityMapper;
import com.emazon.api_stock.infraestructure.output.jpa.repository.IArticleRepository;
import com.emazon.api_stock.infraestructure.util.ConstantsInfraestructure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ArticleJpaAdapterTest {

    @Mock
    private IArticleRepository articleRepository;

    @Mock
    private ArticleEntityMapper articleEntityMapper;

    @Spy
    @InjectMocks
    private ArticleJpaAdapter articleJpaAdapter;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveArticleSavesSuccessfully() {

        ArticleSave article = new ArticleSave(ConstantsInfraestructure.VALUE_1, ConstantsInfraestructure.FIELD_NAME
                , ConstantsInfraestructure.FIELD_ARTICLE_DESCRIPTION
                , ConstantsInfraestructure.VALUE_1, ConstantsInfraestructure.PRICE, ConstantsInfraestructure.VALUE_1
                , Collections.singletonList(ConstantsInfraestructure.VALUE_1));
        ArticleEntity articleEntity = new ArticleEntity();
        ArticleEntity articleEntityResponse = new ArticleEntity();
        articleEntityResponse.setId(ConstantsInfraestructure.VALUE_1);

        Mockito.when(articleEntityMapper.articleToArticleEntity(article)).thenReturn(articleEntity);
        Mockito.when(articleRepository.save(articleEntity)).thenReturn(articleEntityResponse);

        articleJpaAdapter.saveArticle(article);

        Mockito.verify(articleRepository, Mockito.times(ConstantsInfraestructure.VALUE_1)).save(articleEntity);
    }

    @Test
    void testGetAllArticleSuccess() {
        Integer page = ConstantsInfraestructure.VALUE_0;
        Integer size = ConstantsInfraestructure.VALUE_1;

        List<ArticleEntity> articleEntities = new ArrayList<>();
        articleEntities.add(createArticleEntity());

        List<ArticleResponse> articleResponse = new ArrayList<>();
        articleResponse.add(createArticle());

        Pageable pagination = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC,
                ConstantsInfraestructure.ARTICLE_NAME));

        Mockito.when(articleRepository.findAllItemsByBrandName(pagination)).thenReturn(new PageImpl<>(articleEntities));
        Mockito.when(articleEntityMapper.articleEntityToArticleResponse(articleEntities)).thenReturn(articleResponse);

        List<ArticleResponse> result = articleJpaAdapter.getAllArticles(page, size,
                ConstantsInfraestructure.VALUE_FALSE,
                ConstantsInfraestructure.ARTICLE);

        assertNotNull(result);
        assertEquals(articleResponse.size(), result.size());
        assertEquals(articleResponse.get(ConstantsInfraestructure.VALUE_0).getName(),
                result.get(ConstantsInfraestructure.VALUE_0).getName());
        assertEquals(articleResponse.get(ConstantsInfraestructure.VALUE_0).getDescription(),
                result.get(ConstantsInfraestructure.VALUE_0).getDescription());
    }



    @Test
    void testGetArticleByNameSuccess() {
        ArticleEntity articleEntity = new ArticleEntity();
        Mockito.when(articleRepository.findByName(ConstantsInfraestructure.FIELD_NAME))
                .thenReturn(Optional.of(articleEntity));

        boolean result = articleJpaAdapter.getArticleByName(ConstantsInfraestructure.FIELD_NAME);

        assertTrue(result);
    }

    private ArticleEntity createArticleEntity(){
        ArticleEntity articleEntity = new ArticleEntity();

        articleEntity.setId(ConstantsInfraestructure.VALUE_1);
        articleEntity.setName(ConstantsInfraestructure.COMILLAS);
        articleEntity.setDescription(ConstantsInfraestructure.COMILLAS);

        return articleEntity;
    }

    private ArticleResponse createArticle(){
        Brand brand = new Brand(ConstantsInfraestructure.VALUE_1, ConstantsInfraestructure.FIELD_NAME
                , ConstantsInfraestructure.FIELD_DESCRIPTION);
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category(ConstantsInfraestructure.VALUE_1, ConstantsInfraestructure.FIELD_NAME
                , ConstantsInfraestructure.FIELD_DESCRIPTION));

        return new ArticleResponse(ConstantsInfraestructure.VALUE_1, ConstantsInfraestructure.FIELD_NAME
                , ConstantsInfraestructure.FIELD_ARTICLES_DESCRIPTION,ConstantsInfraestructure.VALUE_2
                , ConstantsInfraestructure.PRICE, brand,categoryList);
    }
}
