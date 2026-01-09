package com.teamsynk.canteenpos.organization.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teamsynk.canteenpos.organization.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, UUID> {
	
	List<Department> findByIsActiveTrue();
	Optional<Department> findByDepartmentNameIgnoreCase(String departmentName);
	Optional<Department> findByDepartmentCodeIgnoreCase(String departmentCode);
	boolean existsByDepartmentNameIgnoreCase(String departmentCode);

}
