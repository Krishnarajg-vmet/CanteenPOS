package com.teamsynk.canteenpos.user.dto.request;

import java.util.Set;
import java.util.UUID;

public class UserRequestDto {

	private String username;
	private String password;
	private Boolean passwordResetRequired;
	private UUID employeeId;
	
	private Set<UUID> roleIds;
    private Set<UUID> branchIds;
    private Set<UUID> departmentIds;
	
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public Boolean getPasswordResetRequired() {
		return passwordResetRequired;
	}
	public UUID getEmployeeId() {
		return employeeId;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setPasswordResetRequired(Boolean passwordResetRequired) {
		this.passwordResetRequired = passwordResetRequired;
	}
	public void setEmployeeId(UUID employeeId) {
		this.employeeId = employeeId;
	}
	public Set<UUID> getRoleIds() {
		return roleIds;
	}
	public Set<UUID> getBranchIds() {
		return branchIds;
	}
	public Set<UUID> getDepartmentIds() {
		return departmentIds;
	}
	public void setRoleIds(Set<UUID> roleIds) {
		this.roleIds = roleIds;
	}
	public void setBranchIds(Set<UUID> branchIds) {
		this.branchIds = branchIds;
	}
	public void setDepartmentIds(Set<UUID> departmentIds) {
		this.departmentIds = departmentIds;
	}
	
}
