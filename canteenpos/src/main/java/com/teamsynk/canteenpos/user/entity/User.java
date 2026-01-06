package com.teamsynk.canteenpos.user.entity;

import java.util.Set;
import java.util.UUID;

import com.teamsynk.canteenpos.common.BaseEntity;
import com.teamsynk.canteenpos.common.util.IdGenerator;
import com.teamsynk.canteenpos.organization.entity.Branch;
import com.teamsynk.canteenpos.organization.entity.Department;
import com.teamsynk.canteenpos.organization.entity.Employee;

import jakarta.persistence.*;

@Entity
@Table(name="user")
public class User extends BaseEntity {
	
	@Id
	@Column(name="user_id", nullable = false, updatable = false)
	private UUID id = IdGenerator.newUUID();
	
	@Column(name="username", nullable = false, unique = true)
	private String username;
	
	@Column(name="password", nullable = false)
	private String password;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
	    name = "user_role",
	    joinColumns = @JoinColumn(name = "user_id"),
	    inverseJoinColumns = @JoinColumn(name = "role_id")
	)
	private Set<Role> roles;
	
	@Column(name="password_reset_required")
	private Boolean passwordResetRequired = true;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="employee_id", nullable = false)
	private Employee employee;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
	    name = "user_department",
	    joinColumns = @JoinColumn(name = "user_id"),
	    inverseJoinColumns = @JoinColumn(name = "department_id")
	)
	private Set<Department> departments;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
	    name = "user_branch",
	    joinColumns = @JoinColumn(name = "user_id"),
	    inverseJoinColumns = @JoinColumn(name = "branch_id")
	)
	private Set<Branch> locations;
	
	protected User() {
		
	}

	public UUID getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Boolean getPasswordResetRequired() {
		return passwordResetRequired;
	}

	public void setPasswordResetRequired(Boolean passwordResetRequired) {
		this.passwordResetRequired = passwordResetRequired;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Set<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(Set<Department> departments) {
		this.departments = departments;
	}

	public Set<Branch> getLocations() {
		return locations;
	}

	public void setLocations(Set<Branch> locations) {
		this.locations = locations;
	}
	
	@Override
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if (!(o instanceof User)) return false;
	    User user = (User) o;
	    return id != null && id.equals(user.id);
	}

	@Override
	public int hashCode() {
	    return getClass().hashCode();
	}

}
