package com.projects.shoppingcart.other;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class CustomMethods {

    public String validateNIC(String nic) {
        String msg;
        if (nic.length() == 10) {
            if (nic.charAt(9) == 'V' || nic.charAt(9) == 'v') {
                msg = "Valid NIC";
            } else {
                msg = "Invalid NIC";
            }
        } else if (nic.length() == 12) {
            msg = "Valid NIC";
        } else {
            msg = "Invalid NIC";
        }
        return msg;
    }

}
