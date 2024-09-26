package com.emazon.api_stock.infraestructure.configuration;

import com.emazon.api_stock.domain.api.IArticleServicePort;
import com.emazon.api_stock.domain.spi.IArticlePersistencePort;
import com.emazon.api_stock.domain.spi.IArticleXCategoryPersistencePort;
import com.emazon.api_stock.domain.usecase.ArticleUseCase;
import com.emazon.api_stock.infraestructure.output.adapter.ArticleJpaAdapter;
import com.emazon.api_stock.infraestructure.output.adapter.ArticleXCategoryJpaAdapter;
import com.emazon.api_stock.infraestructure.output.mapper.ArticleEntityMapper;
import com.emazon.api_stock.infraestructure.output.mapper.ArticleXCategoryEntityMapper;
import com.emazon.api_stock.infraestructure.output.repository.IArticleRepository;
import com.emazon.api_stock.infraestructure.output.repository.IArticleXCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanArticleConfiguration {

    private final IArticleRepository articleRepository;
    private final ArticleEntityMapper articleEntityMapper;
    private final IArticleXCategoryRepository articleXCategoryRepository;
    private final ArticleXCategoryEntityMapper articleXCategoryEntityMapper;

    @Bean
    public IArticlePersistencePort iArticlePersistencePort(){
        return new ArticleJpaAdapter(articleRepository,articleEntityMapper);
    }

    @Bean
    public IArticleXCategoryPersistencePort articleXCategoryPersistencePort(){
        return new ArticleXCategoryJpaAdapter(articleXCategoryRepository,articleXCategoryEntityMapper);
    }

    @Bean
    public IArticleServicePort articleServicePort(){
        return new ArticleUseCase(iArticlePersistencePort(),articleXCategoryPersistencePort());
    }

}
