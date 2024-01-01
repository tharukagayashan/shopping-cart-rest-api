package com.projects.shoppingcart.dao.master;

import com.projects.shoppingcart.model.master.ScMCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScMCustomerRepository extends JpaRepository<ScMCustomer, Long> {
}