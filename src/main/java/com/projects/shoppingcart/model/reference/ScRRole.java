package com.projects.shoppingcart.model.reference;

import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "SC_R_ROLE")
public class ScRRole {

    @Id
    @GeneratedValue(generator = "SC_R_ROLE", strategy = GenerationType.IDENTITY)
    @Column(name = "ROLE_ID", nullable = false)
    private Long roleId;

    @Column(name = "NAME", length = 40)
    private String roleName;

    @Column(name = "DESCRIPTION", length = 100)
    private String description;

    @Column(name = "IS_ACTIVE")
    private Boolean isActive;

}
