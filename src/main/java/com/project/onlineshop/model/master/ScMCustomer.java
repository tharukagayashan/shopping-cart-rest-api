package com.project.onlineshop.model.master;

import com.project.onlineshop.model.reference.ScRStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "SC_M_CUSTOMER", indexes = {
        @Index(name = "SC_M_CUSTOMER_NIC_UNQ_IDX", columnList = "NIC", unique = true),

})
public class ScMCustomer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SC_M_CUSTOMER")
    @Column(name = "CUSTOMER_ID", nullable = false)
    private Long customerId;

    @Column(name = "FIRST_NAME", length = 50)
    private String firstName;

    @Column(name = "LAST_NAME", length = 50)
    private String lastName;

    @Column(name = "DOB")
    private LocalDate dob;

    @Column(name = "EMAIL", length = 60)
    private String email;

    @Column(name = "MOBILE_NO", length = 10)
    private String mobileNo;

    @Column(name = "NIC", length = 12, unique = true)
    private String nic;

    @Column(name = "GENDER", length = 1)
    private String gender;

    @Column(name = "IS_ACTIVE")
    private Boolean isActive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STATUS_ID", referencedColumnName = "STATUS_ID", nullable = false)
    private ScRStatus scRStatus;

    @OneToMany(mappedBy = "scMCustomer", fetch = FetchType.LAZY)
    private List<ScMShippingInfo> scMShippingInfos;

    @OneToMany(mappedBy = "scMCustomer", fetch = FetchType.LAZY)
    private List<ScMOrder> scMOrders;

}