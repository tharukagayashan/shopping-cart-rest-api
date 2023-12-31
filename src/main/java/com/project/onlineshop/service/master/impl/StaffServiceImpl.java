package com.project.onlineshop.service.master.impl;

import com.project.onlineshop.dao.master.ScMStaffRepository;
import com.project.onlineshop.dao.reference.ScRRoleRepository;
import com.project.onlineshop.dto.master.ScMStaffDto;
import com.project.onlineshop.dto.miscellaneous.ApiResponseDto;
import com.project.onlineshop.dto.miscellaneous.PaginationDto;
import com.project.onlineshop.error.BadRequestAlertException;
import com.project.onlineshop.mapper.master.ScMStaffMapper;
import com.project.onlineshop.model.master.ScMStaff;
import com.project.onlineshop.model.reference.ScRRole;
import com.project.onlineshop.service.master.StaffService;
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
public class StaffServiceImpl implements StaffService {

    private final ScMStaffRepository staffRepository;
    private final ScMStaffMapper staffMapper;
    private final ScRRoleRepository roleRepository;

    public StaffServiceImpl(ScMStaffRepository staffRepository, ScMStaffMapper staffMapper, ScRRoleRepository roleRepository) {
        this.staffRepository = staffRepository;
        this.staffMapper = staffMapper;
        this.roleRepository = roleRepository;
    }

    @Override
    public ResponseEntity<List<ScMStaffDto>> getAllStaffs() {
        try {
            List<ScMStaff> staffs = staffRepository.findAll();
            if (staffs.isEmpty()) {
                throw new BadRequestAlertException("No staffs found", "staff", "not.found");
            } else {
                List<ScMStaffDto> staffDtos = staffMapper.entityListToDtoList(staffs);
                return ResponseEntity.ok(staffDtos);
            }
        } catch (Exception e) {
            log.error("Error occurred while getting all staffs", e);
            throw new BadRequestAlertException(e.getMessage(), "staff", "error");
        }
    }

    @Override
    public ResponseEntity<ApiResponseDto<List<ScMStaffDto>>> getStaffForTable(Integer page, Integer perPage, String sort, String direction, String search) {
        try {
            Page<ScMStaff> dbData = null;
            if (direction.equalsIgnoreCase("asc")) {
                dbData = staffRepository.getStaffForTable(search, PageRequest.of(page, perPage, Sort.by(Sort.Direction.ASC, sort)));
            } else {
                dbData = staffRepository.getStaffForTable(search, PageRequest.of(page, perPage, Sort.by(Sort.Direction.DESC, sort)));
            }

            ApiResponseDto<List<ScMStaffDto>> response = new ApiResponseDto<>();
            PaginationDto pagination = new PaginationDto();
            pagination.setTotal(dbData.getTotalElements());
            pagination.setPerPage(dbData.getSize());
            pagination.setCurrentPage(dbData.getNumber());
            pagination.setFrom(perPage * page + 1);
            pagination.setTo(perPage * page + dbData.getNumberOfElements());
            response.setPagination(pagination);
            response.setResult(staffMapper.entityListToDtoList(dbData.getContent()));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error occurred while getting staffs for table", e);
            throw new BadRequestAlertException(e.getMessage(), "staff", "error");
        }
    }

    @Override
    public ResponseEntity<ScMStaffDto> getStaff(Long staffId) {
        try {
            if (staffId == null) {
                throw new BadRequestAlertException("Staff id is required", "staff", "required");
            } else {
                Optional<ScMStaff> optStaff = staffRepository.findById(staffId);
                if (!optStaff.isPresent()) {
                    throw new BadRequestAlertException("Staff not found", "staff", "not.found");
                } else {
                    ScMStaffDto staffDto = staffMapper.toDto(optStaff.get());
                    return ResponseEntity.ok(staffDto);
                }
            }
        } catch (Exception e) {
            log.error("Error occurred while getting staff", e);
            throw new BadRequestAlertException(e.getMessage(), "staff", "error");
        }
    }

    @Override
    public ResponseEntity<ScMStaffDto> updateStaff(Long staffId, ScMStaffDto scMStaffDto) {
        try {
            if (staffId == null) {
                throw new BadRequestAlertException("Staff id is required", "staff", "required");
            } else if (staffId != scMStaffDto.getStaffId()) {
                throw new BadRequestAlertException("Staff id mismatch", "staff", "mismatch");
            } else {
                Optional<ScMStaff> optStaff = staffRepository.findById(staffId);
                if (!optStaff.isPresent()) {
                    throw new BadRequestAlertException("Staff not found", "staff", "not.found");
                } else {
                    Optional<ScRRole> optRole = roleRepository.findById(scMStaffDto.getRoleId());

                    if (!optRole.isPresent()) {
                        throw new BadRequestAlertException("Role not found", "role", "not.found");
                    }

                    ScMStaff staff = optStaff.get();
                    staff.setFirstName(scMStaffDto.getFirstName());
                    staff.setLastName(scMStaffDto.getLastName());
                    staff.setEmail(scMStaffDto.getEmail());
                    staff.setMobileNo(scMStaffDto.getMobileNo());
                    staff.setAddress(scMStaffDto.getAddress());
                    staff.setScRRole(optRole.get());

                    staff = staffRepository.save(staff);
                    log.info("Staff updated successfully");
                    ScMStaffDto staffDto = staffMapper.toDto(staff);
                    return ResponseEntity.ok(staffDto);
                }
            }
        } catch (Exception e) {
            log.error("Error occurred while updating staff", e);
            throw new BadRequestAlertException(e.getMessage(), "staff", "error");
        }
    }

}
