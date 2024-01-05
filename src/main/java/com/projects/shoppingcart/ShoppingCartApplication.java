package com.projects.shoppingcart;

import com.projects.shoppingcart.dao.reference.ScRRoleRepository;
import com.projects.shoppingcart.dao.reference.ScRStatusRepository;
import com.projects.shoppingcart.model.reference.ScRRole;
import com.projects.shoppingcart.model.reference.ScRStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

@Slf4j
@SpringBootApplication
public class ShoppingCartApplication {

    private static final ScRRoleRepository roleRepository = null;
    private static final ScRStatusRepository statusRepository = null;

    public static void main(String[] args) {
        SpringApplication.run(ShoppingCartApplication.class, args);
        createDefaultRecords();
    }

    static void createDefaultRecords() {
        Optional<ScRRole> optRole1 = roleRepository.findById(new Long(1));
        if (!optRole1.isPresent()) {
            ScRRole role1 = new ScRRole();
            role1.setRoleName("Admin");
            role1.setDescription("Admin");
            role1.setIsActive(true);
            roleRepository.save(role1);
        } else {
            log.info("Admin already exists");
        }

        Optional<ScRRole> optRole2 = roleRepository.findById(new Long(2));
        if (!optRole2.isPresent()) {
            ScRRole role2 = new ScRRole();
            role2.setRoleName("Customer");
            role2.setDescription("Customer");
            role2.setIsActive(true);
            roleRepository.save(role2);
        } else {
            log.info("Customer already exists");
        }

        Optional<ScRStatus> optStatus1 = statusRepository.findById(new Long(1));
        if (!optStatus1.isPresent()) {
            ScRStatus status1 = new ScRStatus();
            status1.setName("New");
            status1.setCode("New");
            status1.setType("Order");
            statusRepository.save(status1);
        } else {
            log.info("New already exists");
        }

        Optional<ScRStatus> optStatus2 = statusRepository.findById(new Long(2));
        if (!optStatus2.isPresent()) {
            ScRStatus status2 = new ScRStatus();
            status2.setName("Pending");
            status2.setCode("Pending");
            status2.setType("Order");
            statusRepository.save(status2);
        } else {
            log.info("Pending already exists");
        }

        Optional<ScRStatus> optStatus3 = statusRepository.findById(new Long(3));
        if (!optStatus3.isPresent()) {
            ScRStatus status3 = new ScRStatus();
            status3.setName("Approved");
            status3.setCode("Approved");
            status3.setType("Order");
            statusRepository.save(status3);
        } else {
            log.info("Approved already exists");
        }
    }

}