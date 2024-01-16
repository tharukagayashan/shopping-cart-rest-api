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

    ResponseEntity<ScRProductBrandDto> updateBrand(Long id, ScRProductBrandDto productBrandUpdateReqDto);

    ResponseEntity<ScRProductTypeDto> updateProductType(Long id, ScRProductTypeDto productTypeUpdateReqDto);

    ResponseEntity<ScRProductCategoryDto> updateProductCategory(Long id, ScRProductCategoryDto productCategoryUpdateReqDto);

    ResponseEntity<ScRProductSubCategoryDto> updateProductSubCategory(Long id, ScRProductSubCategoryDto productSubCategoryUpdateReqDto);

    ResponseEntity<ScRProductVariableDto> updateProductVariable(Long id, ScRProductVariableDto productVariableUpdateReqDto);

    ResponseEntity<ScRRoleDto> updateUserRole(Long id, ScRRoleDto roleUpdateReqDto);

    ResponseEntity<ScRStatusDto> updateStatus(Long id, ScRStatusDto statusUpdateReqDto);

    ResponseEntity<Long> deleteBrand(Long id);

    ResponseEntity<Long> deleteProductType(Long id);

    ResponseEntity<Long> deleteProductCategory(Long id);

    ResponseEntity<Long> deleteProductSubCategory(Long id);

    ResponseEntity<Long> deleteProductVariable(Long id);

    ResponseEntity<Long> deleteUserRole(Long id);

    ResponseEntity<Long> deleteStatus(Long id);
}
