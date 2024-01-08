package com.projects.shoppingcart.dto.other;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShippingInfoCreateDto {
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
    private Long userId;
}
