package com.emazon.api_stock.domain.api;

import com.emazon.api_stock.domain.model.Brand;

import java.util.List;

public interface IBrandServicePort {

        void saveBrand(Brand brand);

        List<Brand> getAllBrands(Integer page, Integer size, boolean descending);
}
