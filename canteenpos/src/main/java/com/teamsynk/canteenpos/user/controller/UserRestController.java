package com.teamsynk.canteenpos.user.controller;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.teamsynk.canteenpos.user.dto.request.UserRequestDto;
import com.teamsynk.canteenpos.user.dto.response.UserResponseDto;
import com.teamsynk.canteenpos.user.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto dto) {
        return new ResponseEntity<>(userService.createUser(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
    
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllActiveUsers() {
        return ResponseEntity.ok(userService.getAllActiveUsers());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(
            @PathVariable UUID id,
            @RequestBody UserRequestDto dto
    ) {
        return ResponseEntity.ok(userService.updateUser(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{userId}/roles")
    public ResponseEntity<UserResponseDto> assignRoles(
            @PathVariable UUID userId,
            @RequestBody Set<UUID> roleIds
    ) {
        return ResponseEntity.ok(userService.assignRoles(userId, roleIds));
    }

    @DeleteMapping("/{userId}/roles/{roleId}")
    public ResponseEntity<UserResponseDto> unassignRole(
            @PathVariable UUID userId,
            @PathVariable UUID roleId
    ) {
        return ResponseEntity.ok(userService.unassignRole(userId, roleId));
    }

    @PostMapping("/{userId}/branches")
    public ResponseEntity<UserResponseDto> assignBranches(
            @PathVariable UUID userId,
            @RequestBody Set<UUID> branchIds
    ) {
        return ResponseEntity.ok(userService.assignBranches(userId, branchIds));
    }

    @DeleteMapping("/{userId}/branches/{branchId}")
    public ResponseEntity<UserResponseDto> unassignBranch(
            @PathVariable UUID userId,
            @PathVariable UUID branchId
    ) {
        return ResponseEntity.ok(userService.unassignBranch(userId, branchId));
    }

    @PostMapping("/{userId}/departments")
    public ResponseEntity<UserResponseDto> assignDepartments(
            @PathVariable UUID userId,
            @RequestBody Set<UUID> departmentIds
    ) {
        return ResponseEntity.ok(userService.assignDepartments(userId, departmentIds));
    }

    @DeleteMapping("/{userId}/departments/{departmentId}")
    public ResponseEntity<UserResponseDto> unassignDepartment(
            @PathVariable UUID userId,
            @PathVariable UUID departmentId
    ) {
        return ResponseEntity.ok(userService.unassignDepartment(userId, departmentId));
    }
}
