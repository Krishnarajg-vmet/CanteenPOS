package com.teamsynk.canteenpos.organization.entity;

import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

import com.teamsynk.canteenpos.common.BaseEntity;
import com.teamsynk.canteenpos.common.enums.BloodGroup;
import com.teamsynk.canteenpos.common.enums.EmployeeCategory;
import com.teamsynk.canteenpos.common.enums.EmployeeType;
import com.teamsynk.canteenpos.common.enums.Gender;
import com.teamsynk.canteenpos.common.enums.MaritalStatus;
import com.teamsynk.canteenpos.common.enums.Title;
import com.teamsynk.canteenpos.common.util.IdGenerator;
import com.teamsynk.canteenpos.location.entity.Address;

import jakarta.persistence.*;

@Entity
@Table(name="employee")
public class Employee extends BaseEntity {
	
	@Id
	@Column(name="employee_id", nullable = false, updatable = false)
	private UUID id = IdGenerator.newUUID();
	
	@Enumerated(EnumType.STRING)
	@Column(name="title", nullable = false)
	private Title title;
	
	@Column(name="first_name", nullable = false)
	private String firstName;
	
	@Column(name="last_name", nullable = false)
	private String lastName;
	
	@Column(name="dob", nullable = false)
	private LocalDate dob;
	
	@Enumerated(EnumType.STRING)
	@Column(name="gender", nullable = false)
	private Gender gender;
	
	@Enumerated(EnumType.STRING)
	@Column(name="marital_status", nullable = false)
	private MaritalStatus maritalStatus;
	
	@Enumerated(EnumType.STRING)
	@Column(name="blood_group", nullable = false)
	private BloodGroup bloodGroup;
	
	@Enumerated(EnumType.STRING)
	@Column(name="employee_type", nullable = false)
	private EmployeeType employeeType;
	
	@Enumerated(EnumType.STRING)
	@Column(name="employee_category", nullable = false)
	private EmployeeCategory employeeCategory;
	
	@Embedded
	private Address address;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="designation_id", nullable = false)
	private Designation designation;
	
	@Column(name="mobile_number", nullable = false, unique = true)
	private String mblNumber;
	
	@Column(name="qualification")
	private String qualification;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="home_branch_id")
	private Branch homeBranch;
	
	protected Employee() {
		
	}

	public UUID getId() {
		return id;
	}

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

	@Transient
	public int getAge() {
	    return dob != null ? Period.between(dob, LocalDate.now()).getYears() : 0;
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

	public Branch getHomeBranch() {
		return homeBranch;
	}

	public void setHomeBranch(Branch homeBranch) {
		this.homeBranch = homeBranch;
	}

	public Designation getDesignation() {
		return designation;
	}

	public void setDesignation(Designation designation) {
		this.designation = designation;
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

}
