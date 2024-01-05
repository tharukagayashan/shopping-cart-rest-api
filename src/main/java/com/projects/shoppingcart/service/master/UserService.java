package com.projects.shoppingcart.service.master;

import com.projects.shoppingcart.dto.master.ScMUserDto;
import com.projects.shoppingcart.dto.miscellaneous.ApiResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    ResponseEntity<List<ScMUserDto>> getAllUsers();

    ResponseEntity<ApiResponseDto<List<ScMUserDto>>> getUserForTable(Integer page, Integer perPage, String sort, String direction, String search);

    ResponseEntity<ScMUserDto> getUser(Long userId);

    ResponseEntity<ScMUserDto> updateUser(Long userId, ScMUserDto scMUserDto);
}
