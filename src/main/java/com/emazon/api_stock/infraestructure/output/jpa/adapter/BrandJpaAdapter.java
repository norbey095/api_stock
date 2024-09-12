package com.emazon.api_stock.infraestructure.output.jpa.adapter;

import com.emazon.api_stock.domain.model.Brand;
import com.emazon.api_stock.domain.spi.IBrandPersistencePort;
import com.emazon.api_stock.infraestructure.output.jpa.entity.BrandEntity;
import com.emazon.api_stock.infraestructure.output.jpa.mapper.BrandEntityMapper;
import com.emazon.api_stock.infraestructure.output.jpa.repository.IBrandRepository;
import com.emazon.api_stock.infraestructure.utils.InfraestructureConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@RequiredArgsConstructor
public class BrandJpaAdapter implements IBrandPersistencePort {

    private final IBrandRepository brandRepository;
    private final BrandEntityMapper brandEntityMapper;

    @Override
    public void saveBrand(Brand brand) {
        brandRepository.save(brandEntityMapper.brandToBrandEntity(brand));
    }

    @Override
    public List<Brand> getAllBrands(Integer page, Integer size, boolean descending) {
        Pageable pagination = createPageable(page, size, descending);
        List<BrandEntity> brandEntities = brandRepository.findAll(pagination).getContent();
        return brandEntityMapper.brandEntityToBrand(brandEntities);
    }

    @Override
    public boolean getBrandByName(String name) {
        return brandRepository.findByName(name).isPresent();
    }

    private Pageable createPageable(Integer page, Integer size, boolean descending) {
        Sort.Direction direction = descending ? Sort.Direction.DESC : Sort.Direction.ASC;
        return PageRequest.of(page, size, Sort.by(direction, InfraestructureConstants.NAME));
    }
}
