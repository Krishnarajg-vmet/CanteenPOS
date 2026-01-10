package com.teamsynk.canteenpos.user.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teamsynk.canteenpos.user.entity.UserBranch;

@Repository
public interface UserBranchRepository extends JpaRepository<UserBranch, UUID> {

	List<UserBranch> findByIsActiveTrue();
	List<UserBranch> findByUserId(UUID userId);
	List<UserBranch> findByBranchId(UUID branchId);
	boolean existsByUserIdAndBranchId(UUID userId, UUID branchId);
	List<UserBranch> findByUserIdIn(List<UUID> userIds);
	List<UserBranch> findByBranchIdIn(List<UUID> branchIds);
	
}
