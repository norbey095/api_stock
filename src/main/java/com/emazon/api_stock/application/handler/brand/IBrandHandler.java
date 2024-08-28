package com.emazon.api_stock.application.handler.brand;

import com.emazon.api_stock.application.dto.ResponseSuccess;
import com.emazon.api_stock.application.dto.brand.BrandRequestDto;
import com.emazon.api_stock.application.dto.brand.BrandResponseDto;

import java.util.List;

public interface IBrandHandler {

    ResponseSuccess saveBrand(BrandRequestDto brandDto);

    List<BrandResponseDto> getAllBrands(Integer page, Integer size, boolean descending);
}
