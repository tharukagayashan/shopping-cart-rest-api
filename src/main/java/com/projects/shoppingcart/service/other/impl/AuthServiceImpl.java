package com.projects.shoppingcart.service.other.impl;

import com.projects.shoppingcart.config.JWTUtils;
import com.projects.shoppingcart.config.PasswordUtils;
import com.projects.shoppingcart.dao.master.ScMStaffRepository;
import com.projects.shoppingcart.dao.reference.ScRRoleRepository;
import com.projects.shoppingcart.dto.auth.LoginReqDto;
import com.projects.shoppingcart.dto.auth.LoginResponseDto;
import com.projects.shoppingcart.dto.auth.StaffRegisterReqDto;
import com.projects.shoppingcart.dto.auth.TokenDto;
import com.projects.shoppingcart.dto.master.ScMStaffDto;
import com.projects.shoppingcart.error.BadRequestAlertException;
import com.projects.shoppingcart.mapper.master.ScMStaffMapper;
import com.projects.shoppingcart.model.master.ScMStaff;
import com.projects.shoppingcart.model.reference.ScRRole;
import com.projects.shoppingcart.service.other.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    private final ScMStaffMapper staffMapper;
    private final ScMStaffRepository staffRepository;
    private final ScRRoleRepository roleRepository;

    public AuthServiceImpl(ScMStaffMapper staffMapper, ScMStaffRepository staffRepository, ScRRoleRepository roleRepository) {
        this.staffMapper = staffMapper;
        this.staffRepository = staffRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public ResponseEntity<ScMStaffDto> registerStaffMember(StaffRegisterReqDto staffRegisterReqDto) {
        try {

            Optional<ScMStaff> optStaff = staffRepository.findByUsername(staffRegisterReqDto.getUsername());
            if (optStaff.isPresent()) {
                throw new BadRequestAlertException("Username already exists", "staff", "staff");
            } else {
                Optional<ScRRole> optRole = roleRepository.findById(staffRegisterReqDto.getRoleId());

                if (!optRole.isPresent()) {
                    throw new BadRequestAlertException("Role not found", "staff", "staff");
                } else {
                    log.info("Role and Branch found");
                }

                String encryptedPassword = PasswordUtils.encodePassword(staffRegisterReqDto.getPassword());

                ScMStaff staff = new ScMStaff();
                staff.setFirstName(staffRegisterReqDto.getFirstName());
                staff.setLastName(staffRegisterReqDto.getLastName());
                staff.setEmail(staffRegisterReqDto.getEmail());
                staff.setUsername(staffRegisterReqDto.getUsername());
                staff.setPassword(encryptedPassword);
                staff.setMobileNo(staffRegisterReqDto.getMobileNo());
                staff.setAddress(staffRegisterReqDto.getAddress());
                staff.setIsActive(true);
                staff.setScRRole(optRole.get());
                staff = staffRepository.save(staff);
                if (staff.getStaffId() == null) {
                    throw new BadRequestAlertException("Error while registering staff member", "staff", "staff");
                } else {
                    log.info("Staff member registered successfully");
                    return ResponseEntity.ok(staffMapper.toDto(staff));
                }
            }
        } catch (Exception e) {
            log.error("Error while registering staff member: {}", e.getMessage());
            throw new BadRequestAlertException(e.getMessage(), "staff", "staff");
        }
    }

    @Override
    public ResponseEntity<LoginResponseDto> loginStaffMember(LoginReqDto loginReqDto) {
        try {
            Optional<ScMStaff> optStaff = staffRepository.findByUsername(loginReqDto.getUsername());
            if (!optStaff.isPresent()) {
                throw new BadRequestAlertException("Invalid username or password", "staff", "staff");
            } else {
                ScMStaff staff = optStaff.get();
                if (!PasswordUtils.isPasswordValid(loginReqDto.getPassword(), staff.getPassword())) {
                    throw new BadRequestAlertException("Invalid username or password", "staff", "staff");
                } else {
                    if (!staff.getIsActive()) {
                        throw new BadRequestAlertException("Staff member is not active", "staff", "staff");
                    } else {

                        try {
                            TokenDto tokenDto = new TokenDto();
                            tokenDto.setUsername(staff.getUsername());
                            tokenDto.setFirstName(staff.getFirstName());
                            tokenDto.setLastName(staff.getLastName());
                            tokenDto.setRole(staff.getScRRole().getRoleName());
                            tokenDto.setEmail(staff.getEmail());
                            tokenDto.setRoleId(staff.getScRRole().getRoleId());

                            String generatedToken = JWTUtils.generateJWTToken(tokenDto);

                            LoginResponseDto loginResponseDto = new LoginResponseDto();
                            loginResponseDto.setToken(generatedToken);
                            loginResponseDto.setDetails(tokenDto);

                            return ResponseEntity.ok(loginResponseDto);
                        } catch (Exception e) {
                            log.error("Error while generating token: {}", e.getMessage());
                            throw new BadRequestAlertException(e.getMessage(), "staff", "staff");
                        }
                    }
                }
            }

        } catch (Exception e) {
            log.error("Error while logging in staff member: {}", e.getMessage());
            throw new BadRequestAlertException(e.getMessage(), "staff", "staff");
        }
    }

    @Override
    public ResponseEntity<ScMStaffDto> getLoginUserDetails(String token) {
        try {
            if (!JWTUtils.validateJWTToken(token)) {
                throw new BadRequestAlertException("Invalid token", "staff", "staff");
            } else {
                String username = JWTUtils.getUsernameFromToken(token);
                Optional<ScMStaff> optStaff = staffRepository.findByUsername(username);
                if (!optStaff.isPresent()) {
                    throw new BadRequestAlertException("Invalid token", "staff", "staff");
                } else {
                    ScMStaff staff = optStaff.get();
                    if (!staff.getIsActive()) {
                        throw new BadRequestAlertException("Staff member is not active", "staff", "staff");
                    } else {
                        return ResponseEntity.ok(staffMapper.toDto(staff));
                    }
                }
            }
        } catch (Exception e) {
            log.error("Error while getting login user details: {}", e.getMessage());
            throw new BadRequestAlertException(e.getMessage(), "staff", "staff");
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
