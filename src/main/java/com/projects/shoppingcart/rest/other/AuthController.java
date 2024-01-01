package com.projects.shoppingcart.rest.other;

import com.projects.shoppingcart.dto.auth.LoginReqDto;
import com.projects.shoppingcart.dto.auth.LoginResponseDto;
import com.projects.shoppingcart.dto.auth.StaffRegisterReqDto;
import com.projects.shoppingcart.dto.auth.TokenDto;
import com.projects.shoppingcart.dto.master.ScMStaffDto;
import com.projects.shoppingcart.service.other.AuthService;
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
