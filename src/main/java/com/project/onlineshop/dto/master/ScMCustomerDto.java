package com.project.onlineshop.dto.master;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ScMCustomerDto {
    private Long customerId;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private String email;
    private String mobileNo;
    private String nic;
    private String gender;
    private Boolean isActive;
}