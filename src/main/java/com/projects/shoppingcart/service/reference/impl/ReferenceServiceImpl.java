package com.projects.shoppingcart.service.reference.impl;

import com.projects.shoppingcart.dao.reference.*;
import com.projects.shoppingcart.dto.reference.*;
import com.projects.shoppingcart.error.BadRequestAlertException;
import com.projects.shoppingcart.mapper.reference.*;
import com.projects.shoppingcart.model.reference.*;
import com.projects.shoppingcart.service.reference.ReferenceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ReferenceServiceImpl implements ReferenceService {

    private final ScRProductBrandRepository productBrandRepository;
    private final ScRProductBrandMapper productBrandMapper;
    private final ScRProductTypeRepository productTypeRepository;
    private final ScRProductTypeMapper productTypeMapper;
    private final ScRProductCategoryRepository productCategoryRepository;
    private final ScRProductCategoryMapper productCategoryMapper;
    private final ScRProductSubCategoryRepository productSubCategoryRepository;
    private final ScRProductSubCategoryMapper productSubCategoryMapper;
    private final ScRProductVariableRepository productVariableRepository;
    private final ScRProductVariableMapper productVariableMapper;
    private final ScRRoleRepository roleRepository;
    private final ScRRoleMapper roleMapper;
    private final ScRStatusRepository statusRepository;
    private final ScRStatusMapper statusMapper;

    public ReferenceServiceImpl(ScRProductBrandRepository productBrandRepository, ScRProductBrandMapper productBrandMapper, ScRProductTypeRepository productTypeRepository, ScRProductTypeMapper productTypeMapper, ScRProductCategoryRepository productCategoryRepository, ScRProductCategoryMapper productCategoryMapper, ScRProductSubCategoryRepository productSubCategoryRepository, ScRProductSubCategoryMapper productSubCategoryMapper, ScRProductVariableRepository productVariableRepository, ScRProductVariableMapper productVariableMapper, ScRRoleRepository roleRepository, ScRRoleMapper roleMapper, ScRStatusRepository statusRepository, ScRStatusMapper statusMapper) {
        this.productBrandRepository = productBrandRepository;
        this.productBrandMapper = productBrandMapper;
        this.productTypeRepository = productTypeRepository;
        this.productTypeMapper = productTypeMapper;
        this.productCategoryRepository = productCategoryRepository;
        this.productCategoryMapper = productCategoryMapper;
        this.productSubCategoryRepository = productSubCategoryRepository;
        this.productSubCategoryMapper = productSubCategoryMapper;
        this.productVariableRepository = productVariableRepository;
        this.productVariableMapper = productVariableMapper;
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
        this.statusRepository = statusRepository;
        this.statusMapper = statusMapper;
    }

    @Override
    public ResponseEntity<List<ScRProductBrandDto>> getBrands() {
        try {
            List<ScRProductBrand> brands = productBrandRepository.findAll();
            if (brands.isEmpty()) {
                throw new BadRequestAlertException("No brands found", "Reference", "getBrands");
            }else {
                return ResponseEntity.ok(productBrandMapper.entityListToDtoList(brands));
            }
        }catch (Exception e) {
            log.error("Error while getting brands: ", e);
            throw new BadRequestAlertException(e.getMessage(), "Reference", "getBrands");
        }
    }

    @Override
    public ResponseEntity<List<ScRProductTypeDto>> getProductTypes() {
        try {
            List<ScRProductType> productTypes = productTypeRepository.findAll();
            if (productTypes.isEmpty()) {
                throw new BadRequestAlertException("No product types found", "Reference", "getProductTypes");
            }else {
                return ResponseEntity.ok(productTypeMapper.entityListToDtoList(productTypes));
            }
        }catch (Exception e) {
            log.error("Error while getting product types: ", e);
            throw new BadRequestAlertException(e.getMessage(), "Reference", "getProductTypes");
        }
    }

    @Override
    public ResponseEntity<List<ScRProductCategoryDto>> getProductCategories() {
        try {
            List<ScRProductCategory> productCategories = productCategoryRepository.findAll();
            if (productCategories.isEmpty()) {
                throw new BadRequestAlertException("No product categories found", "Reference", "getProductCategories");
            }else {
                return ResponseEntity.ok(productCategoryMapper.entityListToDtoList(productCategories));
            }
        }catch (Exception e){
            log.error("Error while getting product categories: ", e);
            throw new BadRequestAlertException(e.getMessage(), "Reference", "getProductCategories");
        }
    }

    @Override
    public ResponseEntity<List<ScRProductSubCategoryDto>> getProductSubCategories() {
        try {
            List<ScRProductSubCategory> productSubCategories = productSubCategoryRepository.findAll();
            if (productSubCategories.isEmpty()) {
                throw new BadRequestAlertException("No product sub categories found", "Reference", "getProductSubCategories");
            }else {
                return ResponseEntity.ok(productSubCategoryMapper.entityListToDtoList(productSubCategories));
            }
        }catch (Exception e){
            log.error("Error while getting product sub categories: ", e);
            throw new BadRequestAlertException(e.getMessage(), "Reference", "getProductSubCategories");
        }
    }

    @Override
    public ResponseEntity<List<ScRProductVariableDto>> getProductVariables() {
        try {
            List<ScRProductVariable> productVariables = productVariableRepository.findAll();
            if (productVariables.isEmpty()) {
                throw new BadRequestAlertException("No product variables found", "Reference", "getProductVariables");
            }else {
                return ResponseEntity.ok(productVariableMapper.entityListToDtoList(productVariables));
            }
        }catch (Exception e){
            log.error("Error while getting product variables: ", e);
            throw new BadRequestAlertException(e.getMessage(), "Reference", "getProductVariables");
        }
    }

    @Override
    public ResponseEntity<List<ScRRoleDto>> getUserRoles() {
        try {
            List<ScRRole> roles = roleRepository.findAll();
            if (roles.isEmpty()) {
                throw new BadRequestAlertException("No user roles found", "Reference", "getUserRoles");
            }else {
                return ResponseEntity.ok(roleMapper.entityListToDtoList(roles));
            }
        }catch (Exception e){
            log.error("Error while getting user roles: ", e);
            throw new BadRequestAlertException(e.getMessage(), "Reference", "getUserRoles");
        }
    }

    @Override
    public ResponseEntity<List<ScRStatusDto>> getStatuses() {
        try {
            List<ScRStatus> statuses = statusRepository.findAll();
            if (statuses.isEmpty()) {
                throw new BadRequestAlertException("No statuses found", "Reference", "getStatuses");
            }else {
                return ResponseEntity.ok(statusMapper.entityListToDtoList(statuses));
            }
        }catch (Exception e){
            log.error("Error while getting statuses: ", e);
            throw new BadRequestAlertException(e.getMessage(), "Reference", "getStatuses");
        }
    }
}
