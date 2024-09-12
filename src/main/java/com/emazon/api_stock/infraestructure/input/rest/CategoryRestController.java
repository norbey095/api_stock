package com.emazon.api_stock.infraestructure.input.rest;

import com.emazon.api_stock.application.dto.ResponseSuccess;
import com.emazon.api_stock.application.dto.category.CategoryRequestDto;
import com.emazon.api_stock.application.dto.category.CategoryResponseDto;
import com.emazon.api_stock.application.handler.category.ICategoryHandler;
import com.emazon.api_stock.infraestructure.utils.InfraestructureConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stock/category")
@RequiredArgsConstructor
public class CategoryRestController {

    private final ICategoryHandler categoryHandler;

    @Operation(summary = "Add a new category",
            description = "Save a category, each category must have its respective description.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Category created", content = @Content),
            @ApiResponse(responseCode = "409", description = "Category already exists", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid fields", content = @Content)
    })
    @PostMapping("/registry")
    public ResponseEntity<ResponseSuccess> createCategory(@RequestBody CategoryRequestDto categoryRequestDto){
        ResponseSuccess responseSuccess = categoryHandler.saveCategory(categoryRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(responseSuccess);
    }

    @Operation(
            summary = "Get all categories",
            description = "Get all categories ascending or descending."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categories successfully obtained.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CategoryRequestDto.class))),
            @ApiResponse(responseCode = "400", description
                    = "Invalid request, for example if the `page` or `size` parameters are negative."),
            @ApiResponse(responseCode = "404", description = "No data was found for the parameters provided.")
    })
    @GetMapping("/")
    public ResponseEntity<List<CategoryResponseDto>> getAllCategories(@RequestParam Integer page, @RequestParam Integer size
            , @RequestParam(required = false, defaultValue = InfraestructureConstants.VALUE_FALSE) boolean descending) {
        return ResponseEntity.ok(categoryHandler.getAllCategories(page,size,descending));
    }

}
