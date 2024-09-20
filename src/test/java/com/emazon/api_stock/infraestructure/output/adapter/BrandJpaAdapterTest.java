package com.emazon.api_stock.infraestructure.output.adapter;

import com.emazon.api_stock.domain.model.Brand;
import com.emazon.api_stock.infraestructure.output.entity.BrandEntity;
import com.emazon.api_stock.infraestructure.output.mapper.BrandEntityMapper;
import com.emazon.api_stock.infraestructure.output.repository.IBrandRepository;
import com.emazon.api_stock.infraestructure.util.ConstantsInfraestructure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BrandJpaAdapterTest {

    @Mock
    private IBrandRepository brandRepository;

    @Mock
    private BrandEntityMapper brandEntityMapper;

    @Spy
    @InjectMocks
    private BrandJpaAdapter brandJpaAdapter;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveBrandSavesSuccessfully() {

        Brand brand = new Brand(ConstantsInfraestructure.VALUE_1, ConstantsInfraestructure.FIELD_NAME
                , ConstantsInfraestructure.FIELD_BRAND_DESCRIPTION);
        BrandEntity brandEntity = new BrandEntity();
        Mockito.when(brandEntityMapper.brandToBrandEntity(brand)).thenReturn(brandEntity);

        brandJpaAdapter.saveBrand(brand);

        Mockito.verify(brandRepository, Mockito.times(ConstantsInfraestructure.VALUE_1)).save(brandEntity);
    }

    @Test
    void testGetAllBrandSuccess() {
        Integer page = ConstantsInfraestructure.VALUE_0;
        Integer size = ConstantsInfraestructure.VALUE_1;

        List<BrandEntity> brandEntities = new ArrayList<>();
        brandEntities.add(createBrandEntity());

        List<Brand> brands = new ArrayList<>();
        brands.add(createBrand());

        Pageable pagination = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, ConstantsInfraestructure.NAME));

        Mockito.when(brandRepository.findAll(pagination)).thenReturn(new PageImpl<>(brandEntities));
        Mockito.when(brandEntityMapper.brandEntityToBrand(brandEntities)).thenReturn(brands);

        List<Brand> result = brandJpaAdapter.getAllBrands(page, size, ConstantsInfraestructure.VALUE_FALSE);

        assertNotNull(result);
        assertEquals(brands.size(), result.size());
        assertEquals(brands.get(ConstantsInfraestructure.VALUE_0).getName(),
                result.get(ConstantsInfraestructure.VALUE_0).getName());
        assertEquals(brands.get(ConstantsInfraestructure.VALUE_0).getDescription(),
                result.get(ConstantsInfraestructure.VALUE_0).getDescription());
    }

    @Test
    void testGetBrandByNameSuccess() {
        BrandEntity brandEntity = new BrandEntity();
        Mockito.when(brandRepository.findByName(ConstantsInfraestructure.FIELD_NAME))
                .thenReturn(Optional.of(brandEntity));

        boolean result = brandJpaAdapter.getBrandByName(ConstantsInfraestructure.FIELD_NAME);

        assertTrue(result);
    }

    private BrandEntity createBrandEntity(){
        BrandEntity brandEntity = new BrandEntity();

        brandEntity.setId(ConstantsInfraestructure.VALUE_1);
        brandEntity.setName(ConstantsInfraestructure.COMILLAS);
        brandEntity.setDescription(ConstantsInfraestructure.COMILLAS);

        return brandEntity;
    }

    private Brand createBrand(){
        return new Brand(ConstantsInfraestructure.VALUE_1,ConstantsInfraestructure.COMILLAS
                ,ConstantsInfraestructure.COMILLAS);
    }
}
