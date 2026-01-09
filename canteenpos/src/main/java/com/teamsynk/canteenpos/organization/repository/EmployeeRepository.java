package com.teamsynk.canteenpos.organization.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teamsynk.canteenpos.common.enums.EmployeeCategory;
import com.teamsynk.canteenpos.common.enums.EmployeeType;
import com.teamsynk.canteenpos.organization.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
	
	List<Employee> findByIsActiveTrue();
	Optional<Employee> findByEmployeeCodeIgnoreCase(String employeeCode);
	List<Employee> findByEmployeeType(EmployeeType employeeType);
	List<Employee> findByEmployeeCategory(EmployeeCategory employeeCategory);
	boolean existsByEmployeeCodeIgnoreCase(String employeeCode);

}
