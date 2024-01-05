package com.projects.shoppingcart.rest.master;

import com.projects.shoppingcart.dto.master.ScMUserDto;
import com.projects.shoppingcart.dto.miscellaneous.ApiResponseDto;
import com.projects.shoppingcart.service.master.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ScMUserDto>> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/table")
    public ResponseEntity<ApiResponseDto<List<ScMUserDto>>> getUserForTable(
            @RequestParam(defaultValue = "0", required = false) Integer page,
            @RequestParam(defaultValue = "10", required = false) Integer perPage,
            @RequestParam(defaultValue = "userId", required = false) String sort,
            @RequestParam(defaultValue = "desc", required = false) String direction,
            @RequestParam(defaultValue = "", required = false) String search
    ) {
        return userService.getUserForTable(page, perPage, sort, direction, search);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ScMUserDto> getUser(@PathVariable Long userId) {
        return userService.getUser(userId);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<ScMUserDto> updateUser(@PathVariable Long userId, @RequestBody ScMUserDto scMUserDto) {
        return userService.updateUser(userId, scMUserDto);
    }

}
