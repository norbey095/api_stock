package com.emazon.api_stock.infraestructure.configuration;

import com.emazon.api_stock.domain.api.IBrandServicePort;
import com.emazon.api_stock.domain.spi.IBrandPersistencePort;
import com.emazon.api_stock.domain.usecase.BrandUseCase;
import com.emazon.api_stock.infraestructure.output.adapter.BrandJpaAdapter;
import com.emazon.api_stock.infraestructure.output.mapper.BrandEntityMapper;
import com.emazon.api_stock.infraestructure.output.repository.IBrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanBrandConfiguration {

    private final IBrandRepository brandRepository;
    private final BrandEntityMapper brandEntityMapper;

    @Bean
    public IBrandPersistencePort iBrandPersistencePort(){
        return new BrandJpaAdapter(brandRepository,brandEntityMapper);
    }

    @Bean
    public IBrandServicePort brandServicePort(){
        return new BrandUseCase(iBrandPersistencePort());
    }

}
