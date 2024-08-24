package com.emazon.api_stock.infraestructure.input.rest;

import com.emazon.api_stock.application.dto.CategoryDto;
import com.emazon.api_stock.application.handler.ICategoryHandler;
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
    @PostMapping("/createCategory")
    public ResponseEntity<Void> createCategory(@RequestBody CategoryDto categoryDto){
        categoryHandler.saveCategory(categoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(
            summary = "Get all categorys",
            description = "Get all categories ascending or descending."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categories successfully obtained.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CategoryDto.class))),
            @ApiResponse(responseCode = "400", description
                    = "Invalid request, for example if the `page` or `size` parameters are negative."),
            @ApiResponse(responseCode = "404", description = "No data was found for the parameters provided.")
    })
    @GetMapping("/getCategorys")
    public ResponseEntity<List<CategoryDto>> getAllCategorys(@RequestParam Integer page, @RequestParam Integer size
            ,@RequestParam(required = false, defaultValue = "false") boolean descending) {
        return ResponseEntity.ok(categoryHandler.getAllCategorys(page,size,descending));
    }

}
