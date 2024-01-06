package com.projects.shoppingcart.service.master;

import com.projects.shoppingcart.dto.master.ScMProductDto;
import com.projects.shoppingcart.dto.miscellaneous.ApiResponseDto;
import com.projects.shoppingcart.dto.other.ProductCreateReqDto;
import com.projects.shoppingcart.dto.other.SingleProductDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
    ResponseEntity<ScMProductDto> createProduct(ProductCreateReqDto productCreateReqDto);

    ResponseEntity<SingleProductDto> getProduct(Long productId);

    ResponseEntity<ScMProductDto> updateProduct(Long productId, SingleProductDto singleProductDto);

    ResponseEntity<ApiResponseDto<List<SingleProductDto>>> getAllProducts(Integer page, Integer perPage, String sort, String direction, String search, Long subCategoryId, Long brandId);

    ResponseEntity<Long> deleteProduct(Long productId);
}
