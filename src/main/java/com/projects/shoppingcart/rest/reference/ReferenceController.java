package com.projects.shoppingcart.rest.reference;

import com.projects.shoppingcart.dto.other.*;
import com.projects.shoppingcart.dto.reference.*;
import com.projects.shoppingcart.service.reference.ReferenceService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
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

    @PostMapping("/brand")
    public ResponseEntity<ScRProductBrandDto> createBrand(@Valid @RequestBody ProductBrandCreateReqDto productBrandCreateReqDto) {
        return referenceService.createBrand(productBrandCreateReqDto);
    }

    @PostMapping("/product-type")
    public ResponseEntity<ScRProductTypeDto> createProductType(@Valid @RequestBody ProductTypeCreateReqDto productTypeCreateReqDto) {
        return referenceService.createProductType(productTypeCreateReqDto);
    }

    @PostMapping("/product-category")
    public ResponseEntity<ScRProductCategoryDto> createProductCategory(@Valid @RequestBody ProductCategoryCreateReqDto productCategoryCreateReqDto) {
        return referenceService.createProductCategory(productCategoryCreateReqDto);
    }

    @PostMapping("/product-sub-category")
    public ResponseEntity<ScRProductSubCategoryDto> createProductSubCategory(@Valid @RequestBody ProductSubCategoryCreateReqDto productSubCategoryCreateReqDto) {
        return referenceService.createProductSubCategory(productSubCategoryCreateReqDto);
    }

    @PostMapping("/product-variable")
    public ResponseEntity<ScRProductVariableDto> createProductVariable(@Valid @RequestBody ProductVariableCreateReqDto productVariableCreateReqDto) {
        return referenceService.createProductVariable(productVariableCreateReqDto);
    }

    @PostMapping("/user-role")
    public ResponseEntity<ScRRoleDto> createUserRole(@Valid @RequestBody RoleCreateReqDto roleCreateReqDto) {
        return referenceService.createUserRole(roleCreateReqDto);
    }

    @PostMapping("/status")
    public ResponseEntity<ScRStatusDto> createStatus(@Valid @RequestBody StatusCreateReqDto statusCreateReqDto) {
        return referenceService.createStatus(statusCreateReqDto);
    }

}