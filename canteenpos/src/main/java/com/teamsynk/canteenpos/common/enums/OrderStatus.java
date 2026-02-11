package com.teamsynk.canteenpos.common.enums;

public enum OrderStatus {
	
	CREATED(1, "Created"),
	PAID(2, "Paid"),
	CANCELLED(3, "Cancelled"),
	PARTIALLY_CANCELLED(4, "Partially Cancelled"),
	REFUNDED(5, "Refunded"),
	PARTIALLY_PAID(6, "Partially Paid");
	
	private final int id;
	private final String label;
	
	OrderStatus(int id, String label) {
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
