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


    @PutMapping("/brand/{id}")
    public ResponseEntity<ScRProductBrandDto> updateBrand(@PathVariable Long id, @Valid @RequestBody ScRProductBrandDto productBrandUpdateReqDto) {
        return referenceService.updateBrand(id, productBrandUpdateReqDto);
    }

    @PutMapping("/product-type/{id}")
    public ResponseEntity<ScRProductTypeDto> updateProductType(@PathVariable Long id, @Valid @RequestBody ScRProductTypeDto productTypeUpdateReqDto) {
        return referenceService.updateProductType(id, productTypeUpdateReqDto);
    }

    @PutMapping("/product-category/{id}")
    public ResponseEntity<ScRProductCategoryDto> updateProductCategory(@PathVariable Long id, @Valid @RequestBody ScRProductCategoryDto productCategoryUpdateReqDto) {
        return referenceService.updateProductCategory(id, productCategoryUpdateReqDto);
    }

    @PutMapping("/product-sub-category/{id}")
    public ResponseEntity<ScRProductSubCategoryDto> updateProductSubCategory(@PathVariable Long id, @Valid @RequestBody ScRProductSubCategoryDto productSubCategoryUpdateReqDto) {
        return referenceService.updateProductSubCategory(id, productSubCategoryUpdateReqDto);
    }

    @PutMapping("/product-variable/{id}")
    public ResponseEntity<ScRProductVariableDto> updateProductVariable(@PathVariable Long id, @Valid @RequestBody ScRProductVariableDto productVariableUpdateReqDto) {
        return referenceService.updateProductVariable(id, productVariableUpdateReqDto);
    }

    @PutMapping("/user-role/{id}")
    public ResponseEntity<ScRRoleDto> updateUserRole(@PathVariable Long id, @Valid @RequestBody ScRRoleDto roleUpdateReqDto) {
        return referenceService.updateUserRole(id, roleUpdateReqDto);
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<ScRStatusDto> updateStatus(@PathVariable Long id, @Valid @RequestBody ScRStatusDto statusUpdateReqDto) {
        return referenceService.updateStatus(id, statusUpdateReqDto);
    }

    @DeleteMapping("/brand/{id}")
    public ResponseEntity<Long> deleteBrand(@PathVariable Long id) {
        return referenceService.deleteBrand(id);
    }

    @DeleteMapping("/product-type/{id}")
    public ResponseEntity<Long> deleteProductType(@PathVariable Long id) {
        return referenceService.deleteProductType(id);
    }

    @DeleteMapping("/product-category/{id}")
    public ResponseEntity<Long> deleteProductCategory(@PathVariable Long id) {
        return referenceService.deleteProductCategory(id);
    }

    @DeleteMapping("/product-sub-category/{id}")
    public ResponseEntity<Long> deleteProductSubCategory(@PathVariable Long id) {
        return referenceService.deleteProductSubCategory(id);
    }

    @DeleteMapping("/product-variable/{id}")
    public ResponseEntity<Long> deleteProductVariable(@PathVariable Long id) {
        return referenceService.deleteProductVariable(id);
    }

    @DeleteMapping("/user-role/{id}")
    public ResponseEntity<Long> deleteUserRole(@PathVariable Long id) {
        return referenceService.deleteUserRole(id);
    }

    @DeleteMapping("/status/{id}")
    public ResponseEntity<Long> deleteStatus(@PathVariable Long id) {
        return referenceService.deleteStatus(id);
    }

    @GetMapping("/brand/{id}")
    public ResponseEntity<ScRProductBrandDto> getBrand(@PathVariable Long id) {
        return referenceService.getBrand(id);
    }

    @GetMapping("/product-type/{id}")
    public ResponseEntity<ScRProductTypeDto> getProductType(@PathVariable Long id) {
        return referenceService.getProductType(id);
    }

    @GetMapping("/product-category/{id}")
    public ResponseEntity<ScRProductCategoryDto> getProductCategory(@PathVariable Long id) {
        return referenceService.getProductCategory(id);
    }

    @GetMapping("/product-sub-category/{id}")
    public ResponseEntity<ScRProductSubCategoryDto> getProductSubCategory(@PathVariable Long id) {
        return referenceService.getProductSubCategory(id);
    }

    @GetMapping("/product-variable/{id}")
    public ResponseEntity<ScRProductVariableDto> getProductVariable(@PathVariable Long id) {
        return referenceService.getProductVariable(id);
    }

    @GetMapping("/user-role/{id}")
    public ResponseEntity<ScRRoleDto> getUserRole(@PathVariable Long id) {
        return referenceService.getUserRole(id);
    }

    @GetMapping("/status/{id}")
    public ResponseEntity<ScRStatusDto> getStatus(@PathVariable Long id) {
        return referenceService.getStatus(id);
    }

}