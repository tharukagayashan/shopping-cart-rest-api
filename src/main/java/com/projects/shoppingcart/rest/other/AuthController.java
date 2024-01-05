package com.projects.shoppingcart.rest.other;

import com.projects.shoppingcart.dto.auth.LoginReqDto;
import com.projects.shoppingcart.dto.auth.LoginResponseDto;
import com.projects.shoppingcart.dto.auth.TokenDto;
import com.projects.shoppingcart.dto.auth.UserRegisterReqDto;
import com.projects.shoppingcart.dto.master.ScMUserDto;
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
    public ResponseEntity<ScMUserDto> registerUser(@RequestBody UserRegisterReqDto userRegisterReqDto) {
        return authService.registerUser(userRegisterReqDto);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> loginUser(@RequestBody LoginReqDto loginReqDto) {
        return authService.loginUser(loginReqDto);
    }

    @GetMapping("/login-user-details")
    public ResponseEntity<ScMUserDto> getLoginUserDetails(@RequestHeader("Authorization") String token) {
        return authService.getLoginUserDetails(token);
    }

    @GetMapping("/login-user-by-token")
    public ResponseEntity<TokenDto> getLoginUserByToken(@RequestParam("token") String token) {
        return authService.getLoginUserByToken(token);
    }

}
