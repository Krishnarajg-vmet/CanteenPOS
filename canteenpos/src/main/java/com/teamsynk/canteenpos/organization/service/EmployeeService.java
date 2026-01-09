package com.teamsynk.canteenpos.organization.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teamsynk.canteenpos.common.exception.ResourceNotFoundException;
import com.teamsynk.canteenpos.organization.dto.request.EmployeeRequestDto;
import com.teamsynk.canteenpos.organization.dto.response.EmployeeResponseDto;
import com.teamsynk.canteenpos.organization.entity.Branch;
import com.teamsynk.canteenpos.organization.entity.Designation;
import com.teamsynk.canteenpos.organization.entity.Employee;
import com.teamsynk.canteenpos.organization.mapper.EmployeeMapper;
import com.teamsynk.canteenpos.organization.repository.BranchRepository;
import com.teamsynk.canteenpos.organization.repository.DesignationRepository;
import com.teamsynk.canteenpos.organization.repository.EmployeeRepository;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DesignationRepository designationRepository;
    private final BranchRepository branchRepository;

    public EmployeeService(
            EmployeeRepository employeeRepository,
            DesignationRepository designationRepository,
            BranchRepository branchRepository) {
        this.employeeRepository = employeeRepository;
        this.designationRepository = designationRepository;
        this.branchRepository = branchRepository;
    }

    @Transactional
    public EmployeeResponseDto createEmployee(EmployeeRequestDto request) {

        Designation designation = designationRepository.findById(request.getDesignationId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Designation", request.getDesignationId()));

        Branch branch = null;
        if (request.getBranchId() != null) {
            branch = branchRepository.findById(request.getBranchId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Branch", request.getBranchId()));
        }

        Employee employee = EmployeeMapper.toEntity(request, branch, designation);
        employee.setIsActive(true);

        return EmployeeMapper.toDto(employeeRepository.save(employee));
    }

    public EmployeeResponseDto getEmployeeById(UUID id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", id));

        return EmployeeMapper.toDto(employee);
    }

    @Transactional
    public EmployeeResponseDto updateEmployee(UUID id, EmployeeRequestDto dto) {

        Employee existing = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", id));

        Designation designation = designationRepository.findById(dto.getDesignationId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Designation", dto.getDesignationId()));

        Branch branch = null;
        if (dto.getBranchId() != null) {
            branch = branchRepository.findById(dto.getBranchId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Branch", dto.getBranchId()));
        }

        existing.setTitle(dto.getTitle());
        existing.setFirstName(dto.getFirstName());
        existing.setLastName(dto.getLastName());
        existing.setDob(dto.getDob());
        existing.setGender(dto.getGender());
        existing.setMaritalStatus(dto.getMaritalStatus());
        existing.setBloodGroup(dto.getBloodGroup());
        existing.setEmployeeType(dto.getEmployeeType());
        existing.setEmployeeCategory(dto.getEmployeeCategory());
        existing.setAddress(dto.getAddress());
        existing.setMblNumber(dto.getMblNumber());
        existing.setQualification(dto.getQualification());
        existing.setDesignation(designation);
        existing.setHomeBranch(branch);

        return EmployeeMapper.toDto(employeeRepository.save(existing));
    }

    public List<EmployeeResponseDto> getAllActiveEmployees() {
        return employeeRepository.findByIsActiveTrue()
                .stream()
                .map(EmployeeMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<EmployeeResponseDto> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(EmployeeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteEmployeeById(UUID id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", id));

        employee.setIsActive(false);
        employeeRepository.save(employee);
    }
}
