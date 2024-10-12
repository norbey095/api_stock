package com.emazon.api_stock.domain.usecase;

import com.emazon.api_stock.domain.exception.NoDataFoundException;
import com.emazon.api_stock.domain.exception.PaginationNotAllowedException;
import com.emazon.api_stock.domain.exception.brand.BrandAlreadyExistsException;
import com.emazon.api_stock.domain.exception.brand.InvalidBrandDescriptionException;
import com.emazon.api_stock.domain.exception.brand.InvalidBrandNameException;
import com.emazon.api_stock.domain.model.Brand;
import com.emazon.api_stock.domain.model.Pagination;
import com.emazon.api_stock.domain.spi.IBrandPersistencePort;
import com.emazon.api_stock.domain.util.ConstantsDomain;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BrandUseCaseTest {

    @Mock
    private IBrandPersistencePort brandPersistencePort;

    @InjectMocks
    private BrandUseCase brandUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCallSaveBrandInPersistencePort() {
        Brand brand = new Brand(ConstantsDomain.VALUE_1, ConstantsDomain.FIELD_NAME
                , ConstantsDomain.FIELD_BRAND_DESCRIPTION);

        brandUseCase.saveBrand(brand);

        Mockito.verify(brandPersistencePort, Mockito.times(ConstantsDomain.VALUE_1)).saveBrand(brand);
    }

    @Test
    void shouldThrowsExceptionWhenBrandExists() {
        String name = ConstantsDomain.FIELD_NAME;
        Mockito.when(brandPersistencePort.getBrandByName(name))
                .thenReturn(ConstantsDomain.VALUE_TRUE);

        assertThrows(BrandAlreadyExistsException.class, () -> {
            brandUseCase.validatedNamePresent(name);
        });

        Mockito.verify(brandPersistencePort, Mockito.times(ConstantsDomain.VALUE_1))
                .getBrandByName(name);
    }

    @Test
    void shouldThrowExceptionWhenNameExceeds50Characters() {
        Brand brand = new Brand(ConstantsDomain.VALUE_1, ConstantsDomain.NAME_INVALID
                , ConstantsDomain.FIELD_BRAND_DESCRIPTION);

        InvalidBrandNameException exception = assertThrows(InvalidBrandNameException.class, () -> {
            brandUseCase.saveBrand(brand);
        });

        assertEquals(ConstantsDomain.MSN_NAME_CARACTER, exception.getMessage());
    }


    @Test
    void shouldThrowExceptionWhenDescriptionExceeds120Characters() {
        Brand brand = new Brand(ConstantsDomain.VALUE_1, ConstantsDomain.FIELD_NAME
                , ConstantsDomain.DESCRIPTION_BRAND_INVALID);

        InvalidBrandDescriptionException exception = assertThrows(InvalidBrandDescriptionException.class, () -> {
            brandUseCase.saveBrand(brand);
        });

        assertEquals(ConstantsDomain.MSN_DESCRIPTION_BRAND_CARACTER, exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenNameIsNull() {
        Brand brand = new Brand(ConstantsDomain.VALUE_1, null, ConstantsDomain.FIELD_DESCRIPTION);

        InvalidBrandNameException exception = assertThrows(InvalidBrandNameException.class, () -> {
            brandUseCase.saveBrand(brand);
        });

        assertEquals(ConstantsDomain.MSN_NAME_NULL, exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenNameIsEmpty() {
        Brand brand = new Brand(ConstantsDomain.VALUE_1, ConstantsDomain.COMILLAS
                , ConstantsDomain.FIELD_BRAND_DESCRIPTION);

        InvalidBrandNameException exception = assertThrows(InvalidBrandNameException.class, () -> {
            brandUseCase.saveBrand(brand);
        });

        assertEquals(ConstantsDomain.MSN_NAME_NULL, exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenDescriptionIsNull() {
        Brand brand = new Brand(ConstantsDomain.VALUE_1, ConstantsDomain.FIELD_NAME, null);

        InvalidBrandDescriptionException exception = assertThrows(InvalidBrandDescriptionException.class, () -> {
            brandUseCase.saveBrand(brand);
        });

        assertEquals(ConstantsDomain.MSN_DESCRIPTION_NULL, exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenDescriptionIsEmpty() {
        Brand brand = new Brand(ConstantsDomain.VALUE_1, ConstantsDomain.FIELD_NAME, ConstantsDomain.COMILLAS);

        InvalidBrandDescriptionException exception = assertThrows(InvalidBrandDescriptionException.class, () -> {
            brandUseCase.saveBrand(brand);
        });

        assertEquals(ConstantsDomain.MSN_DESCRIPTION_NULL, exception.getMessage());
    }

    @Test
    void shouldGetAllBrands() {
        Brand brand = new Brand(ConstantsDomain.VALUE_1, ConstantsDomain.FIELD_NAME, ConstantsDomain.COMILLAS);
        List<Brand> brandList = new ArrayList<>();
        brandList.add(brand);
        Pagination<Brand> brandPagination = new Pagination<>();
        brandPagination.setContentList(brandList);

        Mockito.when(brandPersistencePort.getAllBrands(ConstantsDomain.VALUE_1, ConstantsDomain.VALUE_1,
                        ConstantsDomain.VALUE_FALSE))
                .thenReturn(brandPagination);

        Pagination<Brand> result = brandUseCase.getAllBrands(ConstantsDomain.VALUE_1, ConstantsDomain.VALUE_1,
                ConstantsDomain.VALUE_FALSE);

        Mockito.verify(brandPersistencePort, Mockito.times(ConstantsDomain.VALUE_1))
                .getAllBrands(ConstantsDomain.VALUE_1, ConstantsDomain.VALUE_1,ConstantsDomain.VALUE_FALSE);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getContentList().get(ConstantsDomain.VALUE_0).getName(), brand.getName());
        Assertions.assertEquals(result.getContentList().get(ConstantsDomain.VALUE_0)
                .getDescription(), brand.getDescription());
    }

    @Test
    void testGetAllBrand_WhitPageNegative() {
        Integer page = ConstantsDomain.VALUE_N1;
        Integer size = ConstantsDomain.VALUE_1;

        assertThrows(PaginationNotAllowedException.class, () -> {
            brandUseCase.getAllBrands(page, size, ConstantsDomain.VALUE_FALSE);
        });

        Mockito.verify(brandPersistencePort, Mockito.times(ConstantsDomain.VALUE_0))
                .getAllBrands(page,size,ConstantsDomain.VALUE_FALSE);
    }

    @Test
    void testGetAllBrand_WhitSizeNegative() {
        Integer page = ConstantsDomain.VALUE_1;
        Integer size = ConstantsDomain.VALUE_N1;

        assertThrows(PaginationNotAllowedException.class, () -> {
            brandUseCase.getAllBrands(page, size, ConstantsDomain.VALUE_FALSE);
        });

        Mockito.verify(brandPersistencePort, Mockito.times(ConstantsDomain.VALUE_0))
                .getAllBrands(page,size,ConstantsDomain.VALUE_FALSE);
    }

    @Test
    void testGetAllBrand_WhitPageNull() {
        Integer page = null;
        Integer size = ConstantsDomain.VALUE_1;

        assertThrows(PaginationNotAllowedException.class, () -> {
            brandUseCase.getAllBrands(page, size, ConstantsDomain.VALUE_FALSE);
        });

        Mockito.verify(brandPersistencePort, Mockito.times(ConstantsDomain.VALUE_0))
                .getAllBrands(page,size,ConstantsDomain.VALUE_FALSE);
    }

    @Test
    void testGetAllBrand_WhitSizeNull() {
        Integer page = ConstantsDomain.VALUE_1;
        Integer size = null;

        assertThrows(PaginationNotAllowedException.class, () -> {
            brandUseCase.getAllBrands(page, size, ConstantsDomain.VALUE_FALSE);
        });

        Mockito.verify(brandPersistencePort, Mockito.times(ConstantsDomain.VALUE_0))
                .getAllBrands(page,size,ConstantsDomain.VALUE_FALSE);
    }

    @Test
    void shouldGetAllBrandNoData() {
        Mockito.when(brandPersistencePort.getAllBrands(ConstantsDomain.VALUE_1,ConstantsDomain.VALUE_1
                ,ConstantsDomain.VALUE_FALSE)).thenReturn(new Pagination<>());

        assertThrows(NoDataFoundException.class, () -> {
            brandUseCase.getAllBrands(ConstantsDomain.VALUE_1,ConstantsDomain.VALUE_1
                    ,ConstantsDomain.VALUE_FALSE);
        });

        Mockito.verify(brandPersistencePort, Mockito.times(ConstantsDomain.VALUE_1))
                .getAllBrands(ConstantsDomain.VALUE_1,ConstantsDomain.VALUE_1,ConstantsDomain.VALUE_FALSE);
    }

    @Test
    void shouldGetAllBrandIsEmpty() {
        Pagination<Brand> brandPagination = new Pagination<>();
        brandPagination.setContentList(new ArrayList<>());

        Mockito.when(brandPersistencePort.getAllBrands(ConstantsDomain.VALUE_1,ConstantsDomain.VALUE_1
                ,ConstantsDomain.VALUE_FALSE)).thenReturn(brandPagination);

        assertThrows(NoDataFoundException.class, () -> {
            brandUseCase.getAllBrands(ConstantsDomain.VALUE_1,ConstantsDomain.VALUE_1
                    ,ConstantsDomain.VALUE_FALSE);
        });

        Mockito.verify(brandPersistencePort, Mockito.times(ConstantsDomain.VALUE_1))
                .getAllBrands(ConstantsDomain.VALUE_1,ConstantsDomain.VALUE_1,ConstantsDomain.VALUE_FALSE);
    }

    @Test
    void shouldGetAllBrandIsNull() {
        Mockito.when(brandPersistencePort.getAllBrands(ConstantsDomain.VALUE_1,ConstantsDomain.VALUE_1
                ,ConstantsDomain.VALUE_FALSE)).thenReturn(null);

        assertThrows(NoDataFoundException.class, () -> {
            brandUseCase.getAllBrands(ConstantsDomain.VALUE_1,ConstantsDomain.VALUE_1
                    ,ConstantsDomain.VALUE_FALSE);
        });

        Mockito.verify(brandPersistencePort, Mockito.times(ConstantsDomain.VALUE_1))
                .getAllBrands(ConstantsDomain.VALUE_1,ConstantsDomain.VALUE_1,ConstantsDomain.VALUE_FALSE);
    }
}
