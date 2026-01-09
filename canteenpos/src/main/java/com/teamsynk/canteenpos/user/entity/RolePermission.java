package com.teamsynk.canteenpos.user.entity;

import java.util.UUID;

import com.teamsynk.canteenpos.common.BaseEntity;
import com.teamsynk.canteenpos.common.util.IdGenerator;

import jakarta.persistence.*;

@Entity
@Table(
    name = "role_permission",
    uniqueConstraints = @UniqueConstraint(
        columnNames = {"role_id", "permission_id"}
    )
)
public class RolePermission extends BaseEntity {

    @Id
    @Column(name = "role_permission_id", nullable = false, updatable = false)
    private UUID id = IdGenerator.newUUID();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "permission_id", nullable = false)
    private Permission permission;

	public UUID getId() {
		return id;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Permission getPermission() {
		return permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}
      
}

