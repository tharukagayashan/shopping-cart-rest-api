package com.projects.shoppingcart.rest.master;

import com.projects.shoppingcart.dto.master.ScMProductDto;
import com.projects.shoppingcart.dto.miscellaneous.ApiResponseDto;
import com.projects.shoppingcart.dto.other.ProductCreateReqDto;
import com.projects.shoppingcart.dto.other.SingleProductDto;
import com.projects.shoppingcart.service.master.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ScMProductDto> createProduct(@Valid @RequestBody ProductCreateReqDto productCreateReqDto) {
        log.info("Creating product");
        return productService.createProduct(productCreateReqDto);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<SingleProductDto> getProduct(@PathVariable Long productId) {
        log.info("Getting product");
        return productService.getProduct(productId);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ScMProductDto> updateProduct(@PathVariable Long productId, @Valid @RequestBody SingleProductDto singleProductDto) {
        log.info("Updating product");
        return productService.updateProduct(productId, singleProductDto);
    }

    @GetMapping
    public ResponseEntity<ApiResponseDto<List<SingleProductDto>>> getAllProducts(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer per_page,
            @RequestParam(required = false, defaultValue = "productId") String sort,
            @RequestParam(required = false, defaultValue = "asc") String direction,
            @RequestParam(required = false, defaultValue = "") String search,
            @RequestParam(required = false) Long subCategoryId,
            @RequestParam(required = false) Long brandId
    ) {
        log.info("Getting all products");
        return productService.getAllProducts(
                page, per_page, sort, direction, search, subCategoryId, brandId
        );
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Long> deleteProduct(@PathVariable Long productId) {
        log.info("Deleting product");
        return productService.deleteProduct(productId);
    }

}