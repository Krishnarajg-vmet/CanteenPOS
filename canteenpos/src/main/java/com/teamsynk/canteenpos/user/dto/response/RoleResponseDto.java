package com.teamsynk.canteenpos.user.dto.response;

import java.util.Set;
import java.util.UUID;

public class RoleResponseDto {

	private UUID id;
    private String roleName;
    private Set<PermissionResponseDto> permissions;
    
	public RoleResponseDto(UUID id, String roleName, Set<PermissionResponseDto> permissions) {
		this.id = id;
		this.roleName = roleName;
		this.permissions = permissions;
	}
	public UUID getId() {
		return id;
	}
	public String getRoleName() {
		return roleName;
	}
	public Set<PermissionResponseDto> getPermissions() {
		return permissions;
	}
}
