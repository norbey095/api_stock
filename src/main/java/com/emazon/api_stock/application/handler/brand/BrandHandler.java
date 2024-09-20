package com.emazon.api_stock.application.handler.brand;

import com.emazon.api_stock.application.dto.ResponseSuccess;
import com.emazon.api_stock.application.dto.brand.BrandRequestDto;
import com.emazon.api_stock.application.dto.brand.BrandResponseDto;
import com.emazon.api_stock.application.mapper.BrandMapper;
import com.emazon.api_stock.application.util.ConstantsHandler;
import com.emazon.api_stock.domain.api.IBrandServicePort;
import com.emazon.api_stock.domain.model.Brand;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BrandHandler implements IBrandHandler {

    private final IBrandServicePort brandServicePort;
    private final BrandMapper brandMapper;


    @Override
    public ResponseSuccess saveBrand(BrandRequestDto brandDto) {
        Brand brand = brandMapper.brandDtoToBrand(brandDto);
        brandServicePort.saveBrand(brand);
        return new ResponseSuccess(ConstantsHandler.BRAND_MESSAGES_SUCCESS, HttpStatus.CREATED.toString());
    }

    @Override
    public List<BrandResponseDto> getAllBrands(Integer page, Integer size, boolean descending) {
        return brandMapper.toBrandDtoList(brandServicePort.getAllBrands(page,size,descending));
    }
}
