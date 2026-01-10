package com.teamsynk.canteenpos.user.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teamsynk.canteenpos.user.entity.UserDepartment;

@Repository
public interface UserDepartmentRepository extends JpaRepository<UserDepartment, UUID>{

	List<UserDepartment> findByIsActiveTrue();
	List<UserDepartment> findByUserId(UUID userId);
	List<UserDepartment> findByDepartmentId(UUID departmentId);
	boolean existsByUserIdAndDepartmentId(UUID userId, UUID departmentId);
	List<UserDepartment> findByUserIdIn(List<UUID> userIds);
	List<UserDepartment> findByDepartmentIdIn(List<UUID> departmentIds);
	
}
