package com.projects.shoppingcart.dao.master;

import com.projects.shoppingcart.model.master.ScMProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScMProductRepository extends JpaRepository<ScMProduct, Long> {
    Optional<ScMProduct> findByCode(String code);
}