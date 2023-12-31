package com.project.onlineshop.dao.master;

import com.project.onlineshop.model.master.ScMCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScMCustomerRepository extends JpaRepository<ScMCustomer, Long> {
}