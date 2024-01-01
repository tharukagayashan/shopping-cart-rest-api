package com.projects.shoppingcart.dao.master;

import com.projects.shoppingcart.model.master.ScMOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScMOrderRepository extends JpaRepository<ScMOrder, Long> {
}