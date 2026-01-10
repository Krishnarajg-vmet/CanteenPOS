package com.teamsynk.canteenpos.user.service;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teamsynk.canteenpos.common.exception.ResourceNotFoundException;
import com.teamsynk.canteenpos.organization.dto.response.BranchResponseDto;
import com.teamsynk.canteenpos.organization.dto.response.DepartmentResponseDto;
import com.teamsynk.canteenpos.organization.entity.Branch;
import com.teamsynk.canteenpos.organization.entity.Department;
import com.teamsynk.canteenpos.organization.entity.Employee;
import com.teamsynk.canteenpos.user.dto.request.UserRequestDto;
import com.teamsynk.canteenpos.user.dto.response.PermissionResponseDto;
import com.teamsynk.canteenpos.user.dto.response.RoleResponseDto;
import com.teamsynk.canteenpos.user.dto.response.UserResponseDto;
import com.teamsynk.canteenpos.user.entity.Role;
import com.teamsynk.canteenpos.user.entity.User;
import com.teamsynk.canteenpos.user.entity.UserBranch;
import com.teamsynk.canteenpos.user.entity.UserDepartment;
import com.teamsynk.canteenpos.user.entity.UserRole;
import com.teamsynk.canteenpos.user.mapper.UserMapper;
import com.teamsynk.canteenpos.user.repository.*;

