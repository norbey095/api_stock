package com.emazon.api_stock.infraestructure.output.adapter;

import com.emazon.api_stock.domain.model.Brand;
import com.emazon.api_stock.domain.model.Pagination;
import com.emazon.api_stock.domain.spi.IBrandPersistencePort;
import com.emazon.api_stock.infraestructure.output.entity.BrandEntity;
import com.emazon.api_stock.infraestructure.output.mapper.BrandEntityMapper;
import com.emazon.api_stock.infraestructure.output.repository.IBrandRepository;
import com.emazon.api_stock.infraestructure.utils.InfraestructureConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    public Pagination<Brand> getAllBrands(Integer page, Integer size, boolean descending) {
        Pageable pagination = createPageable(page, size, descending);
        Page<BrandEntity> brandEntities = brandRepository.findAll(pagination);
        List<Brand> brandList = brandEntityMapper.brandEntityToBrand(brandEntities.getContent());

        return new Pagination<>(
                brandList,
                brandEntities.getTotalElements()
        );
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
