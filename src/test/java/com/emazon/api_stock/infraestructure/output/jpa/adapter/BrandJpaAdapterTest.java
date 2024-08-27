package com.emazon.api_stock.infraestructure.output.jpa.adapter;

import com.emazon.api_stock.domain.model.Brand;
import com.emazon.api_stock.infraestructure.exception.NegativeNotAllowedException;
import com.emazon.api_stock.infraestructure.output.jpa.entity.BrandEntity;
import com.emazon.api_stock.infraestructure.output.jpa.mapper.BrandEntityMapper;
import com.emazon.api_stock.infraestructure.output.jpa.repository.IBrandRepository;
import com.emazon.api_stock.infraestructure.util.ConstantsTest;
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

        Brand brand = new Brand(1,ConstantsTest.FIELD_NAME.getMessage()
                , ConstantsTest.FIELD_BRAND_DESCRIPTION.getMessage());
        BrandEntity brandEntity = new BrandEntity();
        Mockito.when(brandEntityMapper.brandToBrandEntity(brand)).thenReturn(brandEntity);

        brandJpaAdapter.saveBrand(brand);

        Mockito.verify(brandRepository, Mockito.times(1)).save(brandEntity);
    }

    @Test
    void testGetAllBrandSuccess() {
        Integer page = 0;
        Integer size = 1;

        List<BrandEntity> brandEntities = new ArrayList<>();
        brandEntities.add(createBrandEntity());

        List<Brand> brands = new ArrayList<>();
        brands.add(createBrand());

        Pageable pagination = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "name"));

        Mockito.doReturn(pagination).when(brandJpaAdapter).createPageable(page, size, false);
        Mockito.when(brandRepository.findAll(pagination)).thenReturn(new PageImpl<>(brandEntities));
        Mockito.when(brandEntityMapper.brandEntityToBrand(brandEntities)).thenReturn(brands);

        List<Brand> result = brandJpaAdapter.getAllBrands(page, size, false);

        assertNotNull(result);
        assertEquals(brands.size(), result.size());
        assertEquals(brands.get(0).getName(), result.get(0).getName());
        assertEquals(brands.get(0).getDescription(), result.get(0).getDescription());
    }

    @Test
    void testGetAllBrand_WhitPageNegative() {
        Integer page = -1;
        Integer size = 1;

        assertThrows(NegativeNotAllowedException.class, () -> {
            brandJpaAdapter.getAllBrands(page, size, false);
        });

        Mockito.verify(brandJpaAdapter, Mockito.times(0))
                .createPageable(1,1,false);
    }

    @Test
    void testGetAllBrand_WhitSizeNegative() {
        Integer page = 1;
        Integer size = -1;

        assertThrows(NegativeNotAllowedException.class, () -> {
            brandJpaAdapter.getAllBrands(page, size, false);
        });

        Mockito.verify(brandJpaAdapter, Mockito.times(0))
                .createPageable(1,1,false);
    }

    @Test
    void testGetBrandByNameSuccess() {
        BrandEntity brandEntity = new BrandEntity();
        Mockito.when(brandRepository.findByName(ConstantsTest.FIELD_NAME.getMessage()))
                .thenReturn(Optional.of(brandEntity));

        boolean result = brandJpaAdapter.getBrandByName(ConstantsTest.FIELD_NAME.getMessage());

        assertEquals(true, result);
    }

    private BrandEntity createBrandEntity(){
        BrandEntity brandEntity = new BrandEntity();

        brandEntity.setId(1);
        brandEntity.setName("");
        brandEntity.setDescription("");

        return brandEntity;
    }

    private Brand createBrand(){
        return new Brand(1,"","");
    }
}
