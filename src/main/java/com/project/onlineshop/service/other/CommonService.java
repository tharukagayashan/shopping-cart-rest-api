package com.project.onlineshop.service.other;

import com.project.onlineshop.dto.other.ValidateNICReqDto;
import com.project.onlineshop.dto.other.ValidateNICResDto;
import org.springframework.http.ResponseEntity;

public interface CommonService {
    ResponseEntity<ValidateNICResDto> validateNic(ValidateNICReqDto validateNICReqDto);
}
