package com.teamsynk.canteenpos.user.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.teamsynk.canteenpos.user.dto.request.PermissionRequestDto;
import com.teamsynk.canteenpos.user.dto.response.PermissionResponseDto;
import com.teamsynk.canteenpos.user.service.PermissionService;

@RestController
@RequestMapping("/api/v1/permissions")
public class PermissionRestController {

    private final PermissionService permissionService;

    public PermissionRestController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @PostMapping
    public ResponseEntity<PermissionResponseDto> createPermission(@RequestBody PermissionRequestDto dto) {
        PermissionResponseDto permission = permissionService.createPermission(dto);
        return ResponseEntity.ok(permission);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PermissionResponseDto> getPermission(@PathVariable UUID id) {
        PermissionResponseDto permission = permissionService.getPermissionById(id);
        return ResponseEntity.ok(permission);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PermissionResponseDto>> getAllPermissions() {
        List<PermissionResponseDto> permissions = permissionService.getAllPermissions();
        return ResponseEntity.ok(permissions);
    }

    @GetMapping
    public ResponseEntity<List<PermissionResponseDto>> getAllActivePermissions() {
        List<PermissionResponseDto> permissions = permissionService.getAllActivePermissions();
        return ResponseEntity.ok(permissions);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PermissionResponseDto> updatePermission(
            @PathVariable UUID id,
            @RequestBody PermissionRequestDto dto) {

        PermissionResponseDto updatedPermission = permissionService.updatePermission(id, dto);
        return ResponseEntity.ok(updatedPermission);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePermission(@PathVariable UUID id) {
        permissionService.deletePermissionById(id);
        return ResponseEntity.noContent().build();
    }
}
