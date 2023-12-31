package com.project.onlineshop.rest.other;

import com.project.onlineshop.dto.other.ValidateNICReqDto;
import com.project.onlineshop.dto.other.ValidateNICResDto;
import com.project.onlineshop.service.other.CommonService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping("/common")
public class CommonController {
    private final CommonService commonService;

    public CommonController(CommonService commonService) {
        this.commonService = commonService;
    }

    @PostMapping("/validate-nic")
    public ResponseEntity<ValidateNICResDto> validateNic(@Valid @RequestBody ValidateNICReqDto validateNICReqDto) {
        return commonService.validateNic(validateNICReqDto);
    }

}
