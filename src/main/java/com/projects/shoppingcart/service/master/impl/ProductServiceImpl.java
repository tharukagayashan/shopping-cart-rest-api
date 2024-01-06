package com.projects.shoppingcart.service.master.impl;

import com.projects.shoppingcart.dao.master.ScMProductRepository;
import com.projects.shoppingcart.dao.master.ScMProductVariableMappingRepository;
import com.projects.shoppingcart.dao.reference.ScRProductBrandRepository;
import com.projects.shoppingcart.dao.reference.ScRProductSubCategoryRepository;
import com.projects.shoppingcart.dao.reference.ScRProductVariableRepository;
import com.projects.shoppingcart.dto.master.ScMProductDto;
import com.projects.shoppingcart.dto.master.ScMProductVariableMappingDto;
import com.projects.shoppingcart.dto.miscellaneous.ApiResponseDto;
import com.projects.shoppingcart.dto.miscellaneous.PaginationDto;
import com.projects.shoppingcart.dto.other.ProductCreateReqDto;
import com.projects.shoppingcart.dto.other.ProductVariableDto;
import com.projects.shoppingcart.dto.other.SingleProductDto;
import com.projects.shoppingcart.error.BadRequestAlertException;
import com.projects.shoppingcart.mapper.master.ScMProductMapper;
import com.projects.shoppingcart.mapper.master.ScMProductVariableMappingMapper;
import com.projects.shoppingcart.model.master.ScMProduct;
import com.projects.shoppingcart.model.master.ScMProductVariableMapping;
import com.projects.shoppingcart.model.reference.ScRProductBrand;
import com.projects.shoppingcart.model.reference.ScRProductSubCategory;
import com.projects.shoppingcart.model.reference.ScRProductVariable;
import com.projects.shoppingcart.service.master.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {
    private final ScMProductRepository productRepository;
    private final ScMProductMapper productMapper;
    private final ScMProductVariableMappingRepository productVariableMappingRepository;
    private final ScRProductVariableRepository productVariableRepository;
    private final ScRProductSubCategoryRepository productSubCategoryRepository;
    private final ScRProductBrandRepository productBrandRepository;
    private final ScMProductVariableMappingMapper productVariableMappingMapper;

    public ProductServiceImpl(ScMProductRepository productRepository, ScMProductMapper productMapper, ScMProductVariableMappingRepository productVariableMappingRepository, ScRProductVariableRepository productVariableRepository, ScRProductSubCategoryRepository productSubCategoryRepository, ScRProductBrandRepository productBrandRepository, ScMProductVariableMappingMapper productVariableMappingMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.productVariableMappingRepository = productVariableMappingRepository;
        this.productVariableRepository = productVariableRepository;
        this.productSubCategoryRepository = productSubCategoryRepository;
        this.productBrandRepository = productBrandRepository;
        this.productVariableMappingMapper = productVariableMappingMapper;
    }

    @Override
    public ResponseEntity<ScMProductDto> createProduct(ProductCreateReqDto productCreateReqDto) {
        try {
            if (productRepository.findByCode(productCreateReqDto.getCode()).isPresent()) {
                throw new BadRequestAlertException("Product code already exist", "Product", "ERROR");
            }

            Optional<ScRProductSubCategory> optProductSubCategory = productSubCategoryRepository.findById(productCreateReqDto.getSubCategoryId());
            Optional<ScRProductBrand> optProductBrand = productBrandRepository.findById(productCreateReqDto.getBrandId());
            if (!optProductSubCategory.isPresent()) {
                throw new BadRequestAlertException("Sub Category not found", "Product", "ERROR");
            } else if (!optProductBrand.isPresent()) {
                throw new BadRequestAlertException("Brand not found", "Product", "ERROR");
            } else {
                log.info("Foreign key found");
                List<ProductVariableDto> productVariableList = productCreateReqDto.getProductVariables();
                boolean isVariableFound = false;
                if (!productVariableList.isEmpty()) {
                    log.info("Product variable found");
                    isVariableFound = true;
                    productVariableList.forEach(productVariableDto -> {
                        if (productVariableDto.getVariableId() != null) {
                            if (!productVariableRepository.findById(productVariableDto.getVariableId()).isPresent()) {
                                throw new BadRequestAlertException("Product variable not found : " + productVariableDto.getVariableId(), "Product", "ERROR");
                            }
                        } else {
                            throw new BadRequestAlertException("Product variable ID null", "Product", "ERROR");
                        }
                    });
                }

                ScMProduct product = getScMProduct(productCreateReqDto, optProductSubCategory, optProductBrand);
                product = productRepository.save(product);
                if (product.getProductId() == null) {
                    throw new BadRequestAlertException("Error in creating product", "Product", "ERROR");
                } else {
                    log.info("Product created successfully");

                    if (isVariableFound) {
                        log.info("Product variable found");
                        List<ScMProductVariableMapping> productVariableMapList = new ArrayList<>();
                        for (ProductVariableDto productVariableDto : productVariableList) {
                            ScRProductVariable productVariable = productVariableRepository.findById(productVariableDto.getVariableId()).get();
                            for (String value : productVariableDto.getValues()) {
                                ScMProductVariableMapping productVariableMapping = new ScMProductVariableMapping();
                                productVariableMapping.setScMProduct(product);
                                productVariableMapping.setScRProductVariable(productVariable);
                                productVariableMapping.setVariableValue(value);
                                productVariableMapList.add(productVariableMapping);
                            }
                        }
                        productVariableMapList = productVariableMappingRepository.saveAll(productVariableMapList);
                        for (ScMProductVariableMapping productVariableMapping : productVariableMapList) {
                            if (productVariableMapping.getVariableMappingId() == null) {
                                throw new BadRequestAlertException("Error in creating product variable mapping", "Product", "ERROR");
                            }
                        }
                    }
                    return ResponseEntity.ok(productMapper.toDto(product));
                }
            }
        } catch (Exception e) {
            log.error("Error in creating product: {}", e.getMessage());
            e.printStackTrace();
            throw new BadRequestAlertException(e.getMessage(), "Product", "ERROR");
        }
    }

    private static ScMProduct getScMProduct(ProductCreateReqDto productCreateReqDto, Optional<ScRProductSubCategory> optProductSubCategory, Optional<ScRProductBrand> optProductBrand) {
        ScMProduct product = new ScMProduct();
        product.setName(productCreateReqDto.getName());
        product.setDescription(productCreateReqDto.getDescription());
        product.setCode(productCreateReqDto.getCode());
        product.setPrice(productCreateReqDto.getPrice());
        product.setQuantity(productCreateReqDto.getQuantity());
        product.setImage(productCreateReqDto.getImage());
        product.setScRProductSubCategory(optProductSubCategory.get());
        product.setScRProductBrand(optProductBrand.get());
        return product;
    }

    @Override
    public ResponseEntity<SingleProductDto> getProduct(Long productId) {
        try {
            if (productId == null) {
                throw new BadRequestAlertException("Product ID null", "Product", "ERROR");
            } else {
                Optional<ScMProduct> optProduct = productRepository.findById(productId);
                if (!optProduct.isPresent()) {
                    throw new BadRequestAlertException("Product not found", "Product", "ERROR");
                } else {
                    ScMProduct product = optProduct.get();
                    SingleProductDto singleProductDto = new SingleProductDto();
                    singleProductDto.setProductId(product.getProductId());
                    singleProductDto.setName(product.getName());
                    singleProductDto.setDescription(product.getDescription());
                    singleProductDto.setCode(product.getCode());
                    singleProductDto.setPrice(product.getPrice());
                    singleProductDto.setQuantity(product.getQuantity());
                    singleProductDto.setImage(product.getImage());
                    singleProductDto.setSubCategoryId(product.getScRProductSubCategory().getSubCategoryId());
                    singleProductDto.setSubCategoryName(product.getScRProductSubCategory().getName());
                    singleProductDto.setBrandId(product.getScRProductBrand().getBrandId());
                    singleProductDto.setBrandName(product.getScRProductBrand().getName());

                    List<ScMProductVariableMappingDto> productVariableMappingDtoList = new ArrayList<>();
                    List<ScMProductVariableMapping> productVariableMappingList = productVariableMappingRepository.findByScMProductProductId(product.getProductId());
                    if (productVariableMappingList.isEmpty()) {
                        log.info("Product variable not found");
                        singleProductDto.setProductVariables(productVariableMappingDtoList);
                    } else {
                        log.info(productVariableMappingList.size() + " product variable found");
                        productVariableMappingDtoList = productVariableMappingMapper.entityListToDtoList(productVariableMappingList);
                        singleProductDto.setProductVariables(productVariableMappingDtoList);
                    }

                    return ResponseEntity.ok(singleProductDto);
                }
            }
        } catch (Exception e) {
            log.error("Error in getting product: {}", e.getMessage());
            e.printStackTrace();
            throw new BadRequestAlertException(e.getMessage(), "Product", "ERROR");
        }
    }

    @Override
    public ResponseEntity<ScMProductDto> updateProduct(Long productId, SingleProductDto singleProductDto) {
        try {
            if (productId == null) {
                throw new BadRequestAlertException("Product ID null", "Product", "ERROR");
            } else if (!productId.equals(singleProductDto.getProductId())) {
                throw new BadRequestAlertException("Product ID not match", "Product", "ERROR");
            } else {
                Optional<ScMProduct> optProduct = productRepository.findById(productId);
                if (!optProduct.isPresent()) {
                    throw new BadRequestAlertException("Product not found", "Product", "ERROR");
                } else {
                    log.info("Product found");
                    Optional<ScRProductSubCategory> optProductSubCategory = productSubCategoryRepository.findById(singleProductDto.getSubCategoryId());
                    Optional<ScRProductBrand> optProductBrand = productBrandRepository.findById(singleProductDto.getBrandId());
                    if (!optProductSubCategory.isPresent()) {
                        throw new BadRequestAlertException("Sub Category not found", "Product", "ERROR");
                    } else if (!optProductBrand.isPresent()) {
                        throw new BadRequestAlertException("Brand not found", "Product", "ERROR");
                    } else {
                        log.info("Foreign key found");

                        List<ScMProductVariableMappingDto> productVariableList = singleProductDto.getProductVariables();
                        if (!productVariableList.isEmpty()) {
                            for (ScMProductVariableMappingDto prMapping : productVariableList) {
                                if (prMapping.getProductId() == null) {
                                    throw new BadRequestAlertException("Product Id null in product variable mapping", "Product", "ERROR");
                                } else if (!prMapping.getProductId().equals(productId)) {
                                    throw new BadRequestAlertException("Product Id not match in product variable mapping", "Product", "ERROR");
                                } else if (prMapping.getVariableId() == null) {
                                    throw new BadRequestAlertException("Product variable Id null in product variable mapping", "Product", "ERROR");
                                } else if (!productVariableRepository.findById(prMapping.getVariableId()).isPresent()) {
                                    throw new BadRequestAlertException("Product variable not found : " + prMapping.getVariableId(), "Product", "ERROR");
                                } else if (prMapping.getVariableMappingId() == null) {
                                    throw new BadRequestAlertException("Product variable mapping Id not null", "Product", "ERROR");
                                } else if (!productVariableMappingRepository.findById(prMapping.getVariableMappingId()).isPresent()) {
                                    throw new BadRequestAlertException("Product variable mapping not found : " + prMapping.getVariableMappingId(), "Product", "ERROR");
                                } else {
                                    log.info("Product variable mapping found");
                                    ScRProductVariable productVariable = productVariableRepository.findById(prMapping.getVariableId()).get();
                                    List<ScMProductVariableMapping> dbProductVariableMappingList = productVariableMappingRepository.findByScMProductProductId(productId);
                                    if (!dbProductVariableMappingList.isEmpty()) {
                                        log.info("Product variable found");

                                        for (ScMProductVariableMapping dbPrMapping : dbProductVariableMappingList) {
                                            boolean isFound = false;
                                            for (ScMProductVariableMappingDto prMappingDto : productVariableList) {
                                                if (dbPrMapping.getVariableMappingId().equals(prMappingDto.getVariableMappingId())) {
                                                    isFound = true;
                                                    break;
                                                }
                                            }
                                            if (!isFound) {
                                                log.info("Product variable mapping not found");
                                                productVariableMappingRepository.deleteById(dbPrMapping.getVariableMappingId());
                                            }
                                        }
                                        ScMProductVariableMapping productVariableMapping = productVariableMappingMapper.toEntity(prMapping);
                                        productVariableMapping.setVariableValue(prMapping.getVariableValue());
                                        productVariableMapping.setScRProductVariable(productVariable);
                                        productVariableMapping.setScMProduct(optProduct.get());
                                        productVariableMapping = productVariableMappingRepository.save(productVariableMapping);
                                        log.info("Product variable mapping updated successfully : {}", productVariableMapping);
                                    }
                                }
                            }
                        }

                        ScMProduct product = optProduct.get();
                        product.setName(singleProductDto.getName());
                        product.setDescription(singleProductDto.getDescription());
                        product.setCode(singleProductDto.getCode());
                        product.setPrice(singleProductDto.getPrice());
                        product.setQuantity(singleProductDto.getQuantity());
                        product.setImage(singleProductDto.getImage());
                        product.setScRProductSubCategory(optProductSubCategory.get());
                        product.setScRProductBrand(optProductBrand.get());
                        product = productRepository.save(product);
                        log.info("Product updated successfully : {}", product);
                        return ResponseEntity.ok(productMapper.toDto(product));
                    }
                }
            }
        } catch (Exception e) {
            log.error("Error in updating product: {}", e.getMessage());
            e.printStackTrace();
            throw new BadRequestAlertException(e.getMessage(), "Product", "ERROR");
        }
    }

    @Override
    public ResponseEntity<ApiResponseDto<List<SingleProductDto>>> getAllProducts(Integer page, Integer perPage, String sort, String direction, String search, Long subCategoryId, Long brandId) {
        try {
            Page<ScMProduct> dbData;
            if (direction.equalsIgnoreCase("asc")) {
                dbData = productRepository.findAllProductsForTable(search, PageRequest.of(page, perPage, Sort.by(Sort.Direction.ASC, sort)), subCategoryId, brandId);
            } else {
                dbData = productRepository.findAllProductsForTable(search, PageRequest.of(page, perPage, Sort.by(Sort.Direction.DESC, sort)), subCategoryId, brandId);
            }

            List<SingleProductDto> singleProductDtoList = productMapper.toSingleProductDtoList(dbData.getContent());
            for (SingleProductDto s : singleProductDtoList) {
                List<ScMProductVariableMappingDto> scMProductVariableMappingDtoList = new ArrayList<>();
                List<ScMProductVariableMapping> productVariableMappingList = productVariableMappingRepository.findByScMProductProductId(s.getProductId());
                if (productVariableMappingList.isEmpty()) {
                    s.setProductVariables(scMProductVariableMappingDtoList);
                } else {
                    scMProductVariableMappingDtoList = productVariableMappingMapper.entityListToDtoList(productVariableMappingList);
                    s.setProductVariables(scMProductVariableMappingDtoList);
                }
            }

            ApiResponseDto<List<SingleProductDto>> responseDto = new ApiResponseDto<>();
            PaginationDto paginationDto = new PaginationDto();
            paginationDto.setTotal(dbData.getTotalElements());
            responseDto.setPagination(paginationDto);
            responseDto.setResult(singleProductDtoList);
            return ResponseEntity.ok(responseDto);
        } catch (Exception e) {
            log.error("Error in getting all products: {}", e.getMessage());
            e.printStackTrace();
            throw new BadRequestAlertException(e.getMessage(), "Product", "ERROR");
        }
    }

    @Override
    public ResponseEntity<Long> deleteProduct(Long productId) {
        try {
            if (productId == null) {
                throw new BadRequestAlertException("Product ID null", "Product", "ERROR");
            } else {
                Optional<ScMProduct> optProduct = productRepository.findById(productId);
                if (!optProduct.isPresent()) {
                    throw new BadRequestAlertException("Product not found", "Product", "ERROR");
                } else {
                    log.info("Product found");
                    List<ScMProductVariableMapping> productVariableMappingList = productVariableMappingRepository.findByScMProductProductId(productId);
                    if (!productVariableMappingList.isEmpty()) {
                        log.info("Product variable found");
                        productVariableMappingRepository.deleteAll(productVariableMappingList);
                    }
                    if (!productVariableMappingRepository.findByScMProductProductId(productId).isEmpty()) {
                        throw new BadRequestAlertException("Error in deleting product variable mapping", "Product", "ERROR");
                    }
                    log.info("Product variable mapping deleted successfully");

                    productRepository.deleteById(productId);
                    log.info("Product deleted successfully");
                    if (productRepository.findById(productId).isPresent()) {
                        throw new BadRequestAlertException("Error in deleting product", "Product", "ERROR");
                    } else {
                        return ResponseEntity.ok(productId);
                    }
                }
            }
        } catch (Exception e) {
            log.error("Error in deleting product: {}", e.getMessage());
            e.printStackTrace();
            throw new BadRequestAlertException(e.getMessage(), "Product", "ERROR");
        }
    }

}
