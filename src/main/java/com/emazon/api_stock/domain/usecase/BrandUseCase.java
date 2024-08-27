package com.emazon.api_stock.domain.usecase;

import com.emazon.api_stock.domain.api.IBrandServicePort;
import com.emazon.api_stock.domain.exception.brand.BrandAlreadyExistsException;
import com.emazon.api_stock.domain.exception.brand.InvalidBrandDescriptionException;
import com.emazon.api_stock.domain.exception.brand.InvalidBrandNameException;
import com.emazon.api_stock.domain.model.Brand;
import com.emazon.api_stock.domain.spi.IBrandPersistencePort;
import com.emazon.api_stock.domain.util.Constants;

import java.util.List;

public class BrandUseCase implements IBrandServicePort {

    private final IBrandPersistencePort brandPersistencePort;

    public BrandUseCase(IBrandPersistencePort brandPersistencePort) {
        this.brandPersistencePort = brandPersistencePort;
    }

    @Override
    public void saveBrand(Brand brand) {
        validatedName(brand.getName());
        validatedDescription(brand.getDescription());
        validatedNamePresent(brand.getName());
        this.brandPersistencePort.saveBrand(brand);
    }

    @Override
    public List<Brand> getAllBrands(Integer page, Integer size, boolean descending) {
        return this.brandPersistencePort.getAllBrands(page, size,descending);
    }

    protected void validatedNamePresent(String name){
        if(this.brandPersistencePort.getBrandByName(name)) {
            throw new BrandAlreadyExistsException();
        }
    }

    private void validatedName(String name){
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidBrandNameException(Constants.FIELD_NAME_NULL.getMessage());
        }
        if (name.length() > 50) {
            throw new InvalidBrandNameException(Constants.FIELD_NAME_MAX.getMessage());
        }
    }

    private void validatedDescription(String description){
        if (description == null || description.trim().isEmpty()) {
            throw new InvalidBrandDescriptionException(Constants.FIELD_DESCRIPTION_NULL.getMessage());
        }
        if (description.length() > 120) {
            throw new InvalidBrandDescriptionException(Constants.FIELD_DESCRIPTION_BRAND_MAX.getMessage());
        }
    }
}
