package com.emazon.api_stock.domain.usecase;

import com.emazon.api_stock.domain.api.IBrandServicePort;
import com.emazon.api_stock.domain.exception.NoDataFoundException;
import com.emazon.api_stock.domain.exception.PaginationNotAllowedException;
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
        validateNegativeData(page,size);
        List<Brand> brandList = this.brandPersistencePort.getAllBrands(page, size,descending);
        validateData(brandList);
        return brandList;
    }

    protected void validatedNamePresent(String name){
        if(this.brandPersistencePort.getBrandByName(name)) {
            throw new BrandAlreadyExistsException();
        }
    }

    private void validatedName(String name){
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidBrandNameException(Constants.FIELD_NAME_NULL);
        }
        if (name.length() > Constants.VALUE_50) {
            throw new InvalidBrandNameException(Constants.FIELD_NAME_MAX);
        }
    }

    private void validatedDescription(String description){
        if (description == null || description.trim().isEmpty()) {
            throw new InvalidBrandDescriptionException(Constants.FIELD_DESCRIPTION_NULL);
        }
        if (description.length() > Constants.VALUE_120) {
            throw new InvalidBrandDescriptionException(Constants.FIELD_DESCRIPTION_BRAND_MAX);
        }
    }

    private void validateNegativeData(Integer page, Integer size){
        if (page == null || size == null){
            throw new PaginationNotAllowedException();
        }
        if (page <  Constants.VALUE_0 || size < Constants.VALUE_0) {
            throw new PaginationNotAllowedException();
        }
    }

    private void validateData( List<Brand> brandList){
        if (brandList.isEmpty()) {
            throw new NoDataFoundException();
        }
    }
}
