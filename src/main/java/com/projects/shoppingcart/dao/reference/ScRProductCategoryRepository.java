package com.projects.shoppingcart.dao.reference;

import com.projects.shoppingcart.model.reference.ScRProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScRProductCategoryRepository extends JpaRepository<ScRProductCategory, Long> {
}