package com.projects.shoppingcart.dao.reference;

import com.projects.shoppingcart.model.reference.ScRStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScRStatusRepository extends JpaRepository<ScRStatus, Long> {
    Optional<ScRStatus> findByCode(String code);
}