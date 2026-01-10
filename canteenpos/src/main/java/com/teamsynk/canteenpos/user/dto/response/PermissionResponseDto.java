package com.teamsynk.canteenpos.user.dto.response;

import java.util.UUID;

public class PermissionResponseDto {
	
	private UUID id;
    private String permissionName;
    private String description;
    
	public PermissionResponseDto(UUID id, String permissionName, String description) {
		super();
		this.id = id;
		this.permissionName = permissionName;
		this.description = description;
	}
	public UUID getId() {
		return id;
	}
	public String getPermissionName() {
		return permissionName;
	}
	public String getDescription() {
		return description;
	}

}
