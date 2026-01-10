package com.teamsynk.canteenpos.user.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teamsynk.canteenpos.user.entity.RolePermission;

@Repository
public interface RolePermissionRepository extends JpaRepository<RolePermission, UUID> {

	List<RolePermission> findByIsActiveTrue();
	List<RolePermission> findByRoleId(UUID roleId);
	List<RolePermission> findByPermissionId(UUID permissionId);
	boolean existsByRoleIdAndPermissionId(UUID roleId, UUID permissionId);
	List<RolePermission> findByRoleIdInAndIsActiveTrue(List<UUID> roleIds);

}
