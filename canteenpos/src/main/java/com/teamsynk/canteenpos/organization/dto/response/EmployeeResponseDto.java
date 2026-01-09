package com.teamsynk.canteenpos.organization.dto.response;

import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

import com.teamsynk.canteenpos.common.enums.BloodGroup;
import com.teamsynk.canteenpos.common.enums.EmployeeCategory;
import com.teamsynk.canteenpos.common.enums.EmployeeType;
import com.teamsynk.canteenpos.common.enums.Gender;
import com.teamsynk.canteenpos.common.enums.MaritalStatus;
import com.teamsynk.canteenpos.common.enums.Title;
import com.teamsynk.canteenpos.location.entity.Address;

import jakarta.persistence.Transient;

public class EmployeeResponseDto {

	private UUID id;
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
	private String designationName;
	private String mblNumber;
	private String qualification;
	private UUID homeBranchId;
	private String homeBranchName;
	
	public EmployeeResponseDto(UUID id, Title title, String firstName, String lastName, String employeeCode, LocalDate dob, Gender gender,
			MaritalStatus maritalStatus, BloodGroup bloodGroup, EmployeeType employeeType,
			EmployeeCategory employeeCategory, Address address, UUID designationId, String designationName,
			String mblNumber, String qualification, UUID homeBranchId, String homeBranchName) {
		super();
		this.id = id;
		this.title = title;
		this.firstName = firstName;
		this.lastName = lastName;
		this.employeeCode = employeeCode;
		this.dob = dob;
		this.gender = gender;
		this.maritalStatus = maritalStatus;
		this.bloodGroup = bloodGroup;
		this.employeeType = employeeType;
		this.employeeCategory = employeeCategory;
		this.address = address;
		this.designationId = designationId;
		this.designationName = designationName;
		this.mblNumber = mblNumber;
		this.qualification = qualification;
		this.homeBranchId = homeBranchId;
		this.homeBranchName = homeBranchName;
	}
	public UUID getId() {
		return id;
	}
	public Title getTitle() {
		return title;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getEmployeeCode() {
		return employeeCode;
	}
	public LocalDate getDob() {
		return dob;
	}
	public Gender getGender() {
		return gender;
	}
	public MaritalStatus getMaritalStatus() {
		return maritalStatus;
	}
	public BloodGroup getBloodGroup() {
		return bloodGroup;
	}
	public EmployeeType getEmployeeType() {
		return employeeType;
	}
	public EmployeeCategory getEmployeeCategory() {
		return employeeCategory;
	}
	public Address getAddress() {
		return address;
	}
	public UUID getDesignationId() {
		return designationId;
	}
	public String getDesignationName() {
		return designationName;
	}
	public String getMblNumber() {
		return mblNumber;
	}
	public String getQualification() {
		return qualification;
	}
	public UUID getHomeBranchId() {
		return homeBranchId;
	}
	public String getHomeBranchName() {
		return homeBranchName;
	}
	@Transient
	public int getAge() {
	    return dob != null ? Period.between(dob, LocalDate.now()).getYears() : 0;
	}
}
