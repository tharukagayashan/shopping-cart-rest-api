package com.projects.shoppingcart.service.master.impl;

import com.projects.shoppingcart.dao.master.ScMUserRepository;
import com.projects.shoppingcart.dao.reference.ScRRoleRepository;
import com.projects.shoppingcart.dto.master.ScMUserDto;
import com.projects.shoppingcart.dto.miscellaneous.ApiResponseDto;
import com.projects.shoppingcart.dto.miscellaneous.PaginationDto;
import com.projects.shoppingcart.error.BadRequestAlertException;
import com.projects.shoppingcart.mapper.master.ScMUserMapper;
import com.projects.shoppingcart.model.master.ScMUser;
import com.projects.shoppingcart.model.reference.ScRRole;
import com.projects.shoppingcart.service.master.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final ScMUserMapper userMapper;
    private final ScMUserRepository userRepository;
    private final ScRRoleRepository roleRepository;

    public UserServiceImpl(ScMUserMapper userMapper, ScMUserRepository userRepository, ScRRoleRepository roleRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }


    @Override
    public ResponseEntity<List<ScMUserDto>> getAllUsers() {
        try {
            List<ScMUser> users = userRepository.findAll();
            if (users.isEmpty()) {
                throw new BadRequestAlertException("No users found", "user", "not found");
            } else {
                List<ScMUserDto> userDtoList = userMapper.entityListToDtoList(users);
                return ResponseEntity.ok(userDtoList);
            }
        } catch (Exception e) {
            log.error("Error occurred while getting all users", e);
            throw new BadRequestAlertException(e.getMessage(), "user", "error");
        }
    }

    @Override
    public ResponseEntity<ApiResponseDto<List<ScMUserDto>>> getUserForTable(Integer page, Integer perPage, String sort, String direction, String search) {
        try {
            Page<ScMUser> dbData = null;
            if (direction.equalsIgnoreCase("asc")) {
                dbData = userRepository.getUserForTable(search, PageRequest.of(page, perPage, Sort.by(Sort.Direction.ASC, sort)));
            } else {
                dbData = userRepository.getUserForTable(search, PageRequest.of(page, perPage, Sort.by(Sort.Direction.DESC, sort)));
            }

            ApiResponseDto<List<ScMUserDto>> response = new ApiResponseDto<>();
            PaginationDto pagination = new PaginationDto();
            pagination.setTotal(dbData.getTotalElements());
            pagination.setPerPage(dbData.getSize());
            pagination.setCurrentPage(dbData.getNumber());
            pagination.setFrom(perPage * page + 1);
            pagination.setTo(perPage * page + dbData.getNumberOfElements());
            response.setPagination(pagination);
            response.setResult(userMapper.entityListToDtoList(dbData.getContent()));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error occurred while getting users for table", e);
            throw new BadRequestAlertException(e.getMessage(), "user", "error");
        }
    }

    @Override
    public ResponseEntity<ScMUserDto> getUser(Long userId) {
        try {
            if (userId == null) {
                throw new BadRequestAlertException("User id is required", "user", "required");
            } else {
                Optional<ScMUser> optUser = userRepository.findById(userId);
                if (!optUser.isPresent()) {
                    throw new BadRequestAlertException("User not found", "user", "not.found");
                } else {
                    ScMUserDto userDto = userMapper.toDto(optUser.get());
                    return ResponseEntity.ok(userDto);
                }
            }
        } catch (Exception e) {
            log.error("Error occurred while getting user", e);
            throw new BadRequestAlertException(e.getMessage(), "user", "error");
        }
    }

    @Override
    public ResponseEntity<ScMUserDto> updateUser(Long userId, ScMUserDto scMUserDto) {
        try {
            if (userId == null) {
                throw new BadRequestAlertException("User id is required", "user", "required");
            } else if (userId != scMUserDto.getUserId()) {
                throw new BadRequestAlertException("User id mismatch", "user", "mismatch");
            } else {
                Optional<ScMUser> optUser = userRepository.findById(userId);
                if (!optUser.isPresent()) {
                    throw new BadRequestAlertException("User not found", "user", "not.found");
                } else {
                    Optional<ScRRole> optRole = roleRepository.findById(scMUserDto.getRoleId());

                    if (!optRole.isPresent()) {
                        throw new BadRequestAlertException("Role not found", "role", "not.found");
                    }

                    ScMUser user = optUser.get();
                    user.setFirstName(scMUserDto.getFirstName());
                    user.setLastName(scMUserDto.getLastName());
                    user.setEmail(scMUserDto.getEmail());
                    user.setMobileNo(scMUserDto.getMobileNo());
                    user.setScRRole(optRole.get());

                    user = userRepository.save(user);
                    log.info("User updated successfully");
                    ScMUserDto userDto = userMapper.toDto(user);
                    return ResponseEntity.ok(userDto);
                }
            }
        } catch (Exception e) {
            log.error("Error occurred while updating user", e);
            throw new BadRequestAlertException(e.getMessage(), "user", "error");
        }
    }

}
