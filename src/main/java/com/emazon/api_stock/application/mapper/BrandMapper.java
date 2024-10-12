package com.emazon.api_stock.application.mapper;

import com.emazon.api_stock.application.dto.PaginationDto;
import com.emazon.api_stock.application.dto.brand.BrandRequestDto;
import com.emazon.api_stock.application.dto.brand.BrandResponseDto;
import com.emazon.api_stock.application.util.ConstantsMapper;
import com.emazon.api_stock.domain.model.Brand;
import com.emazon.api_stock.domain.model.Pagination;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BrandMapper {

    @Mapping(target = ConstantsMapper.ID, ignore = true)
    Brand brandDtoToBrand(BrandRequestDto brandDto);

    PaginationDto<BrandResponseDto> toBrandDtoList(Pagination<Brand> brands);
}
