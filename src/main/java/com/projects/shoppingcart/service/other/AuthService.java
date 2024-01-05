package com.projects.shoppingcart.service.other;

import com.projects.shoppingcart.dto.auth.LoginReqDto;
import com.projects.shoppingcart.dto.auth.LoginResponseDto;
import com.projects.shoppingcart.dto.auth.TokenDto;
import com.projects.shoppingcart.dto.auth.UserRegisterReqDto;
import com.projects.shoppingcart.dto.master.ScMUserDto;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<ScMUserDto> registerUser(UserRegisterReqDto staffRegisterReqDto);

    ResponseEntity<LoginResponseDto> loginUser(LoginReqDto loginReqDto);

    ResponseEntity<ScMUserDto> getLoginUserDetails(String token);

    ResponseEntity<TokenDto> getLoginUserByToken(String token);
}
