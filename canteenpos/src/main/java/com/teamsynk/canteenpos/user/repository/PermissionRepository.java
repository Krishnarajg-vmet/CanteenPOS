package com.teamsynk.canteenpos.user.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teamsynk.canteenpos.user.entity.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, UUID>{

	List<Permission> findByIsActiveTrue();
	Optional<Permission> findByPermissionNameIgnoreCase(String permissionName);
	boolean existsByPermissionNameIgnoreCase(String permissionName);
	
}
