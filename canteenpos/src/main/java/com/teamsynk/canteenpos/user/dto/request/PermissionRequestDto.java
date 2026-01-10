package com.teamsynk.canteenpos.user.dto.request;

public class PermissionRequestDto {

	private String permissionName;
	private String description;
	
	public String getPermissionName() {
		return permissionName;
	}
	public String getDescription() {
		return description;
	}
	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
