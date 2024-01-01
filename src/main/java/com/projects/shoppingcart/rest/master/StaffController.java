package com.projects.shoppingcart.rest.master;

import com.projects.shoppingcart.dto.master.ScMStaffDto;
import com.projects.shoppingcart.dto.miscellaneous.ApiResponseDto;
import com.projects.shoppingcart.service.master.StaffService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/staff")
public class StaffController {
    private final StaffService staffService;

    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ScMStaffDto>> getAllStaffs() {
        return staffService.getAllStaffs();
    }

    @GetMapping("/table")
    public ResponseEntity<ApiResponseDto<List<ScMStaffDto>>> getStaffForTable(
            @RequestParam(defaultValue = "0", required = false) Integer page,
            @RequestParam(defaultValue = "10", required = false) Integer perPage,
            @RequestParam(defaultValue = "staffId", required = false) String sort,
            @RequestParam(defaultValue = "desc", required = false) String direction,
            @RequestParam(defaultValue = "", required = false) String search
    ) {
        return staffService.getStaffForTable(page, perPage, sort, direction, search);
    }

    @GetMapping("/{staffId}")
    public ResponseEntity<ScMStaffDto> getStaff(@PathVariable Long staffId) {
        return staffService.getStaff(staffId);
    }

    @PutMapping("/{staffId}")
    public ResponseEntity<ScMStaffDto> updateStaff(@PathVariable Long staffId, @RequestBody ScMStaffDto scMStaffDto) {
        return staffService.updateStaff(staffId, scMStaffDto);
    }

}
