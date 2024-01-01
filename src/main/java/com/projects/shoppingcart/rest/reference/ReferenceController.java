package com.projects.shoppingcart.rest.reference;

import com.projects.shoppingcart.dto.reference.*;
import com.projects.shoppingcart.service.reference.ReferenceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reference")
public class ReferenceController {

    private final ReferenceService referenceService;

    public ReferenceController(ReferenceService referenceService) {
        this.referenceService = referenceService;
    }

    @GetMapping("/brand/fdd")
    public ResponseEntity<List<ScRProductBrandDto>> getBrands() {
        return referenceService.getBrands();
    }

    @GetMapping("/product-type/fdd")
    public ResponseEntity<List<ScRProductTypeDto>> getProductTypes() {
        return referenceService.getProductTypes();
    }

    @GetMapping("/product-category/fdd")
    public ResponseEntity<List<ScRProductCategoryDto>> getProductCategories() {
        return referenceService.getProductCategories();
    }

    @GetMapping("/product-sub-category/fdd")
    public ResponseEntity<List<ScRProductSubCategoryDto>> getProductSubCategories() {
        return referenceService.getProductSubCategories();
    }

    @GetMapping("/product-variable/fdd")
    public ResponseEntity<List<ScRProductVariableDto>> getProductVariables() {
        return referenceService.getProductVariables();
    }

    @GetMapping("/user-role/fdd")
    public ResponseEntity<List<ScRRoleDto>> getUserRoles() {
        return referenceService.getUserRoles();
    }

    @GetMapping("/status/fdd")
    public ResponseEntity<List<ScRStatusDto>> getStatuses() {
        return referenceService.getStatuses();
    }

}