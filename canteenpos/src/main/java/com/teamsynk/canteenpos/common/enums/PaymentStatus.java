package com.teamsynk.canteenpos.common.enums;

public enum PaymentStatus {
	
	INITIATED(1, "Initiated"),
	COMPLETED(2, "Completed"),
    PROCESSING(3, "Processing"),
    FAILED(4, "Failed"),
    REFUNDED(5, "Refunded");
	
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
