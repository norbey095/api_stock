package com.emazon.api_stock.infraestructure.input.rest;

import com.emazon.api_stock.application.dto.BrandDto;
import com.emazon.api_stock.application.handler.brand.IBrandHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stock/brand")
@RequiredArgsConstructor
public class BrandRestController {

    private final IBrandHandler iBrandHandler;

    @Operation(summary = "Add a new brand",
            description = "Save a brand, each brand must have its respective description.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "brand created", content = @Content),
            @ApiResponse(responseCode = "409", description = "brand already exists", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid fields", content = @Content)
    })
    @PostMapping("/createBrand")
    public ResponseEntity<Void> createBrand(@RequestBody BrandDto brandDto){
        iBrandHandler.saveBrand(brandDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
