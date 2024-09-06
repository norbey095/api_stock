package com.emazon.api_stock.infraestructure.output.jpa.adapter;

import com.emazon.api_stock.domain.model.ArticleResponse;
import com.emazon.api_stock.domain.model.ArticleSave;
import com.emazon.api_stock.domain.model.Brand;
import com.emazon.api_stock.domain.model.Category;
import com.emazon.api_stock.infraestructure.exception.PaginationNotAllowedException;
import com.emazon.api_stock.infraestructure.output.jpa.entity.ArticleEntity;
import com.emazon.api_stock.infraestructure.output.jpa.mapper.ArticleEntityMapper;
import com.emazon.api_stock.infraestructure.output.jpa.repository.IArticleRepository;
import com.emazon.api_stock.infraestructure.util.ConstantsTest;
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
import static org.junit.jupiter.api.Assertions.assertThrows;

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

        ArticleSave article = new ArticleSave(1,ConstantsTest.FIELD_NAME.getMessage()
                , ConstantsTest.FIELD_ARTICLE_DESCRIPTION.getMessage()
                , 1, 2.0, 1, Collections.singletonList(1));
        ArticleEntity articleEntity = new ArticleEntity();
        ArticleEntity articleEntityResponse = new ArticleEntity();
        articleEntityResponse.setId(1);

        Mockito.when(articleEntityMapper.articleToArticleEntity(article)).thenReturn(articleEntity);
        Mockito.when(articleRepository.save(articleEntity)).thenReturn(articleEntityResponse);

        articleJpaAdapter.saveArticle(article);

        Mockito.verify(articleRepository, Mockito.times(1)).save(articleEntity);
    }

    @Test
    void testGetAllArticleSuccess() {
        Integer page = 0;
        Integer size = 1;

        List<ArticleEntity> articleEntities = new ArrayList<>();
        articleEntities.add(createArticleEntity());

        List<ArticleResponse> articleResponse = new ArrayList<>();
        articleResponse.add(createArticle());

        Pageable pagination = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "a.name"));

        Mockito.doReturn(pagination).when(articleJpaAdapter).createPageable(page, size,
                false, "a.name");
        Mockito.when(articleRepository.findAllItemsByBrandName(pagination)).thenReturn(new PageImpl<>(articleEntities));
        Mockito.when(articleEntityMapper.articleEntityToArticleResponse(articleEntities)).thenReturn(articleResponse);

        List<ArticleResponse> result = articleJpaAdapter.getAllArticles(page, size, false,
                 "article");

        assertNotNull(result);
        assertEquals(articleResponse.size(), result.size());
        assertEquals(articleResponse.get(0).getName(), result.get(0).getName());
        assertEquals(articleResponse.get(0).getDescription(), result.get(0).getDescription());
    }

    @Test
    void testGetAllBrand_WhitPageNegative() {
        Integer page = -1;
        Integer size = 1;

        assertThrows(PaginationNotAllowedException.class, () -> {
            articleJpaAdapter.getAllArticles(page, size, false,"a.name");
        });

        Mockito.verify(articleJpaAdapter, Mockito.times(0))
                .createPageable(1,1,false, "a.name");
    }

    @Test
    void testGetAllBrand_WhitSizeNegative() {
        Integer page = 1;
        Integer size = -1;

        assertThrows(PaginationNotAllowedException.class, () -> {
            articleJpaAdapter.getAllArticles(page, size, false, "a.name");
        });

        Mockito.verify(articleJpaAdapter, Mockito.times(0))
                .createPageable(1,1,false, "a.name");
    }

    @Test
    void testGetArticleByNameSuccess() {
        ArticleEntity articleEntity = new ArticleEntity();
        Mockito.when(articleRepository.findByName(ConstantsTest.FIELD_NAME.getMessage()))
                .thenReturn(Optional.of(articleEntity));

        boolean result = articleJpaAdapter.getArticleByName(ConstantsTest.FIELD_NAME.getMessage());

        assertTrue(result);
    }

    private ArticleEntity createArticleEntity(){
        ArticleEntity articleEntity = new ArticleEntity();

        articleEntity.setId(1);
        articleEntity.setName("");
        articleEntity.setDescription("");

        return articleEntity;
    }

    private ArticleResponse createArticle(){
        Brand brand = new Brand(1,ConstantsTest.FIELD_NAME.getMessage()
                , ConstantsTest.FIELD_DESCRIPTION.getMessage());
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category(1, ConstantsTest.FIELD_NAME.getMessage()
                ,ConstantsTest.FIELD_DESCRIPTION.getMessage()));

        return new ArticleResponse(1, ConstantsTest.FIELD_NAME.getMessage()
                ,ConstantsTest.FIELD_ARTICLES_DESCRIPTION.getMessage(),2, 2000, brand,categoryList);
    }
}
