package com.projects.shoppingcart.dao.master;

import com.projects.shoppingcart.model.master.ScMProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ScMProductRepository extends JpaRepository<ScMProduct, Long> {
    Optional<ScMProduct> findByCode(String code);

    @Query(value = "SELECT P FROM ScMProduct P WHERE " +
            "(:subCategoryId IS NULL OR P.scRProductSubCategory.subCategoryId = :subCategoryId) AND " +
            "(:brandId IS NULL OR P.scRProductBrand.brandId = :brandId) AND " +
            "(lower(P.name) LIKE %:search% OR " +
            "lower(P.code) LIKE %:search%) AND P.isActive = true")
    Page<ScMProduct> findAllProductsForTable(@Param("search") String search, PageRequest of,@Param("subCategoryId") Long subCategoryId,@Param("brandId") Long brandId);
}