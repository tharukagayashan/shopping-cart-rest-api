package com.projects.shoppingcart.service.reference.impl;

import com.projects.shoppingcart.dao.reference.*;
import com.projects.shoppingcart.dto.other.*;
import com.projects.shoppingcart.dto.reference.*;
import com.projects.shoppingcart.error.BadRequestAlertException;
import com.projects.shoppingcart.mapper.reference.*;
import com.projects.shoppingcart.model.reference.*;
import com.projects.shoppingcart.service.reference.ReferenceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
            } else {
                return ResponseEntity.ok(productBrandMapper.entityListToDtoList(brands));
            }
        } catch (Exception e) {
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
            } else {
                return ResponseEntity.ok(productTypeMapper.entityListToDtoList(productTypes));
            }
        } catch (Exception e) {
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
            } else {
                return ResponseEntity.ok(productCategoryMapper.entityListToDtoList(productCategories));
            }
        } catch (Exception e) {
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
            } else {
                return ResponseEntity.ok(productSubCategoryMapper.entityListToDtoList(productSubCategories));
            }
        } catch (Exception e) {
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
            } else {
                return ResponseEntity.ok(productVariableMapper.entityListToDtoList(productVariables));
            }
        } catch (Exception e) {
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
            } else {
                return ResponseEntity.ok(roleMapper.entityListToDtoList(roles));
            }
        } catch (Exception e) {
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
            } else {
                return ResponseEntity.ok(statusMapper.entityListToDtoList(statuses));
            }
        } catch (Exception e) {
            log.error("Error while getting statuses: ", e);
            throw new BadRequestAlertException(e.getMessage(), "Reference", "getStatuses");
        }
    }

    @Override
    public ResponseEntity<ScRProductBrandDto> createBrand(ProductBrandCreateReqDto productBrandCreateReqDto) {
        try {
            ScRProductBrand productBrand = new ScRProductBrand();
            productBrand.setName(productBrandCreateReqDto.getName());
            productBrand.setDescription(productBrandCreateReqDto.getDescription());
            productBrand = productBrandRepository.save(productBrand);
            if (productBrand.getBrandId() == null) {
                throw new BadRequestAlertException("Error while creating brand", "Reference", "createBrand");
            } else {
                return ResponseEntity.ok(productBrandMapper.toDto(productBrand));
            }
        } catch (Exception e) {
            log.error("Error while creating brand: ", e);
            throw new BadRequestAlertException(e.getMessage(), "Reference", "createBrand");
        }
    }

    @Override
    public ResponseEntity<ScRProductTypeDto> createProductType(ProductTypeCreateReqDto productTypeCreateReqDto) {
        try {
            ScRProductType productType = new ScRProductType();
            productType.setName(productTypeCreateReqDto.getName());
            productType.setDescription(productTypeCreateReqDto.getDescription());
            productType.setCode(productTypeCreateReqDto.getCode());
            productType = productTypeRepository.save(productType);
            if (productType.getTypeId() == null) {
                throw new BadRequestAlertException("Error while creating product type", "Reference", "createProductType");
            } else {
                return ResponseEntity.ok(productTypeMapper.toDto(productType));
            }
        } catch (Exception e) {
            log.error("Error while creating product type: ", e);
            throw new BadRequestAlertException(e.getMessage(), "Reference", "createProductType");
        }
    }

    @Override
    public ResponseEntity<ScRProductCategoryDto> createProductCategory(ProductCategoryCreateReqDto productCategoryCreateReqDto) {
        try {
            Optional<ScRProductType> productType = productTypeRepository.findById(productCategoryCreateReqDto.getTypeId());
            if (!productType.isPresent()) {
                throw new BadRequestAlertException("Product type not found", "Reference", "createProductCategory");
            }
            Optional<ScRProductCategory> optProductCategory = productCategoryRepository.findByCode(productCategoryCreateReqDto.getCode());
            if (optProductCategory.isPresent()) {
                throw new BadRequestAlertException("Product category code already exists", "Reference", "createProductCategory");
            }
            ScRProductCategory productCategory = new ScRProductCategory();
            productCategory.setName(productCategoryCreateReqDto.getName());
            productCategory.setDescription(productCategoryCreateReqDto.getDescription());
            productCategory.setCode(productCategoryCreateReqDto.getCode());
            productCategory.setScRProductType(productType.get());
            productCategory = productCategoryRepository.save(productCategory);
            if (productCategory.getCategoryId() == null) {
                throw new BadRequestAlertException("Error while creating product category", "Reference", "createProductCategory");
            } else {
                return ResponseEntity.ok(productCategoryMapper.toDto(productCategory));
            }
        } catch (Exception e) {
            log.error("Error while creating product category: ", e);
            throw new BadRequestAlertException(e.getMessage(), "Reference", "createProductCategory");
        }
    }

    @Override
    public ResponseEntity<ScRProductSubCategoryDto> createProductSubCategory(ProductSubCategoryCreateReqDto productSubCategoryCreateReqDto) {
        try {
            Optional<ScRProductCategory> productCategory = productCategoryRepository.findById(productSubCategoryCreateReqDto.getCategoryId());
            if (!productCategory.isPresent()) {
                throw new BadRequestAlertException("Product category not found", "Reference", "createProductSubCategory");
            }
            Optional<ScRProductSubCategory> optProductSubCategory = productSubCategoryRepository.findByCode(productSubCategoryCreateReqDto.getCode());
            if (optProductSubCategory.isPresent()) {
                throw new BadRequestAlertException("Product sub category code already exists", "Reference", "createProductSubCategory");
            }
            ScRProductSubCategory productSubCategory = new ScRProductSubCategory();
            productSubCategory.setName(productSubCategoryCreateReqDto.getName());
            productSubCategory.setDescription(productSubCategoryCreateReqDto.getDescription());
            productSubCategory.setCode(productSubCategoryCreateReqDto.getCode());
            productSubCategory.setScRProductCategory(productCategory.get());
            productSubCategory = productSubCategoryRepository.save(productSubCategory);
            if (productSubCategory.getSubCategoryId() == null) {
                throw new BadRequestAlertException("Error while creating product sub category", "Reference", "createProductSubCategory");
            } else {
                return ResponseEntity.ok(productSubCategoryMapper.toDto(productSubCategory));
            }
        } catch (Exception e) {
            log.error("Error while creating product sub category: ", e);
            throw new BadRequestAlertException(e.getMessage(), "Reference", "createProductSubCategory");
        }
    }

    @Override
    public ResponseEntity<ScRProductVariableDto> createProductVariable(ProductVariableCreateReqDto productVariableCreateReqDto) {
        try {
            ScRProductVariable productVariable = new ScRProductVariable();
            productVariable.setName(productVariableCreateReqDto.getName());
            productVariable = productVariableRepository.save(productVariable);
            if (productVariable.getVariableId() == null) {
                throw new BadRequestAlertException("Error while creating product variable", "Reference", "createProductVariable");
            } else {
                return ResponseEntity.ok(productVariableMapper.toDto(productVariable));
            }
        } catch (Exception e) {
            log.error("Error while creating product variable: ", e);
            throw new BadRequestAlertException(e.getMessage(), "Reference", "createProductVariable");
        }
    }

    @Override
    public ResponseEntity<ScRRoleDto> createUserRole(RoleCreateReqDto roleCreateReqDto) {
        try {
            ScRRole role = new ScRRole();
            role.setRoleName(roleCreateReqDto.getRoleName());
            role.setDescription(roleCreateReqDto.getDescription());
            role.setIsActive(true);
            role = roleRepository.save(role);
            if (role.getRoleId() == null) {
                throw new BadRequestAlertException("Error while creating user role", "Reference", "createUserRole");
            } else {
                return ResponseEntity.ok(roleMapper.toDto(role));
            }
        } catch (Exception e) {
            log.error("Error while creating user role: ", e);
            throw new BadRequestAlertException(e.getMessage(), "Reference", "createUserRole");
        }
    }

    @Override
    public ResponseEntity<ScRStatusDto> createStatus(StatusCreateReqDto statusCreateReqDto) {
        try {

            Optional<ScRStatus> optStatus = statusRepository.findByCode(statusCreateReqDto.getCode());
            if (optStatus.isPresent()) {
                throw new BadRequestAlertException("Status code already exists", "Reference", "createStatus");
            }
            ScRStatus status = new ScRStatus();
            status.setName(statusCreateReqDto.getName());
            status.setCode(statusCreateReqDto.getCode());
            status.setType(statusCreateReqDto.getType());
            status = statusRepository.save(status);
            if (status.getStatusId() == null) {
                throw new BadRequestAlertException("Error while creating status", "Reference", "createStatus");
            } else {
                return ResponseEntity.ok(statusMapper.toDto(status));
            }
        } catch (Exception e) {
            log.error("Error while creating status: ", e);
            throw new BadRequestAlertException(e.getMessage(), "Reference", "createStatus");
        }
    }

    @Override
    public ResponseEntity<ScRProductBrandDto> updateBrand(Long id, ScRProductBrandDto productBrandUpdateReqDto) {
        try {
            if (id == null) {
                throw new BadRequestAlertException("Brand id is null", "Reference", "updateBrand");
            } else if (!id.equals(productBrandUpdateReqDto.getBrandId())) {
                throw new BadRequestAlertException("Brand id mismatch", "Reference", "updateBrand");
            } else {
                Optional<ScRProductBrand> optProductBrand = productBrandRepository.findById(id);
                if (!optProductBrand.isPresent()) {
                    throw new BadRequestAlertException("Brand not found", "Reference", "updateBrand");
                }
                ScRProductBrand productBrand = optProductBrand.get();
                productBrand.setName(productBrandUpdateReqDto.getName());
                productBrand.setDescription(productBrandUpdateReqDto.getDescription());
                productBrand = productBrandRepository.save(productBrand);
                if (productBrand.getBrandId() == null) {
                    throw new BadRequestAlertException("Error while updating brand", "Reference", "updateBrand");
                } else {
                    return ResponseEntity.ok(productBrandMapper.toDto(productBrand));
                }
            }
        } catch (Exception e) {
            log.error("Error while updating brand: ", e);
            throw new BadRequestAlertException(e.getMessage(), "Reference", "updateBrand");
        }
    }

    @Override
    public ResponseEntity<ScRProductTypeDto> updateProductType(Long id, ScRProductTypeDto productTypeUpdateReqDto) {
        try {
            if (id == null) {
                throw new BadRequestAlertException("Product type id is null", "Reference", "updateProductType");
            } else if (!id.equals(productTypeUpdateReqDto.getTypeId())) {
                throw new BadRequestAlertException("Product type id mismatch", "Reference", "updateProductType");
            } else {
                Optional<ScRProductType> optProductType = productTypeRepository.findById(id);
                if (!optProductType.isPresent()) {
                    throw new BadRequestAlertException("Product type not found", "Reference", "updateProductType");
                }
                ScRProductType productType = optProductType.get();
                productType.setName(productTypeUpdateReqDto.getName());
                productType.setDescription(productTypeUpdateReqDto.getDescription());
                productType.setCode(productTypeUpdateReqDto.getCode());
                productType = productTypeRepository.save(productType);
                if (productType.getTypeId() == null) {
                    throw new BadRequestAlertException("Error while updating product type", "Reference", "updateProductType");
                } else {
                    return ResponseEntity.ok(productTypeMapper.toDto(productType));
                }
            }
        } catch (Exception e) {
            log.error("Error while updating product type: ", e);
            throw new BadRequestAlertException(e.getMessage(), "Reference", "updateProductType");
        }
    }

    @Override
    public ResponseEntity<ScRProductCategoryDto> updateProductCategory(Long id, ScRProductCategoryDto productCategoryUpdateReqDto) {
        try {
            if (id == null) {
                throw new BadRequestAlertException("Product category id is null", "Reference", "updateProductCategory");
            } else if (!id.equals(productCategoryUpdateReqDto.getCategoryId())) {
                throw new BadRequestAlertException("Product category id mismatch", "Reference", "updateProductCategory");
            } else {
                Optional<ScRProductCategory> optProductCategory = productCategoryRepository.findById(id);
                if (!optProductCategory.isPresent()) {
                    throw new BadRequestAlertException("Product category not found", "Reference", "updateProductCategory");
                }

                Optional<ScRProductType> optProductType = productTypeRepository.findById(productCategoryUpdateReqDto.getTypeId());
                if (!optProductType.isPresent()) {
                    throw new BadRequestAlertException("Product type not found", "Reference", "updateProductCategory");
                }

                ScRProductCategory productCategory = optProductCategory.get();
                productCategory.setName(productCategoryUpdateReqDto.getName());
                productCategory.setDescription(productCategoryUpdateReqDto.getDescription());
                productCategory.setCode(productCategoryUpdateReqDto.getCode());
                productCategory.setScRProductType(optProductType.get());
                productCategory = productCategoryRepository.save(productCategory);
                if (productCategory.getCategoryId() == null) {
                    throw new BadRequestAlertException("Error while updating product category", "Reference", "updateProductCategory");
                } else {
                    return ResponseEntity.ok(productCategoryMapper.toDto(productCategory));
                }
            }
        } catch (Exception e) {
            log.error("Error while updating product category: ", e);
            throw new BadRequestAlertException(e.getMessage(), "Reference", "updateProductCategory");
        }
    }

    @Override
    public ResponseEntity<ScRProductSubCategoryDto> updateProductSubCategory(Long id, ScRProductSubCategoryDto productSubCategoryUpdateReqDto) {
        try {
            if (id == null) {
                throw new BadRequestAlertException("Product sub category id is null", "Reference", "updateProductSubCategory");
            } else if (!id.equals(productSubCategoryUpdateReqDto.getSubCategoryId())) {
                throw new BadRequestAlertException("Product sub category id mismatch", "Reference", "updateProductSubCategory");
            } else {
                Optional<ScRProductSubCategory> optProductSubCategory = productSubCategoryRepository.findById(id);
                if (!optProductSubCategory.isPresent()) {
                    throw new BadRequestAlertException("Product sub category not found", "Reference", "updateProductSubCategory");
                }

                Optional<ScRProductCategory> optProductCategory = productCategoryRepository.findById(productSubCategoryUpdateReqDto.getCategoryId());
                if (!optProductCategory.isPresent()) {
                    throw new BadRequestAlertException("Product category not found", "Reference", "updateProductSubCategory");
                }

                ScRProductSubCategory productSubCategory = optProductSubCategory.get();
                productSubCategory.setName(productSubCategoryUpdateReqDto.getName());
                productSubCategory.setDescription(productSubCategoryUpdateReqDto.getDescription());
                productSubCategory.setCode(productSubCategoryUpdateReqDto.getCode());
                productSubCategory.setScRProductCategory(optProductCategory.get());
                productSubCategory = productSubCategoryRepository.save(productSubCategory);
                if (productSubCategory.getSubCategoryId() == null) {
                    throw new BadRequestAlertException("Error while updating product sub category", "Reference", "updateProductSubCategory");
                } else {
                    return ResponseEntity.ok(productSubCategoryMapper.toDto(productSubCategory));
                }
            }
        } catch (Exception e) {
            log.error("Error while updating product sub category: ", e);
            throw new BadRequestAlertException(e.getMessage(), "Reference", "updateProductSubCategory");
        }
    }

    @Override
    public ResponseEntity<ScRProductVariableDto> updateProductVariable(Long id, ScRProductVariableDto productVariableUpdateReqDto) {
        try {
            if (id == null) {
                throw new BadRequestAlertException("Product variable id is null", "Reference", "updateProductVariable");
            } else if (!id.equals(productVariableUpdateReqDto.getVariableId())) {
                throw new BadRequestAlertException("Product variable id mismatch", "Reference", "updateProductVariable");
            } else {
                Optional<ScRProductVariable> optProductVariable = productVariableRepository.findById(id);
                if (!optProductVariable.isPresent()) {
                    throw new BadRequestAlertException("Product variable not found", "Reference", "updateProductVariable");
                }
                ScRProductVariable productVariable = optProductVariable.get();
                productVariable.setName(productVariableUpdateReqDto.getName());
                productVariable = productVariableRepository.save(productVariable);
                if (productVariable.getVariableId() == null) {
                    throw new BadRequestAlertException("Error while updating product variable", "Reference", "updateProductVariable");
                } else {
                    return ResponseEntity.ok(productVariableMapper.toDto(productVariable));
                }
            }
        } catch (Exception e) {
            log.error("Error while updating product variable: ", e);
            throw new BadRequestAlertException(e.getMessage(), "Reference", "updateProductVariable");
        }
    }

    @Override
    public ResponseEntity<ScRRoleDto> updateUserRole(Long id, ScRRoleDto roleUpdateReqDto) {
        try {
            if (id == null) {
                throw new BadRequestAlertException("User role id is null", "Reference", "updateUserRole");
            } else if (!id.equals(roleUpdateReqDto.getRoleId())) {
                throw new BadRequestAlertException("User role id mismatch", "Reference", "updateUserRole");
            } else {
                Optional<ScRRole> optRole = roleRepository.findById(id);
                if (!optRole.isPresent()) {
                    throw new BadRequestAlertException("User role not found", "Reference", "updateUserRole");
                }
                ScRRole role = optRole.get();
                role.setRoleName(roleUpdateReqDto.getRoleName());
                role.setDescription(roleUpdateReqDto.getDescription());
                role.setIsActive(roleUpdateReqDto.getIsActive());
                role = roleRepository.save(role);
                if (role.getRoleId() == null) {
                    throw new BadRequestAlertException("Error while updating user role", "Reference", "updateUserRole");
                } else {
                    return ResponseEntity.ok(roleMapper.toDto(role));
                }
            }
        } catch (Exception e) {
            log.error("Error while updating user role: ", e);
            throw new BadRequestAlertException(e.getMessage(), "Reference", "updateUserRole");
        }
    }

    @Override
    public ResponseEntity<ScRStatusDto> updateStatus(Long id, ScRStatusDto statusUpdateReqDto) {
        try {
            if (id == null) {
                throw new BadRequestAlertException("Status id is null", "Reference", "updateStatus");
            } else if (!id.equals(statusUpdateReqDto.getStatusId())) {
                throw new BadRequestAlertException("Status id mismatch", "Reference", "updateStatus");
            } else {
                Optional<ScRStatus> optStatus = statusRepository.findById(id);
                if (!optStatus.isPresent()) {
                    throw new BadRequestAlertException("Status not found", "Reference", "updateStatus");
                }
                Optional<ScRStatus> optStatusByCode = statusRepository.findByCode(statusUpdateReqDto.getCode());
                if (optStatusByCode.isPresent() && !optStatusByCode.get().getStatusId().equals(id)) {
                    throw new BadRequestAlertException("Status code already exists", "Reference", "updateStatus");
                }
                ScRStatus status = optStatus.get();
                status.setName(statusUpdateReqDto.getName());
                status.setCode(statusUpdateReqDto.getCode());
                status.setType(statusUpdateReqDto.getType());
                status = statusRepository.save(status);
                if (status.getStatusId() == null) {
                    throw new BadRequestAlertException("Error while updating status", "Reference", "updateStatus");
                } else {
                    return ResponseEntity.ok(statusMapper.toDto(status));
                }
            }
        } catch (Exception e) {
            log.error("Error while updating status: ", e);
            throw new BadRequestAlertException(e.getMessage(), "Reference", "updateStatus");
        }
    }

    @Override
    public ResponseEntity<Long> deleteBrand(Long id) {
        try {
            if (id == null) {
                throw new BadRequestAlertException("Brand id is null", "Reference", "deleteBrand");
            } else {
                Optional<ScRProductBrand> optProductBrand = productBrandRepository.findById(id);
                if (!optProductBrand.isPresent()) {
                    throw new BadRequestAlertException("Brand not found", "Reference", "deleteBrand");
                }
                productBrandRepository.deleteById(id);
                if (productBrandRepository.findById(id).isPresent())
                    throw new BadRequestAlertException("Error while deleting brand", "Reference", "deleteBrand");
                else {
                    return ResponseEntity.ok(id);
                }
            }
        } catch (Exception e) {
            log.error("Error while deleting brand: ", e);
            throw new BadRequestAlertException(e.getMessage(), "Reference", "deleteBrand");
        }
    }

    @Override
    public ResponseEntity<Long> deleteProductType(Long id) {
        try {
            if (id == null) {
                throw new BadRequestAlertException("Product type id is null", "Reference", "deleteProductType");
            } else {
                Optional<ScRProductType> optProductType = productTypeRepository.findById(id);
                if (!optProductType.isPresent()) {
                    throw new BadRequestAlertException("Product type not found", "Reference", "deleteProductType");
                }
                productTypeRepository.deleteById(id);
                if (productTypeRepository.findById(id).isPresent())
                    throw new BadRequestAlertException("Error while deleting product type", "Reference", "deleteProductType");
                else {
                    return ResponseEntity.ok(id);
                }
            }
        } catch (Exception e) {
            log.error("Error while deleting product type: ", e);
            throw new BadRequestAlertException(e.getMessage(), "Reference", "deleteProductType");
        }
    }

    @Override
    public ResponseEntity<Long> deleteProductCategory(Long id) {
        try {
            if (id == null) {
                throw new BadRequestAlertException("Product category id is null", "Reference", "deleteProductCategory");
            } else {
                Optional<ScRProductCategory> optProductCategory = productCategoryRepository.findById(id);
                if (!optProductCategory.isPresent()) {
                    throw new BadRequestAlertException("Product category not found", "Reference", "deleteProductCategory");
                }
                productCategoryRepository.deleteById(id);
                if (productCategoryRepository.findById(id).isPresent())
                    throw new BadRequestAlertException("Error while deleting product category", "Reference", "deleteProductCategory");
                else {
                    return ResponseEntity.ok(id);
                }
            }
        } catch (Exception e) {
            log.error("Error while deleting product category: ", e);
            throw new BadRequestAlertException(e.getMessage(), "Reference", "deleteProductCategory");
        }
    }

    @Override
    public ResponseEntity<Long> deleteProductSubCategory(Long id) {
        try {
            if (id == null) {
                throw new BadRequestAlertException("Product sub category id is null", "Reference", "deleteProductSubCategory");
            } else {
                Optional<ScRProductSubCategory> optProductSubCategory = productSubCategoryRepository.findById(id);
                if (!optProductSubCategory.isPresent()) {
                    throw new BadRequestAlertException("Product sub category not found", "Reference", "deleteProductSubCategory");
                }
                productSubCategoryRepository.deleteById(id);
                if (productSubCategoryRepository.findById(id).isPresent())
                    throw new BadRequestAlertException("Error while deleting product sub category", "Reference", "deleteProductSubCategory");
                else {
                    return ResponseEntity.ok(id);
                }
            }
        } catch (Exception e) {
            log.error("Error while deleting product sub category: ", e);
            throw new BadRequestAlertException(e.getMessage(), "Reference", "deleteProductSubCategory");
        }
    }

    @Override
    public ResponseEntity<Long> deleteProductVariable(Long id) {
        try {
            if (id == null) {
                throw new BadRequestAlertException("Product variable id is null", "Reference", "deleteProductVariable");
            } else {
                Optional<ScRProductVariable> optProductVariable = productVariableRepository.findById(id);
                if (!optProductVariable.isPresent()) {
                    throw new BadRequestAlertException("Product variable not found", "Reference", "deleteProductVariable");
                }
                productVariableRepository.deleteById(id);
                if (productVariableRepository.findById(id).isPresent())
                    throw new BadRequestAlertException("Error while deleting product variable", "Reference", "deleteProductVariable");
                else {
                    return ResponseEntity.ok(id);
                }
            }
        } catch (Exception e) {
            log.error("Error while deleting product variable: ", e);
            throw new BadRequestAlertException(e.getMessage(), "Reference", "deleteProductVariable");
        }
    }

    @Override
    public ResponseEntity<Long> deleteUserRole(Long id) {
        try {
            if (id == null) {
                throw new BadRequestAlertException("User role id is null", "Reference", "deleteUserRole");
            } else {
                Optional<ScRRole> optRole = roleRepository.findById(id);
                if (!optRole.isPresent()) {
                    throw new BadRequestAlertException("User role not found", "Reference", "deleteUserRole");
                }
                roleRepository.deleteById(id);
                if (roleRepository.findById(id).isPresent())
                    throw new BadRequestAlertException("Error while deleting user role", "Reference", "deleteUserRole");
                else {
                    return ResponseEntity.ok(id);
                }
            }
        } catch (Exception e) {
            log.error("Error while deleting user role: ", e);
            throw new BadRequestAlertException(e.getMessage(), "Reference", "deleteUserRole");
        }
    }

    @Override
    public ResponseEntity<Long> deleteStatus(Long id) {
        try {
            if (id == null) {
                throw new BadRequestAlertException("Status id is null", "Reference", "deleteStatus");
            } else {
                Optional<ScRStatus> optStatus = statusRepository.findById(id);
                if (!optStatus.isPresent()) {
                    throw new BadRequestAlertException("Status not found", "Reference", "deleteStatus");
                }
                statusRepository.deleteById(id);
                if (statusRepository.findById(id).isPresent())
                    throw new BadRequestAlertException("Error while deleting status", "Reference", "deleteStatus");
                else {
                    return ResponseEntity.ok(id);
                }
            }
        } catch (Exception e) {
            log.error("Error while deleting status: ", e);
            throw new BadRequestAlertException(e.getMessage(), "Reference", "deleteStatus");
        }
    }
}
