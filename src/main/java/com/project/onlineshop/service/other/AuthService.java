package com.project.onlineshop.service.other;

import com.project.onlineshop.dto.auth.LoginReqDto;
import com.project.onlineshop.dto.auth.LoginResponseDto;
import com.project.onlineshop.dto.auth.StaffRegisterReqDto;
import com.project.onlineshop.dto.auth.TokenDto;
import com.project.onlineshop.dto.master.ScMStaffDto;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<ScMStaffDto> registerStaffMember(StaffRegisterReqDto staffRegisterReqDto);

    ResponseEntity<LoginResponseDto> loginStaffMember(LoginReqDto loginReqDto);

    ResponseEntity<ScMStaffDto> getLoginUserDetails(String token);

    ResponseEntity<TokenDto> getLoginUserByToken(String token);
}
