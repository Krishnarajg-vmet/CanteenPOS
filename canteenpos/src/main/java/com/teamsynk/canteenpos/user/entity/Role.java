package com.teamsynk.canteenpos.user.entity;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.teamsynk.canteenpos.common.BaseEntity;
import com.teamsynk.canteenpos.common.util.IdGenerator;

import jakarta.persistence.*;

@Entity
@Table(name="role")
public class Role extends BaseEntity {
	
	@Id
	@Column(name="role_id", nullable = false, updatable = false)
	private UUID id = IdGenerator.newUUID();
	
	@Column(name="role_name", nullable = false, unique = true)
	private String roleName;
	
	@OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<RolePermission> rolePermissions = new HashSet<>();

	public UUID getId() {
		return id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;
        Role role = (Role) o;
        return id != null && id.equals(role.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

	public Set<RolePermission> getRolePermissions() {
		return rolePermissions;
	}

	public void setRolePermissions(Set<RolePermission> rolePermissions) {
		this.rolePermissions = rolePermissions;
	}

}
