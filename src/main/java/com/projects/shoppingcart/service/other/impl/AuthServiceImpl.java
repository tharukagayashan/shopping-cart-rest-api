package com.projects.shoppingcart.service.other.impl;

import com.projects.shoppingcart.config.JWTUtils;
import com.projects.shoppingcart.config.PasswordUtils;
import com.projects.shoppingcart.dao.master.ScMUserRepository;
import com.projects.shoppingcart.dao.reference.ScRRoleRepository;
import com.projects.shoppingcart.dto.auth.LoginReqDto;
import com.projects.shoppingcart.dto.auth.LoginResponseDto;
import com.projects.shoppingcart.dto.auth.TokenDto;
import com.projects.shoppingcart.dto.auth.UserRegisterReqDto;
import com.projects.shoppingcart.dto.master.ScMUserDto;
import com.projects.shoppingcart.error.BadRequestAlertException;
import com.projects.shoppingcart.mapper.master.ScMUserMapper;
import com.projects.shoppingcart.model.master.ScMUser;
import com.projects.shoppingcart.model.reference.ScRRole;
import com.projects.shoppingcart.service.other.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    private final ScMUserMapper userMapper;
    private final ScMUserRepository userRepository;
    private final ScRRoleRepository roleRepository;

    public AuthServiceImpl(ScMUserMapper userMapper, ScMUserRepository userRepository, ScRRoleRepository roleRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public ResponseEntity<ScMUserDto> registerUser(UserRegisterReqDto userRegisterReqDto) {
        try {

            Optional<ScMUser> optUser = userRepository.findByUsername(userRegisterReqDto.getUsername());
            if (optUser.isPresent()) {
                throw new BadRequestAlertException("Username already exists", "user", "user");
            } else {
                Optional<ScRRole> optRole = roleRepository.findById(userRegisterReqDto.getRoleId());

                if (!optRole.isPresent()) {
                    throw new BadRequestAlertException("Role not found", "user", "user");
                } else {
                    log.info("Role and Branch found");
                }

                String encryptedPassword = PasswordUtils.encodePassword(userRegisterReqDto.getPassword());

                ScMUser user = new ScMUser();
                user.setFirstName(userRegisterReqDto.getFirstName());
                user.setLastName(userRegisterReqDto.getLastName());
                user.setEmail(userRegisterReqDto.getEmail());
                user.setUsername(userRegisterReqDto.getUsername());
                user.setPassword(encryptedPassword);
                user.setMobileNo(userRegisterReqDto.getMobileNo());
                user.setIsActive(true);
                user.setScRRole(optRole.get());
                user = userRepository.save(user);
                if (user.getUserId() == null) {
                    throw new BadRequestAlertException("Error while registering user member", "user", "user");
                } else {
                    log.info("User member registered successfully");
                    return ResponseEntity.ok(userMapper.toDto(user));
                }
            }
        } catch (Exception e) {
            log.error("Error while registering user member: {}", e.getMessage());
            throw new BadRequestAlertException(e.getMessage(), "user", "user");
        }
    }

    @Override
    public ResponseEntity<LoginResponseDto> loginUser(LoginReqDto loginReqDto) {
        try {
            Optional<ScMUser> optUser = userRepository.findByUsername(loginReqDto.getUsername());
            if (!optUser.isPresent()) {
                throw new BadRequestAlertException("Invalid username or password", "user", "user");
            } else {
                ScMUser user = optUser.get();
                if (!PasswordUtils.isPasswordValid(loginReqDto.getPassword(), user.getPassword())) {
                    throw new BadRequestAlertException("Invalid username or password", "user", "user");
                } else {
                    if (!user.getIsActive()) {
                        throw new BadRequestAlertException("User member is not active", "user", "user");
                    } else {

                        try {
                            TokenDto tokenDto = new TokenDto();
                            tokenDto.setUsername(user.getUsername());
                            tokenDto.setFirstName(user.getFirstName());
                            tokenDto.setLastName(user.getLastName());
                            tokenDto.setRole(user.getScRRole().getRoleName());
                            tokenDto.setEmail(user.getEmail());
                            tokenDto.setRoleId(user.getScRRole().getRoleId());

                            String generatedToken = JWTUtils.generateJWTToken(tokenDto);

                            LoginResponseDto loginResponseDto = new LoginResponseDto();
                            loginResponseDto.setToken(generatedToken);
                            loginResponseDto.setDetails(tokenDto);

                            return ResponseEntity.ok(loginResponseDto);
                        } catch (Exception e) {
                            log.error("Error while generating token: {}", e.getMessage());
                            throw new BadRequestAlertException(e.getMessage(), "user", "user");
                        }
                    }
                }
            }

        } catch (Exception e) {
            log.error("Error while logging in user member: {}", e.getMessage());
            throw new BadRequestAlertException(e.getMessage(), "user", "user");
        }
    }

    @Override
    public ResponseEntity<ScMUserDto> getLoginUserDetails(String token) {
        try {
            if (!JWTUtils.validateJWTToken(token)) {
                throw new BadRequestAlertException("Invalid token", "user", "user");
            } else {
                String username = JWTUtils.getUsernameFromToken(token);
                Optional<ScMUser> optUser = userRepository.findByUsername(username);
                if (!optUser.isPresent()) {
                    throw new BadRequestAlertException("Invalid token", "user", "user");
                } else {
                    ScMUser user = optUser.get();
                    if (!user.getIsActive()) {
                        throw new BadRequestAlertException("User member is not active", "user", "user");
                    } else {
                        return ResponseEntity.ok(userMapper.toDto(user));
                    }
                }
            }
        } catch (Exception e) {
            log.error("Error while getting login user details: {}", e.getMessage());
            throw new BadRequestAlertException(e.getMessage(), "user", "user");
        }
    }

    @Override
    public ResponseEntity<TokenDto> getLoginUserByToken(String token) {
        try {
            if (JWTUtils.isTokenExpired(token) && JWTUtils.validateJWTToken(token)) {
                TokenDto tokenDto = JWTUtils.getTokenDetails(token);
                return ResponseEntity.ok(tokenDto);
            } else {
                throw new BadRequestAlertException("Invalid token", "ERROR", "ERROR");
            }
        } catch (Exception e) {
            log.error("Error while getting login user details: {}", e.getMessage());
            throw new BadRequestAlertException(e.getMessage(), "ERROR", "ERROR");
        }
    }
}
