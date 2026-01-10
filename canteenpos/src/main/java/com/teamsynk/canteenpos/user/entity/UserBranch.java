package com.teamsynk.canteenpos.user.entity;

import java.util.UUID;

import com.teamsynk.canteenpos.common.BaseEntity;
import com.teamsynk.canteenpos.common.util.IdGenerator;
import com.teamsynk.canteenpos.organization.entity.Branch;

import jakarta.persistence.*;

@Entity
@Table(
    name = "user_branch",
    uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "branch_id"})
)
public class UserBranch extends BaseEntity {

    @Id
    @Column(name = "user_branch_id", nullable = false, updatable = false)
    private UUID id = IdGenerator.newUUID();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id", nullable = false)
    private Branch branch;

    public UserBranch(User user, Branch branch) {
        this.user = user;
        this.branch = branch;
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

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

}

