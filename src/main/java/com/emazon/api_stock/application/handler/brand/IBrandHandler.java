package com.emazon.api_stock.application.handler.brand;

import com.emazon.api_stock.application.dto.BrandDto;
import java.util.List;

public interface IBrandHandler {

    void saveBrand(BrandDto brandDto);

    List<BrandDto> getAllBrands(Integer page, Integer size, boolean descending);
}
