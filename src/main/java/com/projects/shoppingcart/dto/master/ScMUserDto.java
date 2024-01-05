package com.projects.shoppingcart.dto.master;

import com.projects.shoppingcart.dto.reference.ScRRoleDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ScMUserDto implements Serializable {
    private Long userId;
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