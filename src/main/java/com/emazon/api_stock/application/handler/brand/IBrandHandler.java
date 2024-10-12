package com.emazon.api_stock.application.handler.brand;

import com.emazon.api_stock.application.dto.PaginationDto;
import com.emazon.api_stock.application.dto.ResponseSuccess;
import com.emazon.api_stock.application.dto.brand.BrandRequestDto;
import com.emazon.api_stock.application.dto.brand.BrandResponseDto;

public interface IBrandHandler {

    ResponseSuccess saveBrand(BrandRequestDto brandDto);

    PaginationDto<BrandResponseDto> getAllBrands(Integer page, Integer size, boolean descending);
}
