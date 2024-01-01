package com.projects.shoppingcart.service.master;

import com.projects.shoppingcart.dto.master.ScMStaffDto;
import com.projects.shoppingcart.dto.miscellaneous.ApiResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StaffService {
    ResponseEntity<List<ScMStaffDto>> getAllStaffs();

    ResponseEntity<ApiResponseDto<List<ScMStaffDto>>> getStaffForTable(Integer page, Integer perPage, String sort, String direction, String search);

    ResponseEntity<ScMStaffDto> getStaff(Long staffId);

    ResponseEntity<ScMStaffDto> updateStaff(Long staffId, ScMStaffDto bnMStaffDto);
}
