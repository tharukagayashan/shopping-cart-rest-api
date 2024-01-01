package com.projects.shoppingcart.service.other;

import com.projects.shoppingcart.dto.other.ValidateNICReqDto;
import com.projects.shoppingcart.dto.other.ValidateNICResDto;
import org.springframework.http.ResponseEntity;

public interface CommonService {
    ResponseEntity<ValidateNICResDto> validateNic(ValidateNICReqDto validateNICReqDto);
}
