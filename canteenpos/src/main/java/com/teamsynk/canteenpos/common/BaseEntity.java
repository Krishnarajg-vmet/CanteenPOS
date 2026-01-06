package com.teamsynk.canteenpos.common;

import java.time.Instant;
import java.util.UUID;

import org.springframework.data.annotation.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.*;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
	
	@CreatedDate
	@Column(name="created_dt", nullable = false, updatable = false)
	private Instant createdDt;
	
	@LastModifiedDate
	@Column(name="modified_dt")
	private Instant modifiedDt;
	
	@CreatedBy
	@Column(name="created_by", nullable = false, updatable = false)
	private UUID createdBy;
	
	@LastModifiedBy
	@Column(name="modified_by")
	private UUID modifiedBy;
	
	@Column(name="is_active", nullable = false)
	private Boolean isActive = true;

	public Instant getCreatedDt() {
		return createdDt;
	}

	public void setCreatedDt(Instant createdDt) {
		this.createdDt = createdDt;
	}

	public Instant getModifiedDt() {
		return modifiedDt;
	}

	public void setModifiedDt(Instant modifiedDt) {
		this.modifiedDt = modifiedDt;
	}

	public UUID getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(UUID createdBy) {
		this.createdBy = createdBy;
	}

	public UUID getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(UUID modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

}
