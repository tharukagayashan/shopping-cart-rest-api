package com.projects.shoppingcart.service.master;

import com.projects.shoppingcart.dto.master.ScMProductDto;
import com.projects.shoppingcart.dto.other.ProductCreateReqDto;
import org.springframework.http.ResponseEntity;

public interface ProductService {
    ResponseEntity<ScMProductDto> createProduct(ProductCreateReqDto productCreateReqDto);
}