import com.teamsynk.canteenpos.organization.repository.BranchRepository;
import com.teamsynk.canteenpos.organization.repository.DepartmentRepository;
import com.teamsynk.canteenpos.organization.repository.EmployeeRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final EmployeeRepository employeeRepository;
    private final RoleRepository roleRepository;
    private final BranchRepository branchRepository;
    private final DepartmentRepository departmentRepository;

    public UserService(UserRepository userRepository,
                       EmployeeRepository employeeRepository,
                       RoleRepository roleRepository,
                       BranchRepository branchRepository,
                       DepartmentRepository departmentRepository) {
        this.userRepository = userRepository;
        this.employeeRepository = employeeRepository;
        this.roleRepository = roleRepository;
        this.branchRepository = branchRepository;
        this.departmentRepository = departmentRepository;
    }

    @Transactional
    public UserResponseDto createUser(UserRequestDto dto) {
        Employee employee = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee", dto.getEmployeeId()));

        User user = UserMapper.toEntity(dto, employee);
        user.setIsActive(true);

        assignRoles(user, dto.getRoleIds());
        assignBranches(user, dto.getBranchIds());
        assignDepartments(user, dto.getDepartmentIds());

        user = userRepository.save(user);
        return mapToResponseDto(user);
    }

    public UserResponseDto getUserById(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", id));
        return mapToResponseDto(user);
    }

    @Transactional
    public UserResponseDto updateUser(UUID id, UserRequestDto dto) {
        User existing = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", id));

        existing.setUsername(dto.getUsername());
        existing.setPassword(dto.getPassword());
        existing.setPasswordResetRequired(
                dto.getPasswordResetRequired() != null ? dto.getPasswordResetRequired() : existing.getPasswordResetRequired()
        );

        if (!existing.getEmployee().getId().equals(dto.getEmployeeId())) {
            Employee employee = employeeRepository.findById(dto.getEmployeeId())
                    .orElseThrow(() -> new ResourceNotFoundException("Employee", dto.getEmployeeId()));
            existing.setEmployee(employee);
        }

        assignRoles(existing, dto.getRoleIds());
        assignBranches(existing, dto.getBranchIds());
        assignDepartments(existing, dto.getDepartmentIds());

        existing = userRepository.save(existing);
        return mapToResponseDto(existing);
    }

    public List<UserResponseDto> getAllActiveUsers() {
        return userRepository.findByIsActiveTrue().stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteUserById(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", id));
        user.setIsActive(false);
        userRepository.save(user);
    }

    @Transactional
    public UserResponseDto assignRoles(UUID userId, Set<UUID> roleIds) {
        User user = getUserOrThrow(userId);
        assignRoles(user, roleIds);
        user = userRepository.save(user);
        return mapToResponseDto(user);
    }

    @Transactional
    public UserResponseDto unassignRole(UUID userId, UUID roleId) {
        User user = getUserOrThrow(userId);
        user.getUserRoles().removeIf(ur -> ur.getRole().getId().equals(roleId));
        user = userRepository.save(user);
        return mapToResponseDto(user);
    }

    @Transactional
    public UserResponseDto assignBranches(UUID userId, Set<UUID> branchIds) {
        User user = getUserOrThrow(userId);
        assignBranches(user, branchIds);
        user = userRepository.save(user);
        return mapToResponseDto(user);
    }

    @Transactional
    public UserResponseDto unassignBranch(UUID userId, UUID branchId) {
        User user = getUserOrThrow(userId);
        user.getUserBranches().removeIf(ub -> ub.getBranch().getId().equals(branchId));
        user = userRepository.save(user);
        return mapToResponseDto(user);
    }

    @Transactional
    public UserResponseDto assignDepartments(UUID userId, Set<UUID> departmentIds) {
        User user = getUserOrThrow(userId);
        assignDepartments(user, departmentIds);
        user = userRepository.save(user);
        return mapToResponseDto(user);
    }

    @Transactional
    public UserResponseDto unassignDepartment(UUID userId, UUID departmentId) {
        User user = getUserOrThrow(userId);
        user.getUserDepartments().removeIf(ud -> ud.getDepartment().getId().equals(departmentId));
        user = userRepository.save(user);
        return mapToResponseDto(user);
    }

    private User getUserOrThrow(UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", userId));
    }

    private void assignRoles(User user, Set<UUID> roleIds) {
        user.getUserRoles().clear();
        if (roleIds != null && !roleIds.isEmpty()) {
            roleIds.forEach(roleId -> {
                Role role = roleRepository.findById(roleId)
                        .orElseThrow(() -> new ResourceNotFoundException("Role", roleId));
                user.getUserRoles().add(new UserRole(user, role));
            });
        }
    }

    private void assignBranches(User user, Set<UUID> branchIds) {
        user.getUserBranches().clear();
        if (branchIds != null && !branchIds.isEmpty()) {
            branchIds.forEach(branchId -> {
                Branch branch = branchRepository.findById(branchId)
                        .orElseThrow(() -> new ResourceNotFoundException("Branch", branchId));
                user.getUserBranches().add(new UserBranch(user, branch));
            });
        }
    }

    private void assignDepartments(User user, Set<UUID> departmentIds) {
        user.getUserDepartments().clear();
        if (departmentIds != null && !departmentIds.isEmpty()) {
            departmentIds.forEach(departmentId -> {
                Department department = departmentRepository.findById(departmentId)
                        .orElseThrow(() -> new ResourceNotFoundException("Department", departmentId));
                user.getUserDepartments().add(new UserDepartment(user, department));
            });
        }
    }

    private UserResponseDto mapToResponseDto(User user) {
        Set<RoleResponseDto> roles = user.getUserRoles().stream()
                .map(ur -> {
                    Role role = ur.getRole();

                    Set<PermissionResponseDto> permissions = role.getRolePermissions().stream()
                            .filter(rp -> rp.getIsActive())
                            .map(rp -> new PermissionResponseDto(
                                    rp.getPermission().getId(),
                                    rp.getPermission().getPermissionName(),
                                    rp.getPermission().getDescription()
                            ))
                            .collect(Collectors.toSet());

                    return new RoleResponseDto(
                            role.getId(),
                            role.getRoleName(),
                            permissions
                    );
                })
                .collect(Collectors.toSet());

        Set<BranchResponseDto> branches = user.getUserBranches().stream()
                .map(ub -> new BranchResponseDto(
                        ub.getBranch().getId(),
                        ub.getBranch().getBranchName(),
                        ub.getBranch().getBranchCode(),
                        ub.getBranch().getCompany().getId(),
                        ub.getBranch().getCompany().getCompanyName()))
                .collect(Collectors.toSet());

        Set<DepartmentResponseDto> departments = user.getUserDepartments().stream()
                .map(ud -> new DepartmentResponseDto(
                        ud.getDepartment().getId(),
                        ud.getDepartment().getDepartmentName(),
                        ud.getDepartment().getDepartmentCode()))
                .collect(Collectors.toSet());

        return UserMapper.toDto(user, roles, branches, departments);
    }
}
