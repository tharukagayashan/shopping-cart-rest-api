package com.projects.shoppingcart.dao.reference;

import com.projects.shoppingcart.model.reference.ScRProductSubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScRProductSubCategoryRepository extends JpaRepository<ScRProductSubCategory, Long> {
    Optional<ScRProductSubCategory> findByCode(String code);
}