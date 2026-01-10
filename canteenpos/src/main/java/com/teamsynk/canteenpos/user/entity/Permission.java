package com.teamsynk.canteenpos.user.entity;

import java.util.UUID;

import com.teamsynk.canteenpos.common.BaseEntity;
import com.teamsynk.canteenpos.common.util.IdGenerator;

import jakarta.persistence.*;

@Entity
@Table(name = "permission")
public class Permission extends BaseEntity{

    @Id
    @Column(name = "permission_id", nullable = false, updatable = false)
    private UUID id = IdGenerator.newUUID();

    @Column(name = "permission_name", nullable = false, unique = true)
    private String permissionName;

    @Column(name = "description", nullable = false)
    private String description;

    public UUID getId() {
        return id;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Permission)) return false;
        Permission that = (Permission) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
