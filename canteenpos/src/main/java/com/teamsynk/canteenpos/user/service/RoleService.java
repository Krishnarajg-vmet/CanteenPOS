package com.teamsynk.canteenpos.user.service;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teamsynk.canteenpos.common.exception.ResourceNotFoundException;
import com.teamsynk.canteenpos.user.dto.request.RoleRequestDto;
import com.teamsynk.canteenpos.user.dto.response.RoleResponseDto;
import com.teamsynk.canteenpos.user.entity.Permission;
import com.teamsynk.canteenpos.user.entity.Role;
import com.teamsynk.canteenpos.user.entity.RolePermission;
import com.teamsynk.canteenpos.user.mapper.RoleMapper;
import com.teamsynk.canteenpos.user.repository.PermissionRepository;
import com.teamsynk.canteenpos.user.repository.RoleRepository;

@Service
public class RoleService {

    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    public RoleService(RoleRepository roleRepository, PermissionRepository permissionRepository) {
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
    }

    @Transactional
    public RoleResponseDto createRole(RoleRequestDto dto) {
        Set<Permission> permissions = fetchPermissions(dto.getPermissionIds());

        Role role = RoleMapper.toEntity(dto, permissions);
        role = roleRepository.save(role);

        return RoleMapper.toDto(role);
    }

    public RoleResponseDto getRoleById(UUID id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role", id));
        return RoleMapper.toDto(role);
    }

    @Transactional
    public RoleResponseDto updateRole(UUID id, RoleRequestDto dto) {
        Role existing = roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role", id));

        existing.setRoleName(dto.getRoleName());

        existing.getRolePermissions().clear();
        Set<Permission> permissions = fetchPermissions(dto.getPermissionIds());
        for (Permission permission : permissions) {
            RolePermission rp = new RolePermission();
            rp.setRole(existing);
            rp.setPermission(permission);
            existing.getRolePermissions().add(rp);
        }

        existing = roleRepository.save(existing);
        return RoleMapper.toDto(existing);
    }
    
    public List<RoleResponseDto> getAllActiveRoles() {
        return roleRepository.findByIsActiveTrue()
                .stream()
                .map(RoleMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<RoleResponseDto> getAllRoles() {
        return roleRepository.findAll()
                .stream()
                .map(RoleMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteRoleById(UUID id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role", id));
        roleRepository.delete(role);
    }

    private Set<Permission> fetchPermissions(Set<UUID> permissionIds) {
        if (permissionIds == null || permissionIds.isEmpty()) {
            return Set.of();
        }
        return permissionIds.stream()
                .map(pid -> permissionRepository.findById(pid)
                        .orElseThrow(() -> new ResourceNotFoundException("Permission", pid)))
                .collect(Collectors.toSet());
    }
}
