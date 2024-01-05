package com.projects.shoppingcart.model.master;

import com.projects.shoppingcart.model.reference.ScRRole;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "SC_M_USER", indexes = {
        @Index(name = "fk_SC_M_USER_SC_R_ROLE1_idx", columnList = "ROLE_ID"),
        @Index(name = "SC_M_USER_USERNAME_UNQ_idx", columnList = "USERNAME")
})
public class ScMUser {

    @Id
    @GeneratedValue(generator = "SC_M_USER", strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID", nullable = false)
    private Long userId;

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

    @Column(name = "IS_ACTIVE")
    private Boolean isActive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROLE_ID", referencedColumnName = "ROLE_ID", nullable = false)
    private ScRRole scRRole;

    @OneToMany(mappedBy = "scMUser", fetch = FetchType.LAZY)
    private List<ScMOrder> scMOrders;

    @OneToMany(mappedBy = "scMUser", fetch = FetchType.LAZY)
    private List<ScMShippingInfo> scMShippingInfos;

}