package com.project.onlineshop.model.master;

import com.project.onlineshop.model.reference.ScRRole;
import lombok.*;

import javax.persistence.*;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "SC_M_STAFF", indexes = {
        @Index(name = "fk_SC_M_STAFF_SC_R_ROLE1_idx", columnList = "ROLE_ID"),
        @Index(name = "SC_M_STAFF_USERNAME_UNQ_idx", columnList = "USERNAME")
})
public class ScMStaff {

    @Id
    @GeneratedValue(generator = "SC_M_STAFF", strategy = GenerationType.IDENTITY)
    @Column(name = "STAFF_ID", nullable = false)
    private Long staffId;

    @Column(name = "FIRST_NAME", length = 50)
    private String firstName;

    @Column(name = "LAST_NAME", length = 50)
    private String lastName;

    @Column(name = "EMAIL", length = 70)
    private String email;

    @Column(name = "USERNAME", length = 30, unique = true)
    private String username;

    @Column(name = "PASSWORD", length = 200)
    private String password;

    @Column(name = "MOBILE_NO", length = 10)
    private String mobileNo;

    @Column(name = "ADDRESS", length = 80)
    private String address;

    @Column(name = "IS_ACTIVE")
    private Boolean isActive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROLE_ID", referencedColumnName = "ROLE_ID", nullable = false)
    private ScRRole scRRole;

}