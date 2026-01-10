package com.teamsynk.canteenpos.user.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teamsynk.canteenpos.user.entity.UserRole;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, UUID> {

	List<UserRole> findByIsActiveTrue();
	List<UserRole> findByUserId(UUID userId);
	List<UserRole> findByRoleId(UUID roleId);
	List<UserRole> findByRoleIdAndBranchId(UUID roleId, UUID branchId);
	boolean existsByUserIdAndRoleIdAndBranchId(UUID userId, UUID roleId, UUID branchId);
	List<UserRole> findByUserIdIn(List<UUID> userIds);
	List<UserRole> findByRoleRoleNameIgnoreCase(String roleName);

}
