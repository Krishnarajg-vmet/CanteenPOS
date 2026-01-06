package com.teamsynk.canteenpos.common.enums;

public enum PaymentStatus {
	
	PENDING(1, "Pending"),
	PAID(2, "Paid"),
	FAILED(3, "Failed"),
	REFUNDED(4, "Refunded");
	
	private final int id;
	private final String label;
	
	PaymentStatus(int id, String label) {
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
