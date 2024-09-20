package com.emazon.api_stock.infraestructure.configuration;

import com.emazon.api_stock.domain.api.ICategoryServicePort;
import com.emazon.api_stock.domain.spi.ICategoryPersistencePort;
import com.emazon.api_stock.domain.usecase.CategoryUseCase;
import com.emazon.api_stock.infraestructure.output.adapter.CategoryJpaAdapter;
import com.emazon.api_stock.infraestructure.output.mapper.CategoryEntityMapper;
import com.emazon.api_stock.infraestructure.output.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanCategoryConfiguration {

    private final ICategoryRepository categoryRepository;
    private final CategoryEntityMapper categoryEntityMapper;

    @Bean
    public ICategoryPersistencePort categoryPersistencePort(){
        return new CategoryJpaAdapter(categoryRepository,categoryEntityMapper);
    }

    @Bean
    public ICategoryServicePort categoryServicePort(){
        return new CategoryUseCase(categoryPersistencePort());
    }

}
