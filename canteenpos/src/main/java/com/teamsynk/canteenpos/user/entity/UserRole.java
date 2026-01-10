package com.teamsynk.canteenpos.user.entity;

import java.util.UUID;

import com.teamsynk.canteenpos.common.BaseEntity;
import com.teamsynk.canteenpos.common.util.IdGenerator;
import com.teamsynk.canteenpos.organization.entity.Branch;

import jakarta.persistence.*;

@Entity
@Table(
    name = "user_role",
    uniqueConstraints = @UniqueConstraint(
        columnNames = {"user_id", "role_id", "branch_id"}
    )
)
public class UserRole extends BaseEntity {

    @Id
    @Column(name = "user_role_id", nullable = false, updatable = false)
    private UUID id = IdGenerator.newUUID();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id")
    private Branch branch;

    public UserRole(User user, Role role) {
        this.user = user;
        this.role = role;
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}
    
}
