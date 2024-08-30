package com.emazon.api_stock.infraestructure.input.rest;

import com.emazon.api_stock.application.dto.ResponseSuccess;
import com.emazon.api_stock.application.dto.article.ArticleRequestDto;
import com.emazon.api_stock.application.handler.article.IArticleHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stock/article")
@RequiredArgsConstructor
public class ArticleRestController {

    private final IArticleHandler articleHandler;

    @Operation(summary = "Add a new article",
            description = "Save an article, each article must have its respective brand, a minimum of one " +
                    "associated category and a maximum of 3 categories\n.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "article created", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid fields", content = @Content)
    })
    @PostMapping("/createArticle")
    public ResponseEntity<ResponseSuccess> createArticle(@RequestBody ArticleRequestDto articleRequestDto){
        ResponseSuccess responseSuccess = articleHandler.saveArticle(articleRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(responseSuccess);
    }
}
