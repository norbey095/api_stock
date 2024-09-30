package com.emazon.api_stock.infraestructure.input.rest;

import com.emazon.api_stock.application.dto.ResponseSuccess;
import com.emazon.api_stock.application.dto.article.*;
import com.emazon.api_stock.application.handler.article.IArticleHandler;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/registry")
    public ResponseEntity<ResponseSuccess> createArticle(@Validated @RequestBody ArticleRequestDto articleRequestDto){
        ResponseSuccess responseSuccess = articleHandler.saveArticle(articleRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(responseSuccess);
    }

    @Operation(
            summary = "Get all article",
            description = "Get all article ascending or descending, allows you to sort by the name of " +
                    "the associated category, the name of the registered trademark or the name of the brand, " +
                    "to meet this objective in the filterBy parameter you must send: brand, category or article"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Article successfully obtained.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ArticleRequestDto.class))),
            @ApiResponse(responseCode = "400", description
                    = "Invalid request, for example if the `page` or `size` parameters are negative."),
            @ApiResponse(responseCode = "404", description = "No data was found for the parameters provided.")
    })
    @GetMapping("/")
    public ResponseEntity<List<ArticleResponseDto>> getAllArticles(@RequestParam Integer page, @RequestParam Integer size
            , @RequestParam(required = false, defaultValue = InfraestructureConstants.VALUE_FALSE) boolean descending
            , @RequestParam(required = false, defaultValue = InfraestructureConstants.VALUE_ARTICLE) String filterBy) {
        return ResponseEntity.ok(articleHandler.getAllArticles(page, size, descending, filterBy));
    }

    @Operation(summary = "Quantity of an item update",
            description = "Quantity of an item update")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "The amount was updated correctly", content = @Content),
            @ApiResponse(responseCode = "409", description = "Invalid fields", content = @Content)
    })
    @PreAuthorize("hasRole('ROLE_AUX_WAREHOUSE')")
    @PostMapping("/update")
    public ResponseEntity<ResponseSuccess> updateQuantity(@Validated @RequestBody
                                                              ArticleUpdateRequestDto articleUpdateRequestDto){
        ResponseSuccess responseSuccess = articleHandler.updateQuantity(articleUpdateRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(responseSuccess);
    }

    @GetMapping("/{articleId}")
    public ResponseEntity<ArticleResponseDto> getArticlesById(@PathVariable Integer articleId) {
        return ResponseEntity.ok(articleHandler.getArticlesById(articleId));
    }


    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @GetMapping("/getItemsCart")
    public ResponseEntity<List<ArticleResponseDto>> getArticleByIds(
            @RequestParam Integer page,
            @RequestParam Integer size,
            @RequestParam(required = false, defaultValue = InfraestructureConstants.VALUE_FALSE) boolean descending,
            @RequestParam List<Integer> articlesId,
            @RequestParam(required = false) String categoryName,
            @RequestParam(required = false) String brandName) {
        return ResponseEntity.ok(articleHandler.getArticleByIds(page,size,descending,articlesId,categoryName,brandName));
    }

    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @GetMapping("/getPriceByIds")
    public ResponseEntity<List<ArticlePriceResponseDto>> getPriceByIds(
            @RequestParam List<Integer> articlesIds) {
        return ResponseEntity.ok(articleHandler.getPriceByIds(articlesIds));
    }

    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @PostMapping("/subtract")
    public ResponseEntity<ResponseSuccess> subtractQuantityArticle(@Validated @RequestBody
                                                           List<SubtractArticleRequestDto> subtractArticleRequest){
        ResponseSuccess responseSuccess = articleHandler.subtractQuantityArticle(subtractArticleRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(responseSuccess);
    }
}
