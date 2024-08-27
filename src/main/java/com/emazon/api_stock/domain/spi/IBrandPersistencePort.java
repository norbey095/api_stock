package com.emazon.api_stock.domain.spi;

import com.emazon.api_stock.domain.model.Brand;

import java.util.List;

public interface IBrandPersistencePort {

    void saveBrand(Brand brand);

    List<Brand> getAllBrands(Integer page, Integer size, boolean descending);

    boolean getBrandByName(String name);
}

