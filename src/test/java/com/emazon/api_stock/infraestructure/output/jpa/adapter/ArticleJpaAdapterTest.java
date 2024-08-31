package com.emazon.api_stock.infraestructure.output.jpa.adapter;

import com.emazon.api_stock.domain.model.Article;
import com.emazon.api_stock.infraestructure.output.jpa.entity.ArticleEntity;
import com.emazon.api_stock.infraestructure.output.jpa.mapper.ArticleEntityMapper;
import com.emazon.api_stock.infraestructure.output.jpa.repository.IArticleRepository;
import com.emazon.api_stock.infraestructure.util.ConstantsTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.Collections;

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

        Article article = new Article(1,ConstantsTest.FIELD_NAME.getMessage()
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
}
