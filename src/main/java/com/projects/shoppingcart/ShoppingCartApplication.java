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

    private static ScRRoleRepository roleRepository = null;
    private static ScRStatusRepository statusRepository = null;

    public ShoppingCartApplication(ScRRoleRepository roleRepository, ScRStatusRepository statusRepository) {
        this.roleRepository = roleRepository;
        this.statusRepository = statusRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(ShoppingCartApplication.class, args);
        insertRecordsFromCommandLine();
    }

    static void insertRecordsFromCommandLine() {
        Optional<ScRRole> optRole1 = roleRepository.findById(new Long(1));
        if (!optRole1.isPresent()) {
            ScRRole role1 = new ScRRole();
            role1.setRoleName("Admin");
            role1.setDescription("Admin");
            role1.setIsActive(true);
            roleRepository.save(role1);
            log.info("Admin created");
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
            log.info("Customer created");
        } else {
            log.info("Customer already exists");
        }

        Optional<ScRStatus> optStatus1 = statusRepository.findById(new Long(1));
        if (!optStatus1.isPresent()) {
            ScRStatus status1 = new ScRStatus();
            status1.setName("New");
            status1.setCode("N");
            status1.setType("Order");
            statusRepository.save(status1);
            log.info("Status New created");
        } else {
            log.info("New already exists");
        }

        Optional<ScRStatus> optStatus2 = statusRepository.findById(new Long(2));
        if (!optStatus2.isPresent()) {
            ScRStatus status2 = new ScRStatus();
            status2.setName("Pending");
            status2.setCode("P");
            status2.setType("Order");
            statusRepository.save(status2);
            log.info("Status Pending created");
        } else {
            log.info("Pending already exists");
        }

        Optional<ScRStatus> optStatus3 = statusRepository.findById(new Long(3));
        if (!optStatus3.isPresent()) {
            ScRStatus status3 = new ScRStatus();
            status3.setName("Approved");
            status3.setCode("A");
            status3.setType("Order");
            statusRepository.save(status3);
            log.info("Status Approved created");
        } else {
            log.info("Approved already exists");
        }
    }

}