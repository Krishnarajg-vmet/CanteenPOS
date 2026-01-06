package com.teamsynk.canteenpos.common.enums;

public enum SalesStatus {
	
	CREATED(1, "Created"),
	COMPLETED(2, "Completed"),
	CANCELLED(3, "Cancelled"),
	REFUNDED(4, "Refunded");
	
	private final int id;
	private final String label;
	
	SalesStatus(int id, String label) {
		this.id = id;
		this.label = label;
	}
	
	public int getId() {
		return id;
	}
	
	public String getLabel() {
		return label;
	}

}
