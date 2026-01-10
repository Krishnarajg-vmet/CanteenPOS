package com.teamsynk.canteenpos.user.mapper;

import org.springframework.stereotype.Component;

import com.teamsynk.canteenpos.user.dto.request.PermissionRequestDto;
import com.teamsynk.canteenpos.user.dto.response.PermissionResponseDto;
import com.teamsynk.canteenpos.user.entity.Permission;

@Component
public class PermissionMapper {
	
	public static Permission toEntity(PermissionRequestDto dto) {
		
		Permission permission = new Permission();
		permission.setPermissionName(dto.getPermissionName());
		permission.setDescription(dto.getDescription());
		
		return permission;
	}
	
	public static PermissionResponseDto toDto(Permission permission) {
		
		return new PermissionResponseDto(
				permission.getId(),
				permission.getPermissionName(),
				permission.getDescription());
	}

}
