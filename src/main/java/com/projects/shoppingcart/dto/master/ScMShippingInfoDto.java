package com.projects.shoppingcart.dto.master;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ScMShippingInfoDto {
    private Long shippingInfoId;
    private String fullName;
    private String mobileNo;
    private String email;
    private String city;
    private String province;
    private String postalCode;
    private String country;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private Boolean isDefault;
    private ScMCustomerDto scMCustomer;
}