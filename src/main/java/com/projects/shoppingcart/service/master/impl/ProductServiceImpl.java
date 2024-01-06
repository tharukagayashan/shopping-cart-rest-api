package com.projects.shoppingcart.service.master.impl;

import com.projects.shoppingcart.dao.master.ScMProductRepository;
import com.projects.shoppingcart.dao.master.ScMProductVariableMappingRepository;
import com.projects.shoppingcart.dao.reference.ScRProductBrandRepository;
import com.projects.shoppingcart.dao.reference.ScRProductSubCategoryRepository;
import com.projects.shoppingcart.dao.reference.ScRProductVariableRepository;
import com.projects.shoppingcart.dto.master.ScMProductDto;
import com.projects.shoppingcart.dto.other.ProductCreateReqDto;
import com.projects.shoppingcart.dto.other.ProductVariableDto;
import com.projects.shoppingcart.error.BadRequestAlertException;
import com.projects.shoppingcart.mapper.master.ScMProductMapper;
import com.projects.shoppingcart.model.master.ScMProduct;
import com.projects.shoppingcart.model.master.ScMProductVariableMapping;
import com.projects.shoppingcart.model.reference.ScRProductBrand;
import com.projects.shoppingcart.model.reference.ScRProductSubCategory;
import com.projects.shoppingcart.model.reference.ScRProductVariable;
import com.projects.shoppingcart.service.master.ProductService;
import lombok.extern.slf4j.Slf4j;
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

    public ProductServiceImpl(ScMProductRepository productRepository, ScMProductMapper productMapper, ScMProductVariableMappingRepository productVariableMappingRepository, ScRProductVariableRepository productVariableRepository, ScRProductSubCategoryRepository productSubCategoryRepository, ScRProductBrandRepository productBrandRepository) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.productVariableMappingRepository = productVariableMappingRepository;
        this.productVariableRepository = productVariableRepository;
        this.productSubCategoryRepository = productSubCategoryRepository;
        this.productBrandRepository = productBrandRepository;
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
}
