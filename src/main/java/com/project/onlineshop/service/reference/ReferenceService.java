package com.project.onlineshop.service.reference;

import com.project.onlineshop.dto.reference.*;
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
}