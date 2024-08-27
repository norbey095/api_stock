package com.emazon.api_stock.infraestructure.output.jpa.adapter;

import com.emazon.api_stock.domain.model.Brand;
import com.emazon.api_stock.domain.spi.IBrandPersistencePort;
import com.emazon.api_stock.infraestructure.output.jpa.mapper.BrandEntityMapper;
import com.emazon.api_stock.infraestructure.output.jpa.repository.IBrandRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BrandJpaAdapter implements IBrandPersistencePort {

    private final IBrandRepository brandRepository;
    private final BrandEntityMapper brandEntityMapper;

    @Override
    public void saveBrand(Brand brand) {
        brandRepository.save(brandEntityMapper.brandToBrandEntity(brand));
    }

    @Override
    public boolean getBrandByName(String name) {
        return brandRepository.findByName(name).isPresent();
    }
}
