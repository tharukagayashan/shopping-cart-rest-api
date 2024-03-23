package com.projects.shoppingcart.dao.master;

import com.projects.shoppingcart.model.master.ScMOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ScMOrderRepository extends JpaRepository<ScMOrder, Long> {

    @Query("SELECT O FROM ScMOrder O WHERE " +
            "(:statusId IS NULL OR O.scRStatus.statusId = :statusId) AND " +
            "(:userId IS NULL OR O.scMUser.userId = :userId) AND " +
            "(O.orderNo LIKE %:search% OR " +
            "O.scMUser.username LIKE %:search% OR " +
            "O.scMUser.email LIKE %:search% OR " +
            "O.scMUser.mobileNo LIKE %:search%)"
    )
    Page<ScMOrder> getAllOrder(String search, String statusId, String userId, PageRequest of);

}