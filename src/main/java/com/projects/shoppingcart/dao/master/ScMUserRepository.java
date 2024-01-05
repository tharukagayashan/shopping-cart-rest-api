package com.projects.shoppingcart.dao.master;

import com.projects.shoppingcart.model.master.ScMUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ScMUserRepository extends JpaRepository<ScMUser, Long> {

    @Query("SELECT s FROM ScMUser s WHERE s.firstName LIKE %?1% OR s.lastName LIKE %?1% OR s.email LIKE %?1% OR s.mobileNo LIKE %?1%")
    Page<ScMUser> getUserForTable(String search, PageRequest of);

    Optional<ScMUser> findByUsername(String username);
}