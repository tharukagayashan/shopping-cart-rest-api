package com.project.onlineshop.dao.master;

import com.project.onlineshop.model.master.ScMOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScMOrderRepository extends JpaRepository<ScMOrder, Long> {
}