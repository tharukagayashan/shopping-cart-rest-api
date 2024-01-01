package com.projects.shoppingcart.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TokenDto {
    private String username;
    private String firstName;
    private String lastName;
    private String role;
    private String email;
    private Long roleId;
}
