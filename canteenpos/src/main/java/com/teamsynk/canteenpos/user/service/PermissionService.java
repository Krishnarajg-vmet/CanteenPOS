package com.teamsynk.canteenpos.user.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teamsynk.canteenpos.common.exception.ResourceNotFoundException;
import com.teamsynk.canteenpos.user.dto.request.PermissionRequestDto;
import com.teamsynk.canteenpos.user.dto.response.PermissionResponseDto;
import com.teamsynk.canteenpos.user.entity.Permission;
import com.teamsynk.canteenpos.user.repository.PermissionRepository;

@Service
public class PermissionService {

    private final PermissionRepository permissionRepository;

    public PermissionService(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Transactional
    public PermissionResponseDto createPermission(PermissionRequestDto dto) {
        Permission permission = new Permission();
        permission.setPermissionName(dto.getPermissionName());
        permission.setDescription(dto.getDescription());

        permission = permissionRepository.save(permission);
        return toDto(permission);
    }

    public PermissionResponseDto getPermissionById(UUID id) {
        Permission permission = permissionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Permission", id));
        return toDto(permission);
    }

    @Transactional
    public PermissionResponseDto updatePermission(UUID id, PermissionRequestDto dto) {
        Permission existing = permissionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Permission", id));

        existing.setPermissionName(dto.getPermissionName());
        existing.setDescription(dto.getDescription());

        existing = permissionRepository.save(existing);
        return toDto(existing);
    }

    public List<PermissionResponseDto> getAllPermissions() {
        return permissionRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
    
    public List<PermissionResponseDto> getAllActivePermissions() {
        return permissionRepository.findByIsActiveTrue()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deletePermissionById(UUID id) {
        Permission permission = permissionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Permission", id));
        permissionRepository.delete(permission);
    }

    private PermissionResponseDto toDto(Permission permission) {
        return new PermissionResponseDto(
                permission.getId(),
                permission.getPermissionName(),
                permission.getDescription()
        );
    }
}
