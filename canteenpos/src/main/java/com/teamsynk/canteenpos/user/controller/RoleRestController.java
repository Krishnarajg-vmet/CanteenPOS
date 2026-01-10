package com.teamsynk.canteenpos.user.controller;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.teamsynk.canteenpos.user.dto.request.RoleRequestDto;
import com.teamsynk.canteenpos.user.dto.response.RoleResponseDto;
import com.teamsynk.canteenpos.user.service.RoleService;

@RestController
@RequestMapping("/api/v1/roles")
public class RoleRestController {

    private final RoleService roleService;

    public RoleRestController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<RoleResponseDto> createRole(@RequestBody RoleRequestDto dto) {
        RoleResponseDto role = roleService.createRole(dto);
        return ResponseEntity.ok(role);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleResponseDto> getRole(@PathVariable UUID id) {
        RoleResponseDto role = roleService.getRoleById(id);
        return ResponseEntity.ok(role);
    }

    @GetMapping("/all")
    public ResponseEntity<List<RoleResponseDto>> getAllRoles() {
        List<RoleResponseDto> roles = roleService.getAllRoles();
        return ResponseEntity.ok(roles);
    }

    @GetMapping
    public ResponseEntity<List<RoleResponseDto>> getAllActiveRoles() {
        List<RoleResponseDto> roles = roleService.getAllActiveRoles();
        return ResponseEntity.ok(roles);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoleResponseDto> updateRole(@PathVariable UUID id, @RequestBody RoleRequestDto dto) {
        RoleResponseDto updatedRole = roleService.updateRole(id, dto);
        return ResponseEntity.ok(updatedRole);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable UUID id) {
        roleService.deleteRoleById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{roleId}/permissions")
    public ResponseEntity<RoleResponseDto> assignPermissions(
            @PathVariable UUID roleId,
            @RequestBody Set<UUID> permissionIds) {
        RoleRequestDto dto = new RoleRequestDto();
        dto.setPermissionIds(permissionIds);
        RoleResponseDto role = roleService.updateRole(roleId, dto);
        return ResponseEntity.ok(role);
    }

    @DeleteMapping("/{roleId}/permissions/{permissionId}")
    public ResponseEntity<RoleResponseDto> unassignPermission(
            @PathVariable UUID roleId,
            @PathVariable UUID permissionId) {

        RoleResponseDto role = roleService.getRoleById(roleId);
        Set<UUID> updatedPermissions = role.getPermissions().stream()
                .filter(p -> !p.getId().equals(permissionId))
                .map(p -> p.getId())
                .collect(java.util.stream.Collectors.toSet());

        RoleRequestDto dto = new RoleRequestDto();
        dto.setPermissionIds(updatedPermissions);

        RoleResponseDto updatedRole = roleService.updateRole(roleId, dto);
        return ResponseEntity.ok(updatedRole);
    }
}
