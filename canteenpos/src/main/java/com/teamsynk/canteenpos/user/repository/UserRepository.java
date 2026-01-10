package com.teamsynk.canteenpos.user.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teamsynk.canteenpos.user.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
	
	List<User> findByIsActiveTrue();
	Optional<User> findByUsernameIgnoreCase(String username);
	Optional<User> findByEmployeeEmployeeCode(String employeeCode);
	boolean existsByUsernameIgnoreCase(String username);

}
