package com.emazon.api_stock.application.handler.brand;

import com.emazon.api_stock.application.dto.BrandDto;
import com.emazon.api_stock.application.mapper.BrandMapper;
import com.emazon.api_stock.domain.api.IBrandServicePort;
import com.emazon.api_stock.domain.model.Brand;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BrandHandler implements IBrandHandler {

    private final IBrandServicePort brandServicePort;
    private final BrandMapper brandMapper;


    @Override
    public void saveBrand(BrandDto brandDto) {
        Brand brand = brandMapper.brandDtoToBrand(brandDto);
        brandServicePort.saveBrand(brand);
    }

    @Override
    public List<BrandDto> getAllBrands(Integer page, Integer size, boolean descending) {
        return brandMapper.toBrandDtoList(brandServicePort.getAllBrands(page,size,descending));
    }
}
