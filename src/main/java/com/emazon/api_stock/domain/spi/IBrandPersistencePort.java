package com.emazon.api_stock.domain.spi;

import com.emazon.api_stock.domain.model.Brand;
import com.emazon.api_stock.domain.model.Pagination;

public interface IBrandPersistencePort {

    void saveBrand(Brand brand);

    Pagination<Brand> getAllBrands(Integer page, Integer size, boolean descending);

    boolean getBrandByName(String name);
}

