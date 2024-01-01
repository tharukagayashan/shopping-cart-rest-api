package com.projects.shoppingcart.service.other.impl;

import com.projects.shoppingcart.dto.other.ValidateNICReqDto;
import com.projects.shoppingcart.dto.other.ValidateNICResDto;
import com.projects.shoppingcart.error.BadRequestAlertException;
import com.projects.shoppingcart.other.CustomMethods;
import com.projects.shoppingcart.service.other.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CommonServiceImpl implements CommonService {
    private final CustomMethods customMethods;

    public CommonServiceImpl(CustomMethods customMethods) {
        this.customMethods = customMethods;
    }

    @Override
    public ResponseEntity<ValidateNICResDto> validateNic(ValidateNICReqDto validateNICReqDto) {
        try {
            String msg = customMethods.validateNIC(validateNICReqDto.getNic());
            ValidateNICResDto validateNICResDto = new ValidateNICResDto(validateNICReqDto.getNic(), msg);
            if (msg.equals("Valid NIC")) {
                return ResponseEntity.ok(validateNICResDto);
            } else {
                return ResponseEntity.badRequest().body(validateNICResDto);
            }
        } catch (Exception e) {
            log.error("Error occurred while validating NIC: {}", e.getMessage());
            throw new BadRequestAlertException(e.getMessage(), "ValidateNICReqDto", "ERROR");
        }
    }
}