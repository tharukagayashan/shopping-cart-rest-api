package com.projects.shoppingcart.service.reference;

import com.projects.shoppingcart.dto.other.*;
import com.projects.shoppingcart.dto.reference.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ReferenceService {
    ResponseEntity<List<ScRProductBrandDto>> getBrands();

    ResponseEntity<List<ScRProductTypeDto>> getProductTypes();

    ResponseEntity<List<ScRProductCategoryDto>> getProductCategories();

    ResponseEntity<List<ScRProductSubCategoryDto>> getProductSubCategories();

    ResponseEntity<List<ScRProductVariableDto>> getProductVariables();

    ResponseEntity<List<ScRRoleDto>> getUserRoles();

    ResponseEntity<List<ScRStatusDto>> getStatuses();

    ResponseEntity<ScRProductBrandDto> createBrand(ProductBrandCreateReqDto productBrandCreateReqDto);

    ResponseEntity<ScRProductTypeDto> createProductType(ProductTypeCreateReqDto productTypeCreateReqDto);

    ResponseEntity<ScRProductCategoryDto> createProductCategory(ProductCategoryCreateReqDto productCategoryCreateReqDto);

    ResponseEntity<ScRProductSubCategoryDto> createProductSubCategory(ProductSubCategoryCreateReqDto productSubCategoryCreateReqDto);

    ResponseEntity<ScRProductVariableDto> createProductVariable(ProductVariableCreateReqDto productVariableCreateReqDto);

    ResponseEntity<ScRRoleDto> createUserRole(RoleCreateReqDto roleCreateReqDto);

    ResponseEntity<ScRStatusDto> createStatus(StatusCreateReqDto statusCreateReqDto);
}
