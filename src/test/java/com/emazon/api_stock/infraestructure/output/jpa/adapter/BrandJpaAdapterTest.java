package com.emazon.api_stock.infraestructure.output.jpa.adapter;

import com.emazon.api_stock.domain.model.Brand;
import com.emazon.api_stock.infraestructure.output.jpa.entity.BrandEntity;
import com.emazon.api_stock.infraestructure.output.jpa.mapper.BrandEntityMapper;
import com.emazon.api_stock.infraestructure.output.jpa.repository.IBrandRepository;
import com.emazon.api_stock.infraestructure.util.ConstantsTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

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
    void testGetBrandByNameSuccess() {
        BrandEntity brandEntity = new BrandEntity();
        Mockito.when(brandRepository.findByName(ConstantsTest.FIELD_NAME.getMessage()))
                .thenReturn(Optional.of(brandEntity));

        boolean result = brandJpaAdapter.getBrandByName(ConstantsTest.FIELD_NAME.getMessage());

        assertEquals(true, result);
    }
}
