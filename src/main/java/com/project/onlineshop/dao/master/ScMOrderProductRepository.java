package com.project.onlineshop.dao.master;

import com.project.onlineshop.model.master.ScMOrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScMOrderProductRepository extends JpaRepository<ScMOrderProduct, Long> {
}