package com.emazon.api_stock.application.mapper;

import com.emazon.api_stock.application.dto.brand.BrandRequestDto;
import com.emazon.api_stock.application.dto.brand.BrandResponseDto;
import com.emazon.api_stock.domain.model.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BrandMapper {

    @Mapping(target = "id", ignore = true)
    Brand brandDtoToBrand(BrandRequestDto brandDto);

    List<BrandResponseDto> toBrandDtoList(List<Brand> brands);
}
