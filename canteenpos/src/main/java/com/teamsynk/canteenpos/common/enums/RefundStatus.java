package com.teamsynk.canteenpos.common.enums;

public enum RefundStatus {
	INITIATED(1, "Initiated"),
    COMPLETED(2, "Completed"),
    FAILED(3, "Failed");
	
	private final int id;
	private final String label;
	
	RefundStatus(int id, String label) {
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
