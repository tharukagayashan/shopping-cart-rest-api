package com.project.onlineshop.rest.other;

import com.project.onlineshop.dto.auth.LoginReqDto;
import com.project.onlineshop.dto.auth.LoginResponseDto;
import com.project.onlineshop.dto.auth.StaffRegisterReqDto;
import com.project.onlineshop.dto.auth.TokenDto;
import com.project.onlineshop.dto.master.ScMStaffDto;
import com.project.onlineshop.service.other.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<ScMStaffDto> registerStaffMember(@RequestBody StaffRegisterReqDto staffRegisterReqDto) {
        return authService.registerStaffMember(staffRegisterReqDto);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> loginStaffMember(@RequestBody LoginReqDto loginReqDto) {
        return authService.loginStaffMember(loginReqDto);
    }

    @GetMapping("/login-user-details")
    public ResponseEntity<ScMStaffDto> getLoginUserDetails(@RequestHeader("Authorization") String token) {
        return authService.getLoginUserDetails(token);
    }

    @GetMapping("/login-user-by-token")
    public ResponseEntity<TokenDto> getLoginUserByToken(@RequestParam("token") String token) {
        return authService.getLoginUserByToken(token);
    }

}
