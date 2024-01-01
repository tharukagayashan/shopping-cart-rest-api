package com.projects.shoppingcart.service.other;

import com.projects.shoppingcart.dto.auth.LoginReqDto;
import com.projects.shoppingcart.dto.auth.LoginResponseDto;
import com.projects.shoppingcart.dto.auth.StaffRegisterReqDto;
import com.projects.shoppingcart.dto.auth.TokenDto;
import com.projects.shoppingcart.dto.master.ScMStaffDto;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<ScMStaffDto> registerStaffMember(StaffRegisterReqDto staffRegisterReqDto);

    ResponseEntity<LoginResponseDto> loginStaffMember(LoginReqDto loginReqDto);

    ResponseEntity<ScMStaffDto> getLoginUserDetails(String token);

    ResponseEntity<TokenDto> getLoginUserByToken(String token);
}
