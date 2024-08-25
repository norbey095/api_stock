package com.emazon.api_stock.domain.spi;

import com.emazon.api_stock.domain.model.Brand;

public interface IBrandPersistencePort {

    void saveBrand(Brand brand);

    boolean getBrandByName(String name);
}

