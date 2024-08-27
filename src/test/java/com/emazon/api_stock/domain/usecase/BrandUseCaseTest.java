package com.emazon.api_stock.domain.usecase;

import com.emazon.api_stock.domain.exception.brand.BrandAlreadyExistsException;
import com.emazon.api_stock.domain.exception.brand.InvalidBrandDescriptionException;
import com.emazon.api_stock.domain.exception.brand.InvalidBrandNameException;
import com.emazon.api_stock.domain.model.Brand;
import com.emazon.api_stock.domain.spi.IBrandPersistencePort;
import com.emazon.api_stock.domain.util.ConstantsTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

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
        // Arrange - Preparar
        Brand brand = new Brand(1, ConstantsTest.FIELD_NAME.getMessage()
                , ConstantsTest.FIELD_BRAND_DESCRIPTION.getMessage());

        //Act - Actuar
        brandUseCase.saveBrand(brand);

        // Assert - Afirmar
        Mockito.verify(brandPersistencePort, Mockito.times(1)).saveBrand(brand);
    }

    @Test
    void shouldThrowsExceptionWhenBrandExists() {
        String name = ConstantsTest.FIELD_NAME.getMessage();
        Mockito.when(brandPersistencePort.getBrandByName(name))
                .thenReturn(true);

        assertThrows(BrandAlreadyExistsException.class, () -> {
            brandUseCase.validatedNamePresent(name);
        });

        Mockito.verify(brandPersistencePort, Mockito.times(1))
                .getBrandByName(name);
    }

    @Test
    void shouldThrowExceptionWhenNameExceeds50Characters() {
        Brand brand = new Brand(1,ConstantsTest.NAME_INVALID.getMessage()
                , ConstantsTest.FIELD_BRAND_DESCRIPTION.getMessage());

        InvalidBrandNameException exception = assertThrows(InvalidBrandNameException.class, () -> {
            brandUseCase.saveBrand(brand);
        });

        assertEquals(ConstantsTest.MSN_NAME_CARACTER.getMessage(), exception.getMessage());
    }


    @Test
    void shouldThrowExceptionWhenDescriptionExceeds120Characters() {
        Brand brand = new Brand(1, ConstantsTest.FIELD_NAME.getMessage()
                , ConstantsTest.DESCRIPTION_BRAND_INVALID.getMessage());

        InvalidBrandDescriptionException exception = assertThrows(InvalidBrandDescriptionException.class, () -> {
            brandUseCase.saveBrand(brand);
        });

        assertEquals(ConstantsTest.MSN_DESCRIPTION_BRAND_CARACTER.getMessage(), exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenNameIsNull() {
        Brand brand = new Brand(1, null, ConstantsTest.FIELD_DESCRIPTION.getMessage());

        InvalidBrandNameException exception = assertThrows(InvalidBrandNameException.class, () -> {
            brandUseCase.saveBrand(brand);
        });

        assertEquals(ConstantsTest.MSN_NAME_NULL.getMessage(), exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenNameIsEmpty() {
        Brand brand = new Brand(1, "", ConstantsTest.FIELD_BRAND_DESCRIPTION.getMessage());

        InvalidBrandNameException exception = assertThrows(InvalidBrandNameException.class, () -> {
            brandUseCase.saveBrand(brand);
        });

        assertEquals(ConstantsTest.MSN_NAME_NULL.getMessage(), exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenDescriptionIsNull() {
        Brand brand = new Brand(1,ConstantsTest.FIELD_NAME.getMessage(), null);

        InvalidBrandDescriptionException exception = assertThrows(InvalidBrandDescriptionException.class, () -> {
            brandUseCase.saveBrand(brand);
        });

        assertEquals(ConstantsTest.MSN_DESCRIPTION_NULL.getMessage(), exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenDescriptionIsEmpty() {
        Brand brand = new Brand(1,ConstantsTest.FIELD_NAME.getMessage(), "");

        InvalidBrandDescriptionException exception = assertThrows(InvalidBrandDescriptionException.class, () -> {
            brandUseCase.saveBrand(brand);
        });

        assertEquals(ConstantsTest.MSN_DESCRIPTION_NULL.getMessage(), exception.getMessage());
    }
}
