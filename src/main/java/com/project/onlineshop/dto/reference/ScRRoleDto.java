package com.project.onlineshop.dto.reference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ScRRoleDto {
    private Long roleId;
    private String roleName;
    private String description;
    private Boolean isActive;
}