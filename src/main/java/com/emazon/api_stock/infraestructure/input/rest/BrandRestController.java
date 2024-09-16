package com.emazon.api_stock.infraestructure.input.rest;

import com.emazon.api_stock.application.dto.ResponseSuccess;
import com.emazon.api_stock.application.dto.brand.BrandRequestDto;
import com.emazon.api_stock.application.dto.brand.BrandResponseDto;
import com.emazon.api_stock.application.handler.brand.IBrandHandler;
import com.emazon.api_stock.infraestructure.utils.InfraestructureConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stock/brand")
@RequiredArgsConstructor
public class BrandRestController {

    private final IBrandHandler brandHandler;

    @Operation(summary = "Add a new brand",
            description = "Save a brand, each brand must have its respective description.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "brand created", content = @Content),
            @ApiResponse(responseCode = "409", description = "brand already exists", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid fields", content = @Content)
    })
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/registry")
    public ResponseEntity<ResponseSuccess> createBrand(@RequestBody BrandRequestDto brandRequestDto){
        ResponseSuccess responseSuccess = brandHandler.saveBrand(brandRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(responseSuccess);
    }

    @Operation(
            summary = "Get all brands",
            description = "Get all brands ascending or descending."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Brands successfully obtained.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BrandRequestDto.class))),
            @ApiResponse(responseCode = "400", description
                    = "Invalid request, for example if the `page` or `size` parameters are negative."),
            @ApiResponse(responseCode = "404", description = "No data was found for the parameters provided.")
    })
    @GetMapping("/")
    public ResponseEntity<List<BrandResponseDto>> getAllBrands(@RequestParam Integer page, @RequestParam Integer size
            , @RequestParam(required = false, defaultValue = InfraestructureConstants.VALUE_FALSE) boolean descending) {
        return ResponseEntity.ok(brandHandler.getAllBrands(page,size,descending));
    }
}
