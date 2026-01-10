package com.teamsynk.canteenpos.user.dto.request;

import java.util.Set;
import java.util.UUID;

public class RoleRequestDto {

	private String roleName;
	private Set<UUID> permissionIds;
	
	public String getRoleName() {
		return roleName;
	}
	public Set<UUID> getPermissionIds() {
		return permissionIds;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public void setPermissionIds(Set<UUID> permissionIds) {
		this.permissionIds = permissionIds;
	}
}
