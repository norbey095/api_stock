package com.emazon.api_stock.infraestructure.output.jpa.mapper;

import com.emazon.api_stock.domain.model.Brand;
import com.emazon.api_stock.domain.model.Category;
import com.emazon.api_stock.infraestructure.output.jpa.entity.BrandEntity;
import com.emazon.api_stock.infraestructure.output.jpa.entity.CategoryEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BrandEntityMapper {

    BrandEntity brandToBrandEntity(Brand brand);

    List<Brand> brandEntityToBrand(List<BrandEntity> brandEntity);
}
