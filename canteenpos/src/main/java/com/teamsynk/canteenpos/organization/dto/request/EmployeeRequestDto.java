package com.teamsynk.canteenpos.organization.dto.request;

import java.time.LocalDate;
import java.util.UUID;

import com.teamsynk.canteenpos.common.enums.BloodGroup;
import com.teamsynk.canteenpos.common.enums.EmployeeCategory;
import com.teamsynk.canteenpos.common.enums.EmployeeType;
import com.teamsynk.canteenpos.common.enums.Gender;
import com.teamsynk.canteenpos.common.enums.MaritalStatus;
import com.teamsynk.canteenpos.common.enums.Title;
import com.teamsynk.canteenpos.location.entity.Address;

public class EmployeeRequestDto {
	
	private Title title;
	private String firstName;
	private String lastName;
	private String employeeCode;
	private LocalDate dob;
	private Gender gender;
	private MaritalStatus maritalStatus;
	private BloodGroup bloodGroup;
	private EmployeeType employeeType;
	private EmployeeCategory employeeCategory;
	private Address address;
	private UUID designationId;
	private String mblNumber;
	private String qualification;
	private UUID branchId;
	
	public Title getTitle() {
		return title;
	}
	public void setTitle(Title title) {
		this.title = title;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public MaritalStatus getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(MaritalStatus maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public BloodGroup getBloodGroup() {
		return bloodGroup;
	}
	public void setBloodGroup(BloodGroup bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
	public EmployeeType getEmployeeType() {
		return employeeType;
	}
	public void setEmployeeType(EmployeeType employeeType) {
		this.employeeType = employeeType;
	}
	public EmployeeCategory getEmployeeCategory() {
		return employeeCategory;
	}
	public void setEmployeeCategory(EmployeeCategory employeeCategory) {
		this.employeeCategory = employeeCategory;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public UUID getDesignationId() {
		return designationId;
	}
	public void setDesignationId(UUID designationId) {
		this.designationId = designationId;
	}
	public String getMblNumber() {
		return mblNumber;
	}
	public void setMblNumber(String mblNumber) {
		this.mblNumber = mblNumber;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public UUID getBranchId() {
		return branchId;
	}
	public void setBranchId(UUID branchId) {
		this.branchId = branchId;
	}
	public String getEmployeeCode() {
		return employeeCode;
	}
	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}
	
}
