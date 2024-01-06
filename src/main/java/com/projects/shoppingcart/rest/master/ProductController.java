package com.projects.shoppingcart.rest.master;

import com.projects.shoppingcart.dto.master.ScMProductDto;
import com.projects.shoppingcart.dto.other.ProductCreateReqDto;
import com.projects.shoppingcart.service.master.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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

}