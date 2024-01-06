package com.projects.shoppingcart.dao.master;

import com.projects.shoppingcart.model.master.ScMProductVariableMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScMProductVariableMappingRepository extends JpaRepository<ScMProductVariableMapping, Long> {

    List<ScMProductVariableMapping> findByScMProductProductId(Long productId);

}