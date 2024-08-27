package com.emazon.api_stock.application.mapper;

import com.emazon.api_stock.application.dto.BrandDto;
import com.emazon.api_stock.domain.model.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BrandMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "brandDto.name", target = "name")
    @Mapping(source = "brandDto.description", target = "description")
    Brand brandDtoToBrand(BrandDto brandDto);
}
