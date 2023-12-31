package com.project.onlineshop.dao.master;

import com.project.onlineshop.model.master.ScMStaff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ScMStaffRepository extends JpaRepository<ScMStaff, Long> {

    @Query("SELECT s FROM ScMStaff s WHERE s.firstName LIKE %?1% OR s.lastName LIKE %?1% OR s.email LIKE %?1% OR s.mobileNo LIKE %?1% OR s.address LIKE %?1%")
    Page<ScMStaff> getStaffForTable(String search, PageRequest of);

    Optional<ScMStaff> findByUsername(String username);
}