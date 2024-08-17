package com.emazon.api_stock.infraestructure.input.rest;

import com.emazon.api_stock.application.dto.CategoryDto;
import com.emazon.api_stock.application.handler.ICategoryHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stock/category")
@RequiredArgsConstructor
public class CategoryRestController {

    private final ICategoryHandler iCategoryHandler;

    @PostMapping("/createCategory")
    public ResponseEntity<Void> createCategory(@RequestBody CategoryDto categoryDto){
        iCategoryHandler.saveCategory(categoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
