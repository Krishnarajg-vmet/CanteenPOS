package com.teamsynk.canteenpos.user.mapper;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.teamsynk.canteenpos.user.dto.request.RoleRequestDto;
import com.teamsynk.canteenpos.user.dto.response.PermissionResponseDto;
import com.teamsynk.canteenpos.user.dto.response.RoleResponseDto;
import com.teamsynk.canteenpos.user.entity.Permission;
import com.teamsynk.canteenpos.user.entity.Role;
import com.teamsynk.canteenpos.user.entity.RolePermission;

@Component
public class RoleMapper {
	
	public static Role toEntity(RoleRequestDto dto, Set<Permission> permissions) {

        Role role = new Role();
        role.setRoleName(dto.getRoleName());

        if (permissions != null) {
            Set<RolePermission> rolePermissions =
                    permissions.stream()
                               .map(permission -> {
                                   RolePermission rp = new RolePermission();
                                   rp.setRole(role);
                                   rp.setPermission(permission);
                                   return rp;
                               })
                               .collect(Collectors.toSet());

            role.setRolePermissions(rolePermissions);
        }

        return role;
    }
	
	public static RoleResponseDto toDto(Role role) {

        Set<PermissionResponseDto> permissions =
                role.getRolePermissions()
                    .stream()
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
    }



}
