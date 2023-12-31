package com.project.onlineshop.model.master;

import lombok.*;

import javax.persistence.*;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "SC_M_SHIPPING_INFO")
public class ScMShippingInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SC_M_SHIPPING_INFO")
    @Column(name = "SHIPPING_INFO_ID", nullable = false)
    private Long shippingInfoId;

    @Column(name = "FULL NAME", length = 60)
    private String fullName;

    @Column(name = "MOBILE_NO", length = 15)
    private String mobileNo;

    @Column(name = "EMAIL", length = 50)
    private String email;

    @Column(name = "CITY", length = 50)
    private String city;

    @Column(name = "PROVINCE", length = 50)
    private String province;

    @Column(name = "POSTAL_CODE", length = 10)
    private String postalCode;

    @Column(name = "COUNTRY", length = 50)
    private String country;

    @Column(name = "ADDRESS_LINE_1", length = 100)
    private String addressLine1;

    @Column(name = "ADDRESS_LINE_2", length = 100)
    private String addressLine2;

    @Column(name = "ADDRESS_LINE_3", length = 100)
    private String addressLine3;

    @Column(name = "IS_DEFAULT")
    private Boolean isDefault;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID", referencedColumnName = "CUSTOMER_ID", nullable = false)
    private ScMCustomer scMCustomer;

}
