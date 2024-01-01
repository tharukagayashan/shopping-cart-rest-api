package com.projects.shoppingcart.dao.master;

import com.projects.shoppingcart.model.master.ScMShopCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScMShopCartRepository extends JpaRepository<ScMShopCart, Long> {
}