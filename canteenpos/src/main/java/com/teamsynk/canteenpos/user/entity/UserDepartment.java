package com.teamsynk.canteenpos.user.entity;

import java.util.UUID;

import com.teamsynk.canteenpos.common.BaseEntity;
import com.teamsynk.canteenpos.common.util.IdGenerator;
import com.teamsynk.canteenpos.organization.entity.Department;

import jakarta.persistence.*;

@Entity
@Table(
    name = "user_department",
    uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "department_id"})
)
public class UserDepartment extends BaseEntity {

    @Id
    @Column(name = "user_department_id", nullable = false, updatable = false)
    private UUID id = IdGenerator.newUUID();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    public UserDepartment(User user, Department department) {
        this.user = user;
        this.department = department;
    }

	public UUID getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}


}
