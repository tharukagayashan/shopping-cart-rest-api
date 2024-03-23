package com.projects.shoppingcart.dao.master;

import com.projects.shoppingcart.model.master.ScMOrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScMOrderProductRepository extends JpaRepository<ScMOrderProduct, Long> {

    List<ScMOrderProduct> findByScMOrderOrderId(Long orderId);

}