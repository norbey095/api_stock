package com.emazon.api_stock.domain.api;

import com.emazon.api_stock.domain.model.Brand;
import com.emazon.api_stock.domain.model.Pagination;

public interface IBrandServicePort {

        void saveBrand(Brand brand);

        Pagination<Brand> getAllBrands(Integer page, Integer size, boolean descending);
}
