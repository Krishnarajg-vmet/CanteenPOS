package com.teamsynk.canteenpos.user.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teamsynk.canteenpos.user.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID>{

	List<Role> findByIsActiveTrue();
	Optional<Role> findByRoleNameIgnoreCase(String roleName);
	boolean existsByRoleNameIgnoreCase(String roleName);
}
