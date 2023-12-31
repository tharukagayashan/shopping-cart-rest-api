package com.project.onlineshop.dto.master;

import com.project.onlineshop.dto.reference.ScRRoleDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ScMStaffDto implements Serializable {
    private Long staffId;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private String mobileNo;
    private String address;
    private Boolean isActive;

    private Long roleId;

    private ScRRoleDto scRRole;
}