package com.projects.shoppingcart.dao.master;

import com.projects.shoppingcart.model.master.ScMProduct;
import com.projects.shoppingcart.model.master.ScMShopCart;
import com.projects.shoppingcart.model.master.ScMUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ScMShopCartRepository extends JpaRepository<ScMShopCart, Long> {
    List<ScMShopCart> findAllByScMUser(ScMUser scMUser);
    Optional<ScMShopCart> findByScMUserAndScMProduct(ScMUser scMUser, ScMProduct scMProduct);
}